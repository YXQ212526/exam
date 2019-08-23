package com.yuanxueqi.exam.service;

import java.util.stream.Collectors;

import com.yuanxueqi.exam.dao.AccountMapper;
import com.yuanxueqi.exam.dao.CurrencyMapper;
import com.yuanxueqi.exam.dao.DepositMapper;
import com.yuanxueqi.exam.dao.UserAddressMapper;
import com.yuanxueqi.exam.dao.UserMapper;
import com.yuanxueqi.exam.data.Deposit;
import com.yuanxueqi.exam.data.UpdateBalance;
import com.yuanxueqi.exam.data.UserAddress;
import com.yuanxueqi.exam.data.rep.DepositEntity;
import com.yuanxueqi.exam.data.rep.enums.RespDescEnum;
import com.yuanxueqi.exam.data.req.DepositParam;
import com.yuanxueqi.exam.enums.DepositStateEnum;
import com.yuanxueqi.exam.rest.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class DepositService {

  @Autowired
  DepositMapper depositMapper;

  @Autowired
  CurrencyMapper currencyMapper;

  @Autowired
  UserAddressMapper userAddressMapper;

  @Autowired
  UserMapper userMapper;
  @Autowired
  AccountMapper accountMapper;

  public MyResponse createDepositOrder(DepositParam param) {
    if (param == null) {
      return new MyResponse(RespDescEnum.PARAM_NULL);
    }
    if (accountMapper.select(param.getUserId(), param.getCurrencyName()) == null) {
      return new MyResponse(RespDescEnum.NO_ACCOUNT);
    }

    if (!accountMapper.select(param.getUserId(), param.getCurrencyName()).getAddress().equals(param.getAddress())) {
      return new MyResponse(RespDescEnum.ADDRESS_ERROR);
    }
    if (!userAddressMapper.select(param.getUserId()).stream().map(UserAddress::getAddress).collect(Collectors.toList())
        .contains(param.getAddress())) {
      return new MyResponse(RespDescEnum.NOT_BIND_ADDRESS);
    }

    Deposit deposit = Deposit.builder()
        .address(param.getAddress())
        .amount(param.getAmount())
        .confirm(param.getConfirm())
        .currencyName(param.getCurrencyName())
        .height(param.getHeight())
        .txHash(param.getTxHash())
        .userId(param.getUserId())
        .state(param.getConfirm() < 6 ? DepositStateEnum.WAITING.getCode() : DepositStateEnum.ACCOUNT_IN.getCode())
        .createAt(System.currentTimeMillis())
        .version(0)
        .build();
    try {
      depositMapper.insert(deposit);
    } catch (DuplicateKeyException e) {
    }
    return new MyResponse(RespDescEnum.SUCCESS);
  }

  public MyResponse getDepositRecord(Long userId) {
    return new MyResponse(DepositEntity.ToEntityList(depositMapper.selectById(userId)));

  }

  public MyResponse updateConfirm(DepositParam param) {
    if (param == null) {
      return new MyResponse(RespDescEnum.PARAM_NULL);
    }
    Deposit deposit = depositMapper.selectByIndex(param.getCurrencyName(), param.getTxHash());

    if (null == deposit
        || !param.getUserId().equals(deposit.getUserId())
        || !param.getAddress().equals(deposit.getAddress())
        || param.getAmount().compareTo(deposit.getAmount()) != 0
        || !param.getHeight().equals(deposit.getHeight())

    ) {
      return new MyResponse(RespDescEnum.NO_DEPOSIT);
    }
    if (param.getConfirm() >= 0 && param.getConfirm() <= 6) {
      if (param.getConfirm() == 6) {
        deposit.setState(DepositStateEnum.ACCOUNT_IN.getCode());
      } else if (param.getConfirm() == 0) {
        deposit.setState(DepositStateEnum.INIT.getCode());
      } else {
        deposit.setState(DepositStateEnum.WAITING.getCode());
      }
    } else {
      return new MyResponse(RespDescEnum.CONFIRM_ERROR);
    }
    deposit.setConfirm(param.getConfirm());
    depositMapper.update(deposit);
    if(param.getConfirm()==6)
    {
      accountMapper.update(UpdateBalance.builder()
      .amount(param.getAmount())
      .currencyName(param.getCurrencyName())
      .userId(param.getUserId())
      .build());
    }
    return new MyResponse(RespDescEnum.SUCCESS);
  }


}

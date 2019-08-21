package com.yuanxueqi.exam.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.yuanxueqi.exam.dao.CurrencyMapper;
import com.yuanxueqi.exam.dao.DepositMapper;
import com.yuanxueqi.exam.data.Currency;
import com.yuanxueqi.exam.data.Deposit;
import com.yuanxueqi.exam.data.rep.DepositEntity;
import com.yuanxueqi.exam.data.req.DepositParam;
import com.yuanxueqi.exam.enums.DepositStateEnum;
import com.yuanxueqi.exam.error.ProjectError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class DepositService {

  @Autowired
  DepositMapper depositMapper;

  @Autowired
  CurrencyMapper currencyMapper;

  public String createDepositOrder(DepositParam param) {
    if (param == null) {
      return "传入参数为空";
    }
    if (!currencyMapper.select().stream().map(Currency::getName).collect(Collectors.toList()).contains(param.getCurrencyName())) {
      return "不支持该币种";
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
    return "成功";
  }

  public List<DepositEntity> getDepositRecord(Long userId) {
    return DepositEntity.ToEntityList(depositMapper.selectById(userId));

  }

  public String updateConfirm(DepositParam param) {
    if (param == null) {
      return "传入参数为空";
    }
    Deposit deposit = depositMapper.selectByIndex(param.getCurrencyName(), param.getTxHash());
    if (null == deposit) {
      return "没有该充值记录";
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
      return "确认次数不正确";
    }
    deposit.setConfirm(param.getConfirm());
    depositMapper.update(deposit);
    return "成功";
  }


}

package com.yuanxueqi.exam.service;

import java.math.BigDecimal;
import java.util.stream.Collectors;

import com.yuanxueqi.exam.dao.AccountMapper;
import com.yuanxueqi.exam.dao.CurrencyMapper;
import com.yuanxueqi.exam.dao.UserMapper;
import com.yuanxueqi.exam.data.Account;
import com.yuanxueqi.exam.data.Currency;
import com.yuanxueqi.exam.data.UpdateBalance;
import com.yuanxueqi.exam.data.rep.enums.RespDescEnum;
import com.yuanxueqi.exam.data.req.OpenAccountParam;
import com.yuanxueqi.exam.enums.AccountEnum;
import com.yuanxueqi.exam.rest.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  @Autowired
  AccountMapper accountMapper;

  @Autowired
  UserMapper userMapper;

  @Autowired
  CurrencyMapper currencyMapper;

  public MyResponse openAccount(OpenAccountParam param) {
    if (userMapper.selectById(param.getUserId()) == null) {
      return new MyResponse(RespDescEnum.NO_USER);
    }
    if (!currencyMapper.select().stream().map(Currency::getName).collect(Collectors.toList()).contains(param.getCurrencyName())) {
      return new MyResponse(RespDescEnum.UNSUPPORT_CURRENCY);
    }
    try {
      accountMapper.insert(
          Account.builder()
              .address(String.valueOf(System.currentTimeMillis()))
              .balance(BigDecimal.ZERO)
              .currencyName(param.getCurrencyName())
              .userId(param.getUserId())
              .state(AccountEnum.NORMAL.getCode())
              .build()
      );
    }catch (DuplicateKeyException e){

    }
    return new MyResponse(RespDescEnum.SUCCESS);
  }

  public MyResponse getAccount(Long userId, String currencyName) {

    return new MyResponse(accountMapper.select(userId, currencyName));
  }

  public MyResponse getAllAccount(Long userId) {

    return new MyResponse(accountMapper.selectAll(userId));
  }
  public MyResponse updateBalance(UpdateBalance updateBalance)
  {
    accountMapper.update(updateBalance);
    return new MyResponse(RespDescEnum.SUCCESS);
  }
}

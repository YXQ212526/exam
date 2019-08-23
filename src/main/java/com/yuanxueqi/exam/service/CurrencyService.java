package com.yuanxueqi.exam.service;

import com.yuanxueqi.exam.dao.CurrencyMapper;
import com.yuanxueqi.exam.data.Currency;
import com.yuanxueqi.exam.data.rep.enums.RespDescEnum;
import com.yuanxueqi.exam.enums.OnOffStateEnum;
import com.yuanxueqi.exam.rest.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

  @Autowired
  public CurrencyMapper mapper;

  @Cacheable(value = "currencies")
  public MyResponse displayAllCurrencies() {

    return new MyResponse(mapper.select());
  }

  public MyResponse insertCurrency(Currency currency) {

    if (currency == null) {
      return new MyResponse(RespDescEnum.PARAM_NULL);
    }
    if (OnOffStateEnum.get(currency.getState()) == null) {
      return new MyResponse(RespDescEnum.STATE_ERROR);

    }
    try {
      mapper.insert(currency);
    } catch (DuplicateKeyException e) {

    }
    return new MyResponse(RespDescEnum.SUCCESS);

  }
}

package com.yuanxueqi.exam.service;

import java.util.List;

import com.yuanxueqi.exam.dao.CurrencyMapper;
import com.yuanxueqi.exam.data.Currency;
import com.yuanxueqi.exam.error.ProjectError;
import org.apache.tomcat.jni.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

  @Autowired
  public CurrencyMapper mapper;

  public List<Currency> displayAllCurrencies() {

    return mapper.select();
  }

  public String insertCurrency(Currency currency) {
    if (currency == null) {
      return "传入参数为空";
    }
    try {
      mapper.insert(currency);
    } catch (DuplicateKeyException e) {

    }
    return "成功";
  }
}

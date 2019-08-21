package com.yuanxueqi.exam.dao;

import java.util.List;

import com.yuanxueqi.exam.data.Currency;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CurrencyMapper {

  List<Currency> select();

  int insert(Currency currency);

}

package com.yuanxueqi.exam.dao;

import com.yuanxueqi.exam.data.Currency;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CurrencyMapper {

  List<Currency> select();

  int insert(Currency currency);

}

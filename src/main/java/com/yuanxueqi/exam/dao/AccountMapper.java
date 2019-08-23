package com.yuanxueqi.exam.dao;

import java.math.BigDecimal;
import java.util.List;
import com.yuanxueqi.exam.data.Account;
import com.yuanxueqi.exam.data.UpdateBalance;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {

  void insert(Account account);

  Account select(Long userId, String currencyName);

  List<Account> selectAll(Long userId);

  void update(UpdateBalance updateBalance);
}

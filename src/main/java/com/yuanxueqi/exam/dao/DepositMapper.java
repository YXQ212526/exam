package com.yuanxueqi.exam.dao;

import java.util.List;

import com.yuanxueqi.exam.data.Deposit;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepositMapper {

  void insert(Deposit deposit);

  List<Deposit> selectById(Long userId);

  Deposit selectByIndex(String currencyName, Long txHash);

  int update(Deposit deposit);
}

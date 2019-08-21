package com.yuanxueqi.exam.dao;

import java.util.List;

import com.yuanxueqi.exam.data.UserAddress;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAddressMapper {

  List<String> select(Long userId);

  int insert(UserAddress userAddress);
}

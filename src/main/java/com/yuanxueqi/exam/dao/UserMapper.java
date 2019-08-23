package com.yuanxueqi.exam.dao;

import com.yuanxueqi.exam.data.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

  User selectByName(String name);

  User selectById(Long id);

  void insert(User user);

}

package com.yuanxueqi.exam.service;

import java.util.List;

import com.yuanxueqi.exam.dao.UserAddressMapper;
import com.yuanxueqi.exam.data.UserAddress;
import com.yuanxueqi.exam.error.ProjectError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class UserAddressService {

  @Autowired
  UserAddressMapper mapper;

  public List<String> getAddress(Long userId) {
    return mapper.select(userId);
  }

  public String insertUser(UserAddress userAddress) {
    try {
      mapper.insert(userAddress);
    } catch (DuplicateKeyException e) {

    }
    return "成功";

  }
}

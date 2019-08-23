package com.yuanxueqi.exam.service;

import com.yuanxueqi.exam.dao.AccountMapper;
import com.yuanxueqi.exam.dao.UserAddressMapper;
import com.yuanxueqi.exam.dao.UserMapper;
import com.yuanxueqi.exam.data.Account;
import com.yuanxueqi.exam.data.UserAddress;
import com.yuanxueqi.exam.data.rep.enums.RespDescEnum;
import com.yuanxueqi.exam.data.req.UserAddressParam;
import com.yuanxueqi.exam.enums.AccountEnum;
import com.yuanxueqi.exam.rest.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class UserAddressService {

  @Autowired
  UserAddressMapper userAddressMappermapper;
  @Autowired
  UserMapper userMapper;
  @Autowired
  AccountMapper accountMapper;

  public MyResponse getAddress(Long userId) {
    return new MyResponse(userAddressMappermapper.select(userId));
  }

  public MyResponse insertUserAddress(UserAddressParam userAddress) {
    if (userMapper.selectById(userAddress.getUserId()) == null) {
      return new MyResponse(RespDescEnum.NO_USER);
    }
    if (accountMapper.selectAll(
        userAddress.getUserId()).stream().map(Account::getAddress).filter((address) -> address.equals(userAddress.getAddress())).count() == 0) {
      return new MyResponse(RespDescEnum.NOT_BIND_ACCOUNT);
    }
    try {
      userAddressMappermapper.insert(UserAddress.builder()
          .state(AccountEnum.NORMAL.getCode())
          .userId(userAddress.getUserId())
          .address(userAddress.getAddress())
          .build()
      );
    } catch (DuplicateKeyException e) {

    }
    return new MyResponse(RespDescEnum.SUCCESS);

  }
}

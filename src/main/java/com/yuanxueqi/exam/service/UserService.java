package com.yuanxueqi.exam.service;

import com.yuanxueqi.exam.dao.UserMapper;
import com.yuanxueqi.exam.data.User;
import com.yuanxueqi.exam.data.rep.enums.RespDescEnum;
import com.yuanxueqi.exam.data.req.UserParam;
import com.yuanxueqi.exam.enums.OnOffStateEnum;
import com.yuanxueqi.exam.rest.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  UserMapper mapper;

  public MyResponse selectByName(String name) {
    User user = mapper.selectByName(name);
    if (user == null) {
      return new MyResponse(RespDescEnum.NO_USER);
    }
    return new MyResponse(user);
  }

  public MyResponse selectById(Long id) {
    User user = mapper.selectById(id);
    if (user == null) {
      return new MyResponse(RespDescEnum.NO_USER);
    }
    return new MyResponse(user);
  }

  public MyResponse insert(UserParam userParam) {
    if (userParam == null) {
      return new MyResponse(RespDescEnum.PARAM_NULL);
    }
    try {
      mapper.insert(User.builder()
          .createAt(System.currentTimeMillis())
          .name(userParam.getName())
          .phone(userParam.getPhone())
          .state(OnOffStateEnum.ON.getCode())
          .build());
    } catch (DuplicateKeyException e) {

    }
    return new MyResponse(RespDescEnum.SUCCESS);
  }
}

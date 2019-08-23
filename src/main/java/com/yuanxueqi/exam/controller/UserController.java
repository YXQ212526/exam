package com.yuanxueqi.exam.controller;

import com.yuanxueqi.exam.data.req.UserParam;
import com.yuanxueqi.exam.rest.MyResponse;
import com.yuanxueqi.exam.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "用户信息")
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  UserService service;

  @ApiOperation("通过用户名字查用户信息")
  @GetMapping("/select/name/{name}")
  public MyResponse selectByName(@PathVariable("name") String name) {
    return service.selectByName(name);
  }

  @ApiOperation("通过用户id查用户信息")
  @GetMapping("/select/id/{id}")
  public MyResponse selectById(@PathVariable("id") Long id) {
    return service.selectById(id);
  }

  @ApiOperation("创建新用户")
  @PostMapping("/create")
  public MyResponse insert(@RequestBody UserParam userParam) {
    return service.insert(userParam);
  }
}

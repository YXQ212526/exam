package com.yuanxueqi.exam.controller;

import java.util.List;

import com.yuanxueqi.exam.service.UserAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "用户绑定地址信息")
@RestController
@RequestMapping("/user")
public class UserAddressController {

  @Autowired
  UserAddressService userAddressService;

  @ApiOperation("获取用户绑定地址")
  @GetMapping("/address")
  public List<String> getAddress(@ApiParam("用户ID") @RequestParam Long userId) {
    return userAddressService.getAddress(userId);
  }

}

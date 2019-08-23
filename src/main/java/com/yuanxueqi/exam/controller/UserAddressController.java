package com.yuanxueqi.exam.controller;

import com.yuanxueqi.exam.data.req.UserAddressParam;
import com.yuanxueqi.exam.rest.MyResponse;
import com.yuanxueqi.exam.service.UserAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "用户绑定地址信息")
@RestController
@RequestMapping("/address")
public class UserAddressController {

  @Autowired
  UserAddressService userAddressService;

  @ApiOperation("获取用户绑定地址")
  @GetMapping("/get")
  public MyResponse getAddress(@ApiParam("用户ID") @RequestParam Long userId) {
    return userAddressService.getAddress(userId);
  }

  @ApiOperation("插入用户绑定地址")
  @PostMapping("/insert")
  public MyResponse insertUserAddress(@RequestBody @ApiParam("用户地址信息") UserAddressParam userAddress) {
    return userAddressService.insertUserAddress(userAddress);
  }
}

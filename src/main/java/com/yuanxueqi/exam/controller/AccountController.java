package com.yuanxueqi.exam.controller;

import com.yuanxueqi.exam.data.req.OpenAccountParam;
import com.yuanxueqi.exam.rest.MyResponse;
import com.yuanxueqi.exam.service.AccountService;
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

@Api(description = "账户信息")
@RestController
@RequestMapping("/account")
public class AccountController {

  @Autowired
  AccountService accountService;

  @ApiOperation("开户")
  @PostMapping("/create")
  public MyResponse openAccount(@RequestBody @ApiParam("开户信息") OpenAccountParam param) {
    return accountService.openAccount(param);
  }

  @ApiOperation("获取账户")
  @GetMapping("/get")
  public MyResponse getAccount(@RequestParam @ApiParam("用户ID") Long userId, @RequestParam @ApiParam("币种") String currencyName) {
    return accountService.getAccount(userId, currencyName);
  }

  @ApiOperation("获取所有账户")
  @GetMapping("/list")
  public MyResponse getAllAccount(@RequestParam @ApiParam("用户ID") Long userId) {
    return accountService.getAllAccount(userId);
  }

}

package com.yuanxueqi.exam.controller;

import com.yuanxueqi.exam.data.req.DepositParam;
import com.yuanxueqi.exam.rest.MyResponse;
import com.yuanxueqi.exam.service.DepositService;
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

@Api(description = "充值")
@RestController
@RequestMapping("/deposit")
public class DepositController {

  @Autowired
  DepositService depositService;

  @ApiOperation("创建充值订单")
  @PostMapping("/create")
  public MyResponse createDepositOrder(@RequestBody @ApiParam("充值参数") DepositParam param) {
    return depositService.createDepositOrder(param);
  }

  @ApiOperation("获取充值订单")
  @GetMapping("/get")
  public MyResponse getDepositRecord(@RequestParam @ApiParam("用户ID") Long userId) {
    return depositService.getDepositRecord(userId);
  }

  @ApiOperation("更改确认次数")
  @PostMapping("/confirm")
  public MyResponse updateConfirm(@RequestBody @ApiParam("用户ID") DepositParam param) {
    return depositService.updateConfirm(param);
  }
}

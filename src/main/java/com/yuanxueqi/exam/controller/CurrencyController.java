package com.yuanxueqi.exam.controller;

import com.yuanxueqi.exam.data.Currency;
import com.yuanxueqi.exam.rest.MyResponse;
import com.yuanxueqi.exam.service.CurrencyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "币种信息")
@RestController
@RequestMapping("/currency")
public class CurrencyController {

  @Autowired
  CurrencyService service;

  @ApiOperation("获取所有币种")
  @GetMapping("/list")
  public MyResponse getCurrencies() {
    return service.displayAllCurrencies();
  }

  @ApiOperation("插入币种")
  @PostMapping("/insert")
  public MyResponse insertCurrency(@RequestBody @ApiParam("币种信息") Currency currency) {
    return service.insertCurrency(currency);
  }
}

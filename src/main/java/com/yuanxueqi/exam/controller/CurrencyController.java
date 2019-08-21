package com.yuanxueqi.exam.controller;


import java.util.List;

import com.yuanxueqi.exam.data.Currency;
import com.yuanxueqi.exam.service.CurrencyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
  public List<Currency> getCurrencies() {
    return service.displayAllCurrencies();
  }

}

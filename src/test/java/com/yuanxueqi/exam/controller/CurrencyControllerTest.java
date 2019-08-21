package com.yuanxueqi.exam.controller;


import java.util.ArrayList;
import java.util.List;

import com.yuanxueqi.exam.data.Currency;
import com.yuanxueqi.exam.enums.OnOffStateEnum;
import com.yuanxueqi.exam.service.CurrencyService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class CurrencyControllerTest {

  @Autowired
  CurrencyService currencyService;
  @Autowired
  TestRestTemplate testRestTemplate;
  private static final String url = "/currency";
  private static final int code = 200;

  @Test
  public void getCurrencies() {
    currencyService.insertCurrency(Currency.builder()
        .currencyId(100L)
        .name("btc")
        .state(OnOffStateEnum.ON.getCode())
        .build());
    ResponseEntity<List> response = testRestTemplate.getForEntity(url + "/list", List.class);
    Assert.assertEquals(code, response.getStatusCodeValue());
    Assert.assertEquals(1, response.getBody().size());

  }

}


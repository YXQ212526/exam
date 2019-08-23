package com.yuanxueqi.exam.controller;

import com.yuanxueqi.exam.data.Currency;
import com.yuanxueqi.exam.enums.OnOffStateEnum;
import com.yuanxueqi.exam.service.CurrencyService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CurrencyControllerTest {

  @Autowired
  CurrencyService currencyService;
  @Autowired
  TestRestTemplate testRestTemplate;
  private static final String url = "/currency";
  private static final int code = 200;

  @Test
  public void b_getCurrencies() {
    ResponseEntity<String> response = testRestTemplate.getForEntity(url + "/list", String.class);
    Assert.assertEquals(code, response.getStatusCodeValue());

  }

  @Test
  public void a_insert() {
    ResponseEntity<String> response = testRestTemplate.postForEntity(url + "/insert", Currency.builder()
        .currencyId(100L)
        .name("btc")
        .state(OnOffStateEnum.ON.getCode())
        .build(), String.class);
    Assert.assertEquals(code, response.getStatusCodeValue());
  }
}


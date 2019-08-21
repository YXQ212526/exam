package com.yuanxueqi.exam.controller;

import java.math.BigDecimal;
import java.util.List;


import com.yuanxueqi.exam.data.req.DepositParam;
import com.yuanxueqi.exam.service.UserAddressService;
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
public class DepositControllerTest {

  @Autowired
  UserAddressService service;
  @Autowired
  TestRestTemplate testRestTemplate;
  private static final String url = "/deposit";
  private static final int code = 200;

  @Test
  public void createDepositOrder() {

    ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(url + "/create", DepositParam.builder()
        .address("icbc")
        .amount(BigDecimal.ONE)
        .confirm(1)
        .currencyName("btc")
        .txHash(1L)
        .height(1L)
        .userId(1L)
        .build(), String.class);
    Assert.assertEquals(code, responseEntity.getStatusCodeValue());

  }

  @Test
  public void getDepositRecord() {
    ResponseEntity<List> response = testRestTemplate.getForEntity(url + "/get?userId={userId}", List.class, 1);
    Assert.assertEquals(code, response.getStatusCodeValue());
    Assert.assertEquals(1, response.getBody().size());
  }

  @Test
  public void updateConfirm() {
    ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(url + "/confirm", DepositParam.builder()
        .address("icbc")
        .amount(BigDecimal.ONE)
        .confirm(3)
        .currencyName("btc")
        .txHash(1L)
        .height(1L)
        .userId(1L)
        .build(), String.class);
    Assert.assertEquals(code, responseEntity.getStatusCodeValue());
  }
}

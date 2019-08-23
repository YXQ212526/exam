package com.yuanxueqi.exam.controller;

import java.math.BigDecimal;
import java.util.List;


import com.yuanxueqi.exam.dao.AccountMapper;
import com.yuanxueqi.exam.data.Account;
import com.yuanxueqi.exam.data.Currency;
import com.yuanxueqi.exam.data.UserAddress;
import com.yuanxueqi.exam.data.req.DepositParam;
import com.yuanxueqi.exam.data.req.OpenAccountParam;
import com.yuanxueqi.exam.data.req.UserAddressParam;
import com.yuanxueqi.exam.data.req.UserParam;
import com.yuanxueqi.exam.enums.OnOffStateEnum;
import com.yuanxueqi.exam.service.AccountService;
import com.yuanxueqi.exam.service.CurrencyService;
import com.yuanxueqi.exam.service.UserAddressService;
import com.yuanxueqi.exam.service.UserService;
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
  UserService userService;
  @Autowired
  AccountService accountService;
  @Autowired
  UserAddressService userAddressService;
  @Autowired
  CurrencyService currencyService;
  @Autowired
  TestRestTemplate testRestTemplate;

  private static final String url = "/deposit";
  private static final int code = 200;

  @Test
  public void createDepositOrder() {

    currencyService.insertCurrency(
        Currency.builder()
            .currencyId(100L)
            .name("btc")
            .state(OnOffStateEnum.ON.getCode())
            .build());

    userService.insert(UserParam.builder()
        .name("a")
        .phone("1")
        .build());
    accountService.openAccount(OpenAccountParam.
        builder()
        .currencyName("btc")
        .userId(1L)
       . build());
    Account account=(Account)accountService.getAccount(1L,"btc").getData();

    userAddressService.insertUserAddress(
        UserAddressParam.builder()
            .address(account.getAddress())
            .userId(1L)
            .build());

    ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(url + "/create", DepositParam.builder()
        .address(account.getAddress())
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
    ResponseEntity<String> response = testRestTemplate.getForEntity(url + "/get?userId={userId}", String.class, 1);
    Assert.assertEquals(code, response.getStatusCodeValue());

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

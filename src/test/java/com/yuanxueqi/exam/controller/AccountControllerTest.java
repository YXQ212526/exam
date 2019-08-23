package com.yuanxueqi.exam.controller;


import com.yuanxueqi.exam.data.Currency;
import com.yuanxueqi.exam.data.req.OpenAccountParam;
import com.yuanxueqi.exam.data.req.UserParam;
import com.yuanxueqi.exam.enums.OnOffStateEnum;
import com.yuanxueqi.exam.service.CurrencyService;
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
public class AccountControllerTest {

  @Autowired
  TestRestTemplate testRestTemplate;

  @Autowired
  CurrencyService currencyService;
  @Autowired
  UserService userService;
  private static final String url = "/account";
  private static final int code = 200;

  @Test
  public void a_openAccount() {
    currencyService.insertCurrency(Currency.builder()
        .currencyId(100L)
        .name("btc")
        .state(OnOffStateEnum.ON.getCode())
        .build());
    userService.insert(UserParam.builder()
        .name("a")
        .phone("1")
        .build());
    ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(url + "/create", OpenAccountParam.builder()
        .currencyName("btc")
        .userId(1L)
        .build(), String.class);
    Assert.assertEquals(code, responseEntity.getStatusCodeValue());
  }

  @Test
  public void getAccount() {
    ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(url + "/get?userId={userId}&currencyName={currencyName}",
        String.class, 1L, "btc");
    Assert.assertEquals(code, responseEntity.getStatusCodeValue());
  }

  @Test
  public void getAllAccount() {
    ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(url + "/list?userId={userId}", String.class, 1L);
    Assert.assertEquals(code, responseEntity.getStatusCodeValue());
  }

}

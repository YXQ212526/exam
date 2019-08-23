package com.yuanxueqi.exam.controller;

import com.yuanxueqi.exam.data.UserAddress;
import com.yuanxueqi.exam.data.req.UserParam;
import com.yuanxueqi.exam.enums.OnOffStateEnum;
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
public class UserAddressControllerTest {

  @Autowired
  UserAddressService userAddressService;
  @Autowired
  UserService userService;
  @Autowired
  TestRestTemplate testRestTemplate;

  private static final String url = "/address";
  private static final int code = 200;

  @Test
  public void b_getAddress() {

    ResponseEntity<String> response = testRestTemplate.getForEntity(url + "/get?userId={userId}", String.class, 1);
    Assert.assertEquals(code, response.getStatusCodeValue());
  }

  @Test
  public void a_insertUserAddress() {
    userService.insert(UserParam.builder()
        .name("a")
        .phone("1")
        .build());

    ResponseEntity<String> response = testRestTemplate.postForEntity(url + "/insert", UserAddress.builder()
        .address("icbc")
        .userId(1L)
        .state(OnOffStateEnum.ON.getCode())
        .build(), String.class);
    Assert.assertEquals(code, response.getStatusCodeValue());
  }
}

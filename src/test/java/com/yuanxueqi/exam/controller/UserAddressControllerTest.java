package com.yuanxueqi.exam.controller;


import java.util.List;

import com.yuanxueqi.exam.data.UserAddress;
import com.yuanxueqi.exam.enums.OnOffStateEnum;
import com.yuanxueqi.exam.service.UserAddressService;
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
public class UserAddressControllerTest {

  @Autowired
  UserAddressService service;
  @Autowired
  TestRestTemplate testRestTemplate;
  private static final String url = "/user";
  private static final int code = 200;

  @Test
  public void getAddress() {
    service.insertUser(UserAddress.builder()
        .address("icbc")
        .userId(1L)
        .state(OnOffStateEnum.ON.getCode())
        .build());
    ResponseEntity<List> response = testRestTemplate.getForEntity(url + "/address?userId={userId}", List.class, 1);
    Assert.assertEquals(code, response.getStatusCodeValue());
    Assert.assertEquals(1, response.getBody().size());
  }
}

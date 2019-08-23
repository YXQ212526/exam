package com.yuanxueqi.exam.controller;

import com.yuanxueqi.exam.data.req.UserParam;
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
public class UserControllerTest {

  @Autowired
  UserService currencyService;
  @Autowired
  TestRestTemplate testRestTemplate;
  private static final String url = "/user";
  private static final int code = 200;

  @Test
  public void selectByName() {
    ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(url + "/select/name/{name}", String.class, "a");
    Assert.assertEquals(code, responseEntity.getStatusCodeValue());
  }

  @Test
  public void selectById() {
    ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(url + "/select/id/{id}", String.class, 1);
    Assert.assertEquals(code, responseEntity.getStatusCodeValue());
  }

  @Test
  public void a_insert() {
    ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(url + "/create",
        UserParam.builder()
            .name("a")
            .phone("1")
            .build(), String.class);
    Assert.assertEquals(code, responseEntity.getStatusCodeValue());
  }

}

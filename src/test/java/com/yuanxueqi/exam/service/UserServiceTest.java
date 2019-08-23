package com.yuanxueqi.exam.service;

import com.yuanxueqi.exam.data.User;
import com.yuanxueqi.exam.data.rep.enums.RespDescEnum;
import com.yuanxueqi.exam.data.req.UserParam;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {

  @Autowired
  UserService service;

  @Test
  public void a_insert() {

    RespDescEnum respDescEnum = (RespDescEnum) service.insert(UserParam.builder()
        .name("a")
        .phone("1")
        .build()).getData();
    Assert.assertEquals(RespDescEnum.SUCCESS, respDescEnum);
  }

  @Test
  public void b_selectByName() {
    User user = (User) service.selectByName("a").getData();
    Assert.assertEquals("1", user.getPhone());
  }

  @Test
  public void c_selectById() {
    User user = (User) service.selectById(1L).getData();
    Assert.assertEquals("1", user.getPhone());
  }

}

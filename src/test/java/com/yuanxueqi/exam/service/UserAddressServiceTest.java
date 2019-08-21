package com.yuanxueqi.exam.service;

import com.yuanxueqi.exam.data.UserAddress;
import com.yuanxueqi.exam.enums.OnOffStateEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserAddressServiceTest {

  @Autowired
  UserAddressService service;

  @Test
  public void getAddress() {
    service.insertUser(
        UserAddress.builder()
            .address("icbc")
            .userId(1L)
            .state(OnOffStateEnum.ON.getCode())
            .build()
    );
    Assert.assertEquals(1, service.getAddress(1L).size());
    Assert.assertEquals("icbc", service.getAddress(1L).get(0));
  }
}

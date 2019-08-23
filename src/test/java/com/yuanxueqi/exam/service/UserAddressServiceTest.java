package com.yuanxueqi.exam.service;

import java.util.List;

import com.yuanxueqi.exam.data.Account;
import com.yuanxueqi.exam.data.Currency;
import com.yuanxueqi.exam.data.UserAddress;
import com.yuanxueqi.exam.data.rep.enums.RespDescEnum;
import com.yuanxueqi.exam.data.req.OpenAccountParam;
import com.yuanxueqi.exam.data.req.UserAddressParam;
import com.yuanxueqi.exam.data.req.UserParam;
import com.yuanxueqi.exam.enums.OnOffStateEnum;
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
public class UserAddressServiceTest {

  @Autowired
  UserAddressService service;
  @Autowired
  AccountService accountService;
  @Autowired
  UserService userService;

  @Autowired
  CurrencyService currencyService;

  @Test
  public void b_getAddress() {
    Account account = (Account) accountService.getAccount(1L, "btc").getData();
    List<UserAddress> userAddresses = (List<UserAddress>) service.getAddress(1L).getData();
    Assert.assertTrue(userAddresses.size() > 0);
    Assert.assertEquals(account.getAddress(), userAddresses.get(0).getAddress());
  }

  @Test
  public void a_inert() {
    currencyService.insertCurrency(
        Currency.builder()
            .currencyId(100L)
            .name("btc")
            .state(OnOffStateEnum.ON.getCode())
            .build()
    );
    userService.insert(UserParam.builder()
        .name("a")
        .phone("2")
        .build());
    accountService.openAccount(OpenAccountParam
        .builder()
        .currencyName("btc")
        .userId(1L)
        .build()).getData();
    Account account = (Account) accountService.getAccount(1L, "btc").getData();
    RespDescEnum respDescEnum = (RespDescEnum) service.insertUserAddress(
        UserAddressParam.builder()
            .address(account.getAddress())
            .userId(1L)
            .build()).getData();
    Assert.assertEquals(RespDescEnum.SUCCESS, respDescEnum);
  }
}

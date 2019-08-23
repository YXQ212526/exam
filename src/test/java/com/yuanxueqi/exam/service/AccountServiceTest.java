package com.yuanxueqi.exam.service;

import java.math.BigDecimal;
import java.util.List;
import com.yuanxueqi.exam.data.Account;
import com.yuanxueqi.exam.data.Currency;
import com.yuanxueqi.exam.data.UpdateBalance;
import com.yuanxueqi.exam.data.rep.enums.RespDescEnum;
import com.yuanxueqi.exam.data.req.OpenAccountParam;
import com.yuanxueqi.exam.data.req.UserParam;
import com.yuanxueqi.exam.enums.AccountEnum;
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
public class AccountServiceTest {

  @Autowired
  AccountService accountService;
  @Autowired
  UserService userService;
  @Autowired
  CurrencyService currencyService;

  @Test
  public void a_open() {
    currencyService.insertCurrency(
        Currency.builder()
            .currencyId(100L)
            .name("btc")
            .state(OnOffStateEnum.ON.getCode())
            .build()
    );
    userService.insert(UserParam.builder()
        .name("a")
        .phone("1")
        .build());
    RespDescEnum descEnum = (RespDescEnum) accountService.openAccount(OpenAccountParam
        .builder()
        .currencyName("btc")
        .userId(1L)
        .build()).getData();
    Assert.assertEquals(RespDescEnum.SUCCESS, descEnum);

  }

  @Test
  public void get() {
    Account account = (Account) accountService.getAccount(1L, "btc").getData();
    Assert.assertEquals(AccountEnum.NORMAL.getCode(), account.getState());
    Assert.assertTrue(account.getBalance().compareTo(BigDecimal.TEN) == 0);
  }

  @Test
  public void getAll() {
    List<Account> list = (List<Account>) accountService.getAllAccount(1L ).getData();
    Assert.assertTrue(1 == list.size());
  }

  @Test
  public void b_updateBalance() {
    RespDescEnum respDescEnum = (RespDescEnum) accountService.updateBalance(UpdateBalance.builder()
        .userId(1L)
        .currencyName("btc")
        .amount(BigDecimal.TEN)
        .build()).getData();
    Assert.assertEquals(RespDescEnum.SUCCESS, respDescEnum);

  }
}
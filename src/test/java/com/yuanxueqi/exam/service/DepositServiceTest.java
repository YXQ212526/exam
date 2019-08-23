package com.yuanxueqi.exam.service;

import java.math.BigDecimal;
import java.util.List;

import com.yuanxueqi.exam.data.Account;
import com.yuanxueqi.exam.data.Currency;
import com.yuanxueqi.exam.data.Deposit;
import com.yuanxueqi.exam.data.UserAddress;
import com.yuanxueqi.exam.data.rep.DepositEntity;
import com.yuanxueqi.exam.data.rep.enums.RespDescEnum;
import com.yuanxueqi.exam.data.req.DepositParam;
import com.yuanxueqi.exam.data.req.OpenAccountParam;
import com.yuanxueqi.exam.data.req.UserAddressParam;
import com.yuanxueqi.exam.data.req.UserParam;
import com.yuanxueqi.exam.enums.DepositStateEnum;
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
public class DepositServiceTest {

  @Autowired
  DepositService depositService;
  @Autowired
  UserService userService;
  @Autowired
  UserAddressService userAddressService;
  @Autowired
  CurrencyService currencyService;
  @Autowired
  AccountService accountService;


  @Test
  public void createDeposit() {
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
    accountService.openAccount(OpenAccountParam
        .builder()
        .currencyName("btc")
        .userId(1L)
        .build()).getData();
    Account account = (Account) accountService.getAccount(1L, "btc").getData();
    userAddressService.insertUserAddress(
        UserAddressParam.builder()
            .address(account.getAddress())
            .userId(1L)
            .build());

    RespDescEnum respDescEnum = (RespDescEnum) depositService.createDepositOrder(
        DepositParam.builder()
            .address(account.getAddress())
            .amount(BigDecimal.ONE)
            .confirm(1)
            .currencyName("btc")
            .txHash(1L)
            .height(1L)
            .userId(1L)
            .build()).getData();
    Assert.assertEquals(RespDescEnum.SUCCESS, respDescEnum);
  }

  @Test
  public void getDeposit() {
    Account account = (Account) accountService.getAccount(1L, "btc").getData();
    List<DepositEntity> deposits = (List<DepositEntity>) depositService.getDepositRecord(1L).getData();
    Assert.assertEquals(1, deposits.size());
    Assert.assertEquals(account.getAddress(), deposits.get(0).getAddress());
    Assert.assertTrue(deposits.get(0).getAmount().compareTo(BigDecimal.ONE) == 0);
    Assert.assertEquals("btc", deposits.get(0).getCurrencyName());
    Assert.assertEquals(1, deposits.get(0).getConfirm());
    Assert.assertTrue(1L == deposits.get(0).getHeight());
    Assert.assertEquals(DepositStateEnum.WAITING.getCode(), deposits.get(0).getState());
    Assert.assertTrue(1L == deposits.get(0).getTxHash());

  }

  @Test
  public void updateConfirm() {
    Account accountBefore = (Account) accountService.getAccount(1L, "btc").getData();
    depositService.updateConfirm(
        DepositParam.builder()
            .address(accountBefore.getAddress())
            .amount(BigDecimal.ONE)
            .confirm(3)
            .currencyName("btc")
            .txHash(1L)
            .height(1L)
            .userId(1L)
            .build()
    );
    List<DepositEntity> deposits1 = (List<DepositEntity>) depositService.getDepositRecord(1L).getData();
    Assert.assertEquals(3, deposits1.get(0).getConfirm());
    Assert.assertEquals(DepositStateEnum.WAITING.getCode(), deposits1.get(0).getState());

    depositService.updateConfirm(
        DepositParam.builder()
            .address(accountBefore.getAddress())
            .amount(BigDecimal.ONE)
            .confirm(6)
            .currencyName("btc")
            .txHash(1L)
            .height(1L)
            .userId(1L)
            .build()
    );
    List<DepositEntity> deposits2 = (List<DepositEntity>) depositService.getDepositRecord(1L).getData();
    Assert.assertEquals(6, deposits2.get(0).getConfirm());
    Assert.assertEquals(DepositStateEnum.ACCOUNT_IN.getCode(), deposits2.get(0).getState());
    Account accountAfter = (Account) accountService.getAccount(1L, "btc").getData();
    Assert.assertTrue(accountAfter.getBalance().compareTo(BigDecimal.ONE) == 0);
  }
}

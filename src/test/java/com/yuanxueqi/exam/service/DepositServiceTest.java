package com.yuanxueqi.exam.service;

import java.math.BigDecimal;

import com.yuanxueqi.exam.data.req.DepositParam;
import com.yuanxueqi.exam.enums.DepositStateEnum;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DepositServiceTest {

  @Autowired
  DepositService depositService;


//  public void createDeposit() {
//    depositService.createDepositOrder(
//        DepositParam.builder()
//            .address("icbc")
//            .amount(BigDecimal.ONE)
//            .confirm(1)
//            .currencyName("btc")
//            .txHash(1L)
//            .height(1L)
//            .userId(1L)
//            .build()
//    );
//  }

  @Test
  public void getDeposit() {
 //   createDeposit();
    Assert.assertEquals(1, depositService.getDepositRecord(1L).size());
    Assert.assertEquals("icbc", depositService.getDepositRecord(1L).get(0).getAddress());
    Assert.assertTrue(depositService.getDepositRecord(1L).get(0).getAmount().compareTo(BigDecimal.ONE)==0);
    Assert.assertEquals("btc", depositService.getDepositRecord(1L).get(0).getCurrencyName());
    Assert.assertEquals(1, depositService.getDepositRecord(1L).get(0).getConfirm());
    Assert.assertTrue(1L == depositService.getDepositRecord(1L).get(0).getHeight());
    Assert.assertEquals(DepositStateEnum.WAITING.getCode(), depositService.getDepositRecord(1L).get(0).getState());
    Assert.assertTrue(1L == depositService.getDepositRecord(1L).get(0).getTxHash());

  }

  @Test
  public void updateConfirm() {
    depositService.updateConfirm(
        DepositParam.builder()
            .address("icbc")
            .amount(BigDecimal.ONE)
            .confirm(3)
            .currencyName("btc")
            .txHash(1L)
            .height(1L)
            .userId(1L)
            .build()
    );
    Assert.assertEquals(3, depositService.getDepositRecord(1L).get(0).getConfirm());
    Assert.assertEquals(DepositStateEnum.WAITING.getCode(), depositService.getDepositRecord(1L).get(0).getState());

    depositService.updateConfirm(
        DepositParam.builder()
            .address("icbc")
            .amount(BigDecimal.ONE)
            .confirm(6)
            .currencyName("btc")
            .txHash(1L)
            .height(1L)
            .userId(1L)
            .build()
    );

    Assert.assertEquals(6, depositService.getDepositRecord(1L).get(0).getConfirm());
    Assert.assertEquals(DepositStateEnum.ACCOUNT_IN.getCode(), depositService.getDepositRecord(1L).get(0).getState());
  }
}

package com.yuanxueqi.exam.service;

import com.yuanxueqi.exam.data.Currency;
import com.yuanxueqi.exam.enums.OnOffStateEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CurrencyServiceTest {

  @Autowired
  CurrencyService currencyService;

  @Test
  public void displayAllCurrencies() {
    currencyService.insertCurrency(
        Currency.builder()
            .currencyId(100L)
            .name("btc")
            .state(OnOffStateEnum.ON.getCode())
            .build());

    Assert.assertSame(1,currencyService.displayAllCurrencies().size());
    Assert.assertSame(100L,currencyService.displayAllCurrencies().get(0).getCurrencyId());
    Assert.assertEquals("btc",currencyService.displayAllCurrencies().get(0).getName());
    Assert.assertEquals(OnOffStateEnum.ON.getCode(),currencyService.displayAllCurrencies().get(0).getState());
  }


}

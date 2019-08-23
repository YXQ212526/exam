package com.yuanxueqi.exam.service;

import java.util.List;

import com.yuanxueqi.exam.data.Currency;
import com.yuanxueqi.exam.data.rep.enums.RespDescEnum;
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
public class CurrencyServiceTest {

  @Autowired
  CurrencyService currencyService;

  @Test
  public void b_displayAllCurrencies() {
    List<Currency> currencies = (List<Currency>) currencyService.displayAllCurrencies().getData();
    Assert.assertSame(1, currencies.size());
    Assert.assertSame(100L, currencies.get(0).getCurrencyId());
    Assert.assertEquals("btc", currencies.get(0).getName());
    Assert.assertEquals(OnOffStateEnum.ON.getCode(), currencies.get(0).getState());
  }

  @Test
  public void a_insertCurrency() {
    RespDescEnum respDescEnum = (RespDescEnum) currencyService.insertCurrency(
        Currency.builder()
            .currencyId(100L)
            .name("btc")
            .state(OnOffStateEnum.ON.getCode())
            .build()).getData();
    Assert.assertEquals(RespDescEnum.SUCCESS, respDescEnum);
  }

}

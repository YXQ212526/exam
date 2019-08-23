package com.yuanxueqi.exam.data;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Account {

  Long id;
  Long userId;
  String currencyName;
  BigDecimal balance;
  String address;
  int state;
}

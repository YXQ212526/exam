package com.yuanxueqi.exam.data.req;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepositParam {

  Long userId;
  String currencyName;
  Long txHash;
  String address;
  BigDecimal amount;
  Long height;
  int confirm;
}

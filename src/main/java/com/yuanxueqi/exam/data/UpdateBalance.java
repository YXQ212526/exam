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
public class UpdateBalance {

  BigDecimal amount;
  Long userId;
  String currencyName;
}

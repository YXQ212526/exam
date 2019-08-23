package com.yuanxueqi.exam.data.rep;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.yuanxueqi.exam.data.Deposit;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class DepositEntity {

  Long id;
  Long userId;
  String currencyName;
  Long txHash;
  String address;
  BigDecimal amount;
  Long height;
  int confirm;
  int state;
  Long createAt;

  public static List<DepositEntity> ToEntityList(List<Deposit> deposit) {
    return deposit.stream().map((val) -> {
     return DepositEntity.builder()
          .id(val.getId())
          .userId(val.getUserId())
          .currencyName(val.getCurrencyName())
          .txHash(val.getTxHash())
          .address(val.getAddress())
          .amount(val.getAmount())
          .height(val.getHeight())
          .confirm(val.getConfirm())
          .state(val.getState())
          .createAt(val.getCreateAt())
          .build();
    }).collect(Collectors.toList());

  }
}

package com.yuanxueqi.exam.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAddress {

  Long id;
  Long userId;
  String address;
  int state;
}

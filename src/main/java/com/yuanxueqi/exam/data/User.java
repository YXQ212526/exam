package com.yuanxueqi.exam.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class User {

  Long id;
  String name;
  String phone;
  int state;
  Long createAt;
}

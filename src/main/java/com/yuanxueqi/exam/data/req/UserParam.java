package com.yuanxueqi.exam.data.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserParam {

  String name;
  String phone;
}

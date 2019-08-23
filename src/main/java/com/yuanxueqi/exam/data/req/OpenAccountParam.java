package com.yuanxueqi.exam.data.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpenAccountParam {

  Long userId;
  String currencyName;
}

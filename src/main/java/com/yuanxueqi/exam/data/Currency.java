package com.yuanxueqi.exam.data;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Currency implements Serializable {

  private static final long serialVersionUID = 1L;
  Long currencyId;
  int state;
  String name;
}

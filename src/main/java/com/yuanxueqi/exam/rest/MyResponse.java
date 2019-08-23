package com.yuanxueqi.exam.rest;

import java.io.Serializable;

import lombok.Data;

@Data
public class MyResponse<T> implements Serializable {

  private static final long serialVersionUID = 1L;
  T data;

  public MyResponse(T data) {
    this.data = data;
  }
}

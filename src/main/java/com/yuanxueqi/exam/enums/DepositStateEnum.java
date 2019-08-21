package com.yuanxueqi.exam.enums;

import lombok.Getter;

@Getter
public enum DepositStateEnum {
  INIT(0, "初始创建"),
  WAITING(1, "等待中"),
  ACCOUNT_IN(2, "已上账");
  int code;
  String desc;

  DepositStateEnum(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public static DepositStateEnum get(int code) {
    for (DepositStateEnum state : DepositStateEnum.values()) {
      if (state.code == code) {
        return state;
      }
    }
    return null;
  }

}

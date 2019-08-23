package com.yuanxueqi.exam.enums;

import lombok.Getter;


@Getter
public enum AccountEnum {
  NORMAL(0, "正常"),
  FREEZE(1, "冻结");
  int code;
  String desc;

  AccountEnum(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public static AccountEnum get(int code) {
    for (AccountEnum state : AccountEnum.values()) {
      if (state.code == code) {
        return state;
      }
    }
    return null;
  }

}

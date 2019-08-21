package com.yuanxueqi.exam.enums;

import lombok.Getter;

@Getter
public enum OnOffStateEnum {
  ON(0, "启用"),
  OFF(1, "停用");
  int code;
  String desc;

  OnOffStateEnum(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public static OnOffStateEnum get(int code) {
    for (OnOffStateEnum state : OnOffStateEnum.values()) {
      if (state.code == code) {
        return state;
      }
    }
    return null;
  }

}

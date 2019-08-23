package com.yuanxueqi.exam.data.rep.enums;

public enum RespDescEnum {
  SUCCESS(125, "成功"),
  PARAM_NULL(126, "传入参数为空"),
  STATE_ERROR(127, "状态不正确"),
  NO_USER(128, "没有该用户"),
  NOT_BIND_ADDRESS(129, "用户未绑定该地址"),
  UNSUPPORT_CURRENCY(130, "不支持该币种"),
  NO_DEPOSIT(131, "没有该充值记录"),
  CONFIRM_ERROR(132, "确认次数不正确"),
  NO_ACCOUNT(133, "没有该账户"),
  NOT_BIND_ACCOUNT(134, "没有绑定该账户"),
  ADDRESS_ERROR(135, "地址不正确");

  int code;
  String des;

  RespDescEnum(int code, String des) {
    this.code = code;
  }
}

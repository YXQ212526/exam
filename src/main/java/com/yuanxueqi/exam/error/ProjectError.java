package com.yuanxueqi.exam.error;

public class ProjectError extends Exception {

  public String getMessage() {
    return "发生了某种错误";
  }

  public ProjectError() {
    super();
  }

  public ProjectError(String message) {
    super(message);
  }
}

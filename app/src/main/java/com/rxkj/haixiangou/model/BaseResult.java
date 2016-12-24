package com.rxkj.haixiangou.model;

/**
 * 创建时间: 2016/12/18 23:59 <br>
 * 作者: zhangbin <br>
 * 描述: 服务端相应的model基类
 */

public class BaseResult {

  /**
   * request_id : 12092061666321
   * error_code : 0
   * error_msg :
   * data : {}
   */

  private String request_id;
  private int error_code;
  private String error_msg;

  public String getRequest_id() {
    return request_id;
  }

  public void setRequest_id(String request_id) {
    this.request_id = request_id;
  }

  public int getError_code() {
    return error_code;
  }

  public void setError_code(int error_code) {
    this.error_code = error_code;
  }

  public String getError_msg() {
    return error_msg;
  }

  public void setError_msg(String error_msg) {
    this.error_msg = error_msg;
  }
}

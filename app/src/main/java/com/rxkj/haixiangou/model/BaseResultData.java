package com.rxkj.haixiangou.model;

/**
 * 接口返回基本数据结构
 *
 * @param <T> 返回数据对象
 * @author hlwang
 */
public class BaseResultData<T> extends BaseResult {

  /**
   * 返回数据对象
   */
  public T data;
}

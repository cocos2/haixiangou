package com.rxkj.haixiangou.net.callback;

/**
 * Created by kobe on 16/3/18.
 */
public interface ResponseFilter<T> {

  void doFilter(T t);
}

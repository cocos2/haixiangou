package com.rxkj.haixiangou.view;

/**
 * 创建时间: 2016/12/20 11:09 <br>
 * 作者: zhangbin <br>
 * 描述: mvp中v的接口
 */
public interface BaseView<T> {
  void showNetErrorPage();
  void setPresenter(T presenter);
}


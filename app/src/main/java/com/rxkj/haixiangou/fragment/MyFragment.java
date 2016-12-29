package com.rxkj.haixiangou.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.rxkj.haixiangou.R;

/**
 * 创建时间: 2016/12/18 23:21 <br>
 * 作者: zhangbin <br>
 * 描述: 个人中心fragment
 */
public class MyFragment extends BaseFragment {

  public MyFragment() {
  }
  public static MyFragment newInstance() {
    MyFragment fragment = new MyFragment();
    return fragment;
  }
  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_my, container, false);
  }
}
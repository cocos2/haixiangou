package com.rxkj.haixiangou.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.rxkj.haixiangou.R;

public class ShoppingCartFragment extends BaseFragment {
  public ShoppingCartFragment() {
  }
  public static ShoppingCartFragment newInstance() {
    ShoppingCartFragment fragment = new ShoppingCartFragment();
    return fragment;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_shopping_cart, container, false);
  }
}

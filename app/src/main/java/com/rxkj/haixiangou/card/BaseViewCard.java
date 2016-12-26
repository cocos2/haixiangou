package com.rxkj.haixiangou.card;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.rxkj.haixiangou.listener.InitDataListener;

/**
 * Created by zhangbin on 16/6/29.
 *
 * 所有卡片的基类
 */

public abstract class BaseViewCard<T> extends FrameLayout implements InitDataListener<T> {

  public BaseViewCard(Context context) {
    super(context);
    onCreate(context);
  }

  public BaseViewCard(Context context, AttributeSet attrs) {
    super(context, attrs);
    onCreate(context);
  }

  public BaseViewCard(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    onCreate(context);
  }

  protected void onCreate(Context context) {
    View view = LayoutInflater.from(context).inflate(onBindLayoutId(), this);
    onViewCreated(view);
  }

  protected abstract void onViewCreated(View mView);

  protected abstract int onBindLayoutId();
}

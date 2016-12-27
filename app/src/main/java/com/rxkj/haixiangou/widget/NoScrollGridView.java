package com.rxkj.haixiangou.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 创建时间: 2016/12/26 17:02 <br>
 * 作者: zhangbin <br>
 * 描述:
 */

public class NoScrollGridView extends GridView {
  public NoScrollGridView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
    super.onMeasure(widthMeasureSpec, mExpandSpec);
  }
}

package com.rxkj.haixiangou.widget.snappy;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 创建时间:   <br>
 * 作者:  <br>
 * 描述:
 */
public class HorizontalSpaceItemDecoration extends RecyclerView.ItemDecoration {
  private final int mHorizontalSpaceHeight;

  public HorizontalSpaceItemDecoration(int mVerticalSpaceHeight) {
    this.mHorizontalSpaceHeight = mVerticalSpaceHeight;
  }

  @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
      RecyclerView.State state) {
    if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
      outRect.right = mHorizontalSpaceHeight;
    }
  }
}

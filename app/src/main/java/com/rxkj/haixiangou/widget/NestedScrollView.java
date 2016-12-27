package com.rxkj.haixiangou.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义ScrollView
 */
public class NestedScrollView extends ScrollView {

  private List<OnScrollListener> onScrollListeners;

  /**
   * 主要是用在用户手指离开MyScrollView，MyScrollView还在继续滑动，我们用来保存Y的距离，然后做比较
   */
  private int lastScrollY;

  /**
   * 用于用户手指离开MyScrollView的时候获取MyScrollView滚动的Y距离，然后回调给onScroll方法中
   */
  private Handler handler = new Handler() {

    public void handleMessage(android.os.Message msg) {
      int scrollY = NestedScrollView.this.getScrollY();

      // 此时的距离和记录下的距离不相等，在隔5毫秒给handler发送消息
      if (lastScrollY != scrollY) {
        lastScrollY = scrollY;
        handler.sendMessageDelayed(handler.obtainMessage(), 5);
      }
      if (onScrollListeners != null) {
        for (OnScrollListener listner : onScrollListeners) {
          listner.onScroll(scrollY);
        }
      }
    }

    ;
  };

  public NestedScrollView(Context context) {
    super(context);
  }

  public NestedScrollView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public NestedScrollView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  /**
   * 设置滚动接口
   */
  public void setOnScrollListener(OnScrollListener onScrollListener) {
    if (onScrollListeners == null) {
      onScrollListeners = new ArrayList<NestedScrollView.OnScrollListener>();
    }
    onScrollListeners.add(onScrollListener);
  }

  private float xDistance, yDistance, lastX, lastY;

  public boolean onInterceptTouchEvent(MotionEvent ev) {
    switch (ev.getAction()) {
      case MotionEvent.ACTION_DOWN:
        xDistance = yDistance = 0f;
        lastX = ev.getX();
        lastY = ev.getY();
        break;
      case MotionEvent.ACTION_MOVE:
        final float curX = ev.getX();
        final float curY = ev.getY();
        xDistance += Math.abs(curX - lastX);
        yDistance += Math.abs(curY - lastY);
        lastX = curX;
        lastY = curY;
        if (xDistance > yDistance) {
          return false;
        }
    }
    return super.onInterceptTouchEvent(ev);
  }

  /**
   * 重写onTouchEvent， 当用户的手在MyScrollView上面的时候，
   * 直接将MyScrollView滑动的Y方向距离回调给onScroll方法中，当用户抬起手的时候，
   * MyScrollView可能还在滑动，所以当用户抬起手我们隔5毫秒给handler发送消息，在handler处理
   * MyScrollView滑动的距离
   */
  @Override public boolean onTouchEvent(MotionEvent ev) {
    if (onScrollListeners != null) {
      for (OnScrollListener listner : onScrollListeners) {
        listner.onScroll(lastScrollY = this.getScrollY());
        listner.onTouchEvent(ev);
      }
    }
    switch (ev.getAction()) {
      case MotionEvent.ACTION_UP:
        handler.sendMessageDelayed(handler.obtainMessage(), 20);
        break;
    }
    return super.onTouchEvent(ev);
  }

  /**
   * 滚动的回调接口
   */
  public interface OnScrollListener {

    /**
     * 回调方法， 返回MyScrollView滑动的Y方向距离
     */
    public void onScroll(int scrollY);

    /**
     * 回调方法， 返回MyScrollView滑动到Y方向上的位置
     */
    public void onScrollPosition(int position);

    public void onTouchEvent(MotionEvent ev);
  }

  @Override protected void onScrollChanged(int l, int t, int oldl, int oldt) {
    if (onScrollListeners != null) {
      for (OnScrollListener listner : onScrollListeners) {
        int scrollY = getScrollY();
        listner.onScrollPosition(scrollY);
      }
    }
    super.onScrollChanged(l, t, oldl, oldt);
  }
}

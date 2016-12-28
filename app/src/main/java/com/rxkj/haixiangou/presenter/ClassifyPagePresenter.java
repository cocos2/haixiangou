package com.rxkj.haixiangou.presenter;

import android.support.annotation.NonNull;
import com.rxkj.haixiangou.interf.ClassifyPageContract;
import rx.subscriptions.CompositeSubscription;

/**
 * 创建时间: 2016/12/28 15:05 <br>
 * 作者: zhangbin <br>
 * 描述:分类页面
 */

public class ClassifyPagePresenter implements ClassifyPageContract.Presenter {
  private ClassifyPageContract.View mView;
  @NonNull private CompositeSubscription mSubscriptions;

  public ClassifyPagePresenter(ClassifyPageContract.View mView) {
    this.mView = mView;
    mSubscriptions = new CompositeSubscription();
    mView.setPresenter(this);
  }

  private void getClassifyData() {

  }

  @Override public void subscribe() {
    getClassifyData();
  }

  @Override public void unsubscribe() {
    mSubscriptions.clear();
  }
}

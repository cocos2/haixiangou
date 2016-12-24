package com.rxkj.haixiangou.model;

import com.rxkj.haixiangou.util.LogUtil;
import java.io.Serializable;
import rx.Subscriber;

/**
 * Created by GanQuan on 16/4/4.
 */
public abstract class CommonSubcriber<T> extends Subscriber<T> implements Serializable {

  private static final long serialVersionUID = -3550873117087533787L;
  ErrorHandler mErrorHandler = null;

  public CommonSubcriber(ErrorHandler errorHandler) {
    mErrorHandler = errorHandler;
  }

  public CommonSubcriber() {

  }

  @Override public void onCompleted() {
    LogUtil.e("okhttp", "onCompleted");
  }

  @Override public void onError(Throwable e) {
    if (mErrorHandler != null) {
      mErrorHandler.onError();
    }

    LogUtil.e("okhttp", e.toString());
  }

  @Override public void onNext(T t) {
    if (t == null) {
      mErrorHandler.onError();
    }
  }

  public interface ErrorHandler {

    void onError();
  }
}

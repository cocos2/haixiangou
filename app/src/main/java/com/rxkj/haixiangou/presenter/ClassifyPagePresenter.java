package com.rxkj.haixiangou.presenter;

import android.support.annotation.NonNull;
import com.rxkj.haixiangou.interf.ClassifyPageContract;
import com.rxkj.haixiangou.model.BaseResultData;
import com.rxkj.haixiangou.model.ClassifyModel;
import com.rxkj.haixiangou.model.CommonSubcriber;
import com.rxkj.haixiangou.net.service.APIService;
import com.rxkj.haixiangou.net.service.NetApiService;
import com.rxkj.haixiangou.util.CollectionUtils;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
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
    Subscription mSubscription = APIService.createService(NetApiService.class)
        .getClassifyData(null)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CommonSubcriber<BaseResultData<ClassifyModel>>() {

          @Override public void onError(Throwable e) {
            mView.showNetErrorPage();
          }

          @Override public void onNext(BaseResultData<ClassifyModel> classifyModelBaseResultData) {
            if (classifyModelBaseResultData.data != null && CollectionUtils.isNotEmpty(
                classifyModelBaseResultData.data.getClassify())) {
              mView.showRvClassify(classifyModelBaseResultData.data.getClassify());
            }
          }
        });
    mSubscriptions.add(mSubscription);
  }

  @Override public void subscribe() {
    getClassifyData();
  }

  @Override public void unsubscribe() {
    mSubscriptions.clear();
  }
}

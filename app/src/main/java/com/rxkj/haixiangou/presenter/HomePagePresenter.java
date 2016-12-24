package com.rxkj.haixiangou.presenter;

import android.support.annotation.NonNull;
import com.rxkj.haixiangou.app.MyApplication;
import com.rxkj.haixiangou.interf.HomePageContract;
import com.rxkj.haixiangou.model.BaseResultData;
import com.rxkj.haixiangou.model.CommonSubcriber;
import com.rxkj.haixiangou.model.HomePageModel;
import com.rxkj.haixiangou.net.service.APIService;
import com.rxkj.haixiangou.net.service.NetApiService;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 创建时间: 2016/12/20 11:25 <br>
 * 作者: zhangbin <br>
 * 描述: 大首页presenter
 */

public class HomePagePresenter implements HomePageContract.Presenter, IBasePresenter {
  private HomePageContract.View mView;
  @NonNull private CompositeSubscription mSubscriptions;

  public HomePagePresenter(HomePageContract.View mView) {
    this.mView = mView;
    mSubscriptions = new CompositeSubscription();
    mView.setPresenter(this);
  }

  private void getHomePageData() {
    Subscription mSubscription = APIService.createService(NetApiService.class)
        .getHomePage(MyApplication.getInstance().mISharedPreferencesFactory.getHomeSign())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CommonSubcriber<BaseResultData<HomePageModel>>() {

          @Override public void onError(Throwable e) {
            mView.showNetErrorPage();
          }

          @Override public void onNext(BaseResultData<HomePageModel> homePageModelBaseResultData) {
            if (homePageModelBaseResultData.data != null) {

            }
          }
        });
    mSubscriptions.add(mSubscription);
  }

  @Override public void subscribe() {
   // getHomePageData();
  }

  @Override public void unsubscribe() {
    mSubscriptions.clear();
  }
}

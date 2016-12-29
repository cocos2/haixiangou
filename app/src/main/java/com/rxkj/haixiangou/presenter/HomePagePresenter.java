package com.rxkj.haixiangou.presenter;

import android.support.annotation.NonNull;
import com.rxkj.haixiangou.interf.HomePageContract;
import com.rxkj.haixiangou.model.BaseResultData;
import com.rxkj.haixiangou.model.CommonSubcriber;
import com.rxkj.haixiangou.model.HomePageModel;
import com.rxkj.haixiangou.net.service.APIService;
import com.rxkj.haixiangou.net.service.NetApiService;
import com.rxkj.haixiangou.util.CollectionUtils;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 创建时间: 2016/12/20 11:25 <br>
 * 作者: zhangbin <br>
 * 描述: 大首页presenter
 */

public class HomePagePresenter implements HomePageContract.Presenter {
  private HomePageContract.View mView;
  @NonNull private CompositeSubscription mSubscriptions;

  public HomePagePresenter(HomePageContract.View mView) {
    this.mView = mView;
    mSubscriptions = new CompositeSubscription();
    mView.setPresenter(this);
  }

  private void getHomePageData() {
    mSubscriptions.add(APIService.createService(NetApiService.class)
        .getHomePage(null)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CommonSubcriber<BaseResultData<HomePageModel>>() {

          @Override public void onError(Throwable e) {
            mView.showNetErrorPage();
          }

          @Override public void onNext(BaseResultData<HomePageModel> homePageModelBaseResultData) {
            if (homePageModelBaseResultData.data != null
                && homePageModelBaseResultData.data.getHomepage() != null) {
              HomePageModel.HomepageEntity dataEntity =
                  homePageModelBaseResultData.data.getHomepage();
              if (CollectionUtils.isNotEmpty(dataEntity.getBanners())) {
                mView.showBanner(homePageModelBaseResultData.data.getHomepage().getBanners());
              }
              if (CollectionUtils.isNotEmpty(dataEntity.getChannel())) {
                mView.showChannel(homePageModelBaseResultData.data.getHomepage().getChannel());
              }
              if (null != dataEntity.getRecommend()) {
                mView.showRecommend(homePageModelBaseResultData.data.getHomepage().getRecommend());
              }
              if (null != dataEntity.getRanking()) {
                mView.showRanking(homePageModelBaseResultData.data.getHomepage().getRanking());
              }
            }
          }
        }));
  }

  @Override public void subscribe() {
    getHomePageData();
  }

  @Override public void unsubscribe() {
    mSubscriptions.clear();
  }
}

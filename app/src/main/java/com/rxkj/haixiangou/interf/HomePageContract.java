package com.rxkj.haixiangou.interf;

import com.rxkj.haixiangou.model.HomePageModel;
import com.rxkj.haixiangou.presenter.IBasePresenter;
import com.rxkj.haixiangou.view.BaseView;
import java.util.List;

/**
 * 创建时间: 2016/12/18 23:21 <br>
 * 作者: zhangbin <br>
 * 描述: 首页接口
 */
public interface HomePageContract {

  interface View extends BaseView<Presenter> {
    void setProgressIndicator(boolean active);

    void showBanner(List<HomePageModel.HomepageEntity.BannersEntity> banners);

    void showChannel(List<HomePageModel.HomepageEntity.ChannelEntity> channels);

    void showRecommend(HomePageModel.HomepageEntity.RecommendEntity recommend);

    void showRanking(HomePageModel.HomepageEntity.RankingEntity ranking);

    void showLoadingHomePageError();
  }

  interface Presenter extends IBasePresenter {

  }
}

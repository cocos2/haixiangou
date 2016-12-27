package com.rxkj.haixiangou.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.rxkj.haixiangou.R;
import com.rxkj.haixiangou.card.BannerCard;
import com.rxkj.haixiangou.card.ChannelCard;
import com.rxkj.haixiangou.card.RankingCard;
import com.rxkj.haixiangou.card.RecommendCard;
import com.rxkj.haixiangou.interf.HomePageContract;
import com.rxkj.haixiangou.model.HomePageModel;
import com.rxkj.haixiangou.presenter.HomePagePresenter;
import com.rxkj.haixiangou.util.CollectionUtils;
import com.rxkj.haixiangou.util.UIUtils;
import com.rxkj.haixiangou.widget.NestedScrollView;
import com.rxkj.haixiangou.widget.TopTitleBar;
import java.util.List;

/**
 * 创建时间: 2016/12/18 23:21 <br>
 * 作者: zhangbin <br>
 * 描述: 大首页fragment
 */
public class HomeFragment extends BaseFragment implements HomePageContract.View {

  @Bind(R.id.root_content) LinearLayout mRootContent;
  @Bind(R.id.scroll) NestedScrollView mScroll;
  @Bind(R.id.title_bar) TopTitleBar mTitleBar;
  private HomePageContract.Presenter mPresenter;

  public HomeFragment() {

  }

  public static HomeFragment newInstance(String param1, String param2) {
    HomeFragment fragment = new HomeFragment();
    return fragment;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_home, container, false);
    ButterKnife.bind(this, view);
    initTitleBar();
    mScroll.setOnScrollListener(onScrollListener);
    mPresenter = new HomePagePresenter(this);
    mPresenter.subscribe();
    return view;
  }

  private void initTitleBar() {
    mTitleBar.setImmersive(true);
    mTitleBar.setLeftVisible(false);
    mTitleBar.setBackgroundColor(Color.TRANSPARENT);
    mTitleBar.setDividerVisible(false);
  }

  @Override public void setProgressIndicator(boolean active) {

  }

  @Override public void showBanner(List<HomePageModel.HomepageEntity.BannersEntity> banners) {
    if (CollectionUtils.isNotEmpty(banners)) {
      BannerCard bannerCard = new BannerCard(getContext());
      bannerCard.initViewWithData(banners);
      mRootContent.addView(bannerCard);
    }
  }

  @Override public void showChannel(List<HomePageModel.HomepageEntity.ChannelEntity> channels) {
    if (CollectionUtils.isNotEmpty(channels)) {
      ChannelCard channelCard = new ChannelCard(getContext());
      channelCard.initViewWithData(channels);
      mRootContent.addView(channelCard);
    }
  }

  @Override public void showRecommend(HomePageModel.HomepageEntity.RecommendEntity recommend) {
    if (recommend != null) {
      RecommendCard recommendCard = new RecommendCard(getContext());
      recommendCard.initViewWithData(recommend);
      mRootContent.addView(recommendCard);
    }
  }

  @Override public void showRanking(HomePageModel.HomepageEntity.RankingEntity ranking) {
    if (ranking != null) {
      RankingCard rankingCard = new RankingCard(getContext());
      rankingCard.initViewWithData(ranking);
      mRootContent.addView(rankingCard);
    }
  }

  @Override public void showLoadingHomePageError() {

  }

  @Override public void showNetErrorPage() {

  }

  @Override public void setPresenter(HomePageContract.Presenter presenter) {
    mPresenter = presenter;
  }

  private NestedScrollView.OnScrollListener onScrollListener =
      new NestedScrollView.OnScrollListener() {

        private static final int MAX_HEIGHT_VALUE = 440;

        @Override public void onScrollPosition(int position) {
          float colorPercent = (float) (position) / MAX_HEIGHT_VALUE;
          processAnimation(colorPercent);
        }

        @Override public void onScroll(int scrollY) {
        }

        @Override public void onTouchEvent(MotionEvent ev) {

        }
      };

  private void processAnimation(float colorpercent) {
    if (colorpercent <= 1) {
      if (colorpercent <= 0.5) {
        mTitleBar.setAlpha((int) (255 * (1 - colorpercent * 2)));
        mTitleBar.setDividerVisible(false);
      } else {
        mTitleBar.setAlpha((int) (255 * (2 * colorpercent - 1)));
        mTitleBar.setDividerVisible(true);
        mTitleBar.setDividerColor(Color.argb((int) (255 * colorpercent), 83, 160, 190));
      }
      mTitleBar.setBackgroundColor(Color.argb((int) (255 * colorpercent), 83, 160, 190));
    } else {
      mTitleBar.setBackgroundColor(UIUtils.getColor(R.color.title_bar_color));
    }
  }

  @Override public void onResume() {
    super.onResume();
  }

  @Override public void onPause() {
    super.onPause();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    mPresenter.unsubscribe();
    ButterKnife.unbind(this);
  }
}
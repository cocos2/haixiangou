package com.rxkj.haixiangou.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;
import com.rxkj.haixiangou.R;
import com.rxkj.haixiangou.card.BannerCard;
import com.rxkj.haixiangou.interf.HomePageContract;
import com.rxkj.haixiangou.model.HomePageModel;
import com.rxkj.haixiangou.presenter.HomePagePresenter;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * 创建时间: 2016/12/18 23:21 <br>
 * 作者: zhangbin <br>
 * 描述: 大首页fragment
 */
public class HomeFragment extends BaseFragment implements HomePageContract.View {

  @Bind(R.id.root_content) LinearLayout mRootContent;
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
    new HomePagePresenter(this);
    return view;
  }

  /**
   * 初始化 HeaderView
   */
  private View initHeaderView() {
    View headerView = View.inflate(getContext(), R.layout.home_banner_layout, null);
    BGABanner mBanner = (BGABanner) headerView.findViewById(R.id.home_banner);
    mBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
      @Override
      public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
        Picasso.with(itemView.getContext())
            .load(model)
            .placeholder(R.mipmap.holder)
            .error(R.mipmap.holder)
            .centerCrop()
            .into(itemView);
      }
    });
    mBanner.setDelegate(new BGABanner.Delegate<ImageView, String>() {
      @Override public void onBannerItemClick(BGABanner banner, ImageView imageView, String model,
          int position) {
        Toast.makeText(banner.getContext(), "点击了第" + (position + 1) + "页", Toast.LENGTH_SHORT)
            .show();
      }
    });
    return headerView;
  }

  @Override public void onResume() {
    super.onResume();
    mPresenter.subscribe();
  }

  @Override public void onPause() {
    super.onPause();
    mPresenter.unsubscribe();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override public void setProgressIndicator(boolean active) {

  }

  @Override public void showBanner(List<HomePageModel.DataEntity.BannersEntity> banners) {
    BannerCard bannerCard = new BannerCard(getContext());
    bannerCard.initViewWithData(banners);
    mRootContent.addView(bannerCard);
  }

  @Override public void showLoadingHomePageError() {

  }

  @Override public void showNetErrorPage() {

  }

  @Override public void setPresenter(HomePageContract.Presenter presenter) {
    mPresenter = presenter;
  }
}
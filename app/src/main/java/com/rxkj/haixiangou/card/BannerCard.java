package com.rxkj.haixiangou.card;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;
import com.rxkj.haixiangou.R;
import com.rxkj.haixiangou.imageloader.ImageLoader;
import com.rxkj.haixiangou.model.HomePageModel;
import com.rxkj.haixiangou.router.Router;
import java.util.List;

/**
 * 创建时间: 2016/12/26 13:58 <br>
 * 作者: zhangbin <br>
 * 描述: 首页Banner卡片
 */

public class BannerCard extends BaseViewCard<List<HomePageModel.HomepageEntity.BannersEntity>> {
  @Bind(R.id.home_banner) BGABanner mHomeBanner;

  public BannerCard(Context context) {
    super(context);
  }

  public BannerCard(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public BannerCard(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onViewCreated(View mView) {
    ButterKnife.bind(this);
  }

  @Override protected int onBindLayoutId() {
    return R.layout.card_banner_layout;
  }

  @Override public void initViewWithData(List<HomePageModel.HomepageEntity.BannersEntity> list) {
    BGABanner.Adapter adapter =
        new BGABanner.Adapter<ImageView, HomePageModel.HomepageEntity.BannersEntity>() {
          @Override public void fillBannerItem(BGABanner banner, ImageView itemView,
              HomePageModel.HomepageEntity.BannersEntity model, int position) {
            ImageLoader.getInstance().loadImage(model.getUrl(), itemView);
          }
        };
    mHomeBanner.setAutoPlayAble(true);
    mHomeBanner.setAdapter(adapter);
    mHomeBanner.setData(list, null);
    mHomeBanner.setDelegate(
        new BGABanner.Delegate<ImageView, HomePageModel.HomepageEntity.BannersEntity>() {
          @Override public void onBannerItemClick(BGABanner banner, ImageView imageView,
              HomePageModel.HomepageEntity.BannersEntity model, int position) {
            Router.route(model.getScheme(), model.getName());
          }
        });
  }
}

package com.rxkj.haixiangou.card;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.rxkj.haixiangou.R;
import com.rxkj.haixiangou.imageloader.ImageLoader;
import com.rxkj.haixiangou.model.HomePageModel;
import com.rxkj.haixiangou.router.Router;
import com.rxkj.haixiangou.util.CollectionUtils;
import com.rxkj.haixiangou.util.DensityUtil;
import com.rxkj.haixiangou.widget.snappy.HorizontalSpaceItemDecoration;
import com.rxkj.haixiangou.widget.snappy.SnappingRecyclerView;
import com.rxkj.haixiangou.widget.snappy.SnappyLinearLayoutManager;
import java.util.List;

/**
 * 创建时间: 2016/12/26 18:39 <br>
 * 作者: zhangbin <br>
 * 描述: 大首页推荐卡片
 */

public class RecommendCard extends BaseViewCard<HomePageModel.HomepageEntity.RecommendEntity> {

  @Bind(R.id.snap_recommend) SnappingRecyclerView mSnapRecommend;

  public RecommendCard(Context context) {
    super(context);
  }

  public RecommendCard(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public RecommendCard(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onViewCreated(View mView) {
    ButterKnife.bind(this);
  }

  @Override protected int onBindLayoutId() {
    return R.layout.card_recommend_layout;
  }

  @Override public void initViewWithData(final HomePageModel.HomepageEntity.RecommendEntity data) {
    if (CollectionUtils.isNotEmpty(data.getRecommend_list())) {
      int itemWidth = DensityUtil.getEquipmentWidth(getContext()) - DensityUtil.dip2px(30);
      mSnapRecommend.getLayoutParams().height = itemWidth * 9 / 16;
      BaseQuickAdapter recommendAdapter =
          new RecommendAdapter(R.layout.item_recommend, data.getRecommend_list());
      mSnapRecommend.addOnItemTouchListener(new OnItemClickListener() {
        @Override public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
          if (CollectionUtils.isNotEmpty(data.getRecommend_list())
              && data.getRecommend_list().get(position) != null) {
            Router.route(data.getRecommend_list().get(position).getScheme(),
                data.getRecommend_list().get(position).getName());
          }
        }
      });
      SnappyLinearLayoutManager itemsLayoutManager = new SnappyLinearLayoutManager(getContext());
      itemsLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
      mSnapRecommend.setLayoutManager(itemsLayoutManager);
      mSnapRecommend.addItemDecoration(new HorizontalSpaceItemDecoration(DensityUtil.dip2px(5)));
      mSnapRecommend.setAdapter(recommendAdapter);
    }
  }

  private static class RecommendAdapter extends
      BaseQuickAdapter<HomePageModel.HomepageEntity.RecommendEntity.RecommendListEntity, BaseViewHolder> {
    public RecommendAdapter(int layoutResId, List data) {
      super(layoutResId, data);
    }

    @Override protected void convert(BaseViewHolder helper,
        HomePageModel.HomepageEntity.RecommendEntity.RecommendListEntity item) {
      ImageLoader.getInstance()
          .loadImage(item.getIcon_url(), (ImageView) helper.getView(R.id.iv_recommend));
    }
  }
}

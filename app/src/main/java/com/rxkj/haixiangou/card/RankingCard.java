package com.rxkj.haixiangou.card;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.rxkj.haixiangou.R;
import com.rxkj.haixiangou.model.HomePageModel;
import com.rxkj.haixiangou.router.Router;
import com.rxkj.haixiangou.util.CollectionUtils;
import com.rxkj.haixiangou.widget.NoScrollRecycleView;
import java.util.List;

/**
 * 创建时间: 2016/12/27 18:02 <br>
 * 作者: zhangbin <br>
 * 描述: top排行榜卡片
 */

public class RankingCard extends BaseViewCard<HomePageModel.HomepageEntity.RankingEntity> {
  @Bind(R.id.rv_ranking) NoScrollRecycleView mRecyclerView;

  public RankingCard(Context context) {
    super(context);
  }

  public RankingCard(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public RankingCard(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onViewCreated(View mView) {
    ButterKnife.bind(this);
  }

  @Override protected int onBindLayoutId() {
    return R.layout.card_ranking_layout;
  }

  @Override public void initViewWithData(final HomePageModel.HomepageEntity.RankingEntity data) {
    if (CollectionUtils.isNotEmpty(data.getRanking_list())) {
      BaseQuickAdapter rnakingAdapter =
          new RnakingAdapter(R.layout.item_ranking, data.getRanking_list());
      mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
        @Override public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
          if (CollectionUtils.isNotEmpty(data.getRanking_list())
              && data.getRanking_list().get(position) != null) {
            Router.route(data.getRanking_list().get(position).getScheme(),
                data.getRanking_list().get(position).getName());
          }
        }
      });
      mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()){
        @Override public boolean canScrollVertically() {
          return false;
        }
      });
      mRecyclerView.addItemDecoration(
          new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
      mRecyclerView.setAdapter(rnakingAdapter);
    }
  }

  private static class RnakingAdapter extends
      BaseQuickAdapter<HomePageModel.HomepageEntity.RankingEntity.RankingListEntity, BaseViewHolder> {
    public RnakingAdapter(int layoutResId, List data) {
      super(layoutResId, data);
    }

    @Override protected void convert(BaseViewHolder helper,
        HomePageModel.HomepageEntity.RankingEntity.RankingListEntity item) {
      helper.setText(R.id.tv_name, item.getName());
    }
  }
}

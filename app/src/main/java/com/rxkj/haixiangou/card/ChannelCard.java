package com.rxkj.haixiangou.card;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
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
import com.rxkj.haixiangou.widget.NoScrollRecycleView;
import java.util.List;

/**
 * 创建时间: 2016/12/26 16:58 <br>
 * 作者: zhangbin <br>
 * 描述: 大首页频道卡片
 */

public class ChannelCard extends BaseViewCard<List<HomePageModel.HomepageEntity.ChannelEntity>> {
  @Bind(R.id.rv_channel) NoScrollRecycleView mRecyclerView;

  public ChannelCard(Context context) {
    super(context);
  }

  public ChannelCard(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public ChannelCard(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onViewCreated(View mView) {
    ButterKnife.bind(this);
  }

  @Override protected int onBindLayoutId() {
    return R.layout.card_channel_layout;
  }

  @Override
  public void initViewWithData(final List<HomePageModel.HomepageEntity.ChannelEntity> list) {
    BaseQuickAdapter channelAdapter = new ChannelAdapter(R.layout.item_channel, list);
    mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
      @Override public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
        HomePageModel.HomepageEntity.ChannelEntity channelEntity = list.get(position);
        if (channelEntity != null) {
          Router.route(channelEntity.getScheme(), channelEntity.getName());
        }
      }
    });
    mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
    mRecyclerView.setAdapter(channelAdapter);
  }

  private static class ChannelAdapter
      extends BaseQuickAdapter<HomePageModel.HomepageEntity.ChannelEntity, BaseViewHolder> {
    public ChannelAdapter(int layoutResId, List data) {
      super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomePageModel.HomepageEntity.ChannelEntity item) {
      helper.setText(R.id.tv_channel_name, item.getName());
      ImageLoader.getInstance()
          .loadImage(item.getIcon_url(), (ImageView) helper.getView(R.id.iv_channel));
    }
  }
}
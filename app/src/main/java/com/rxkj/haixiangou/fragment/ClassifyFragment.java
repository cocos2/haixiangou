package com.rxkj.haixiangou.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.rxkj.haixiangou.R;
import com.rxkj.haixiangou.interf.ClassifyPageContract;
import com.rxkj.haixiangou.model.ClassifyModel;
import com.rxkj.haixiangou.presenter.ClassifyPagePresenter;
import com.rxkj.haixiangou.widget.TopTitleBar;
import java.util.List;

/**
 * 创建时间: 2016/12/18 23:21 <br>
 * 作者: zhangbin <br>
 * 描述: 分类fragment
 */
public class ClassifyFragment extends BaseFragment
    implements ClassifyPageContract.View, BaseQuickAdapter.RequestLoadMoreListener,
    SwipeRefreshLayout.OnRefreshListener {

  @Bind(R.id.rv_classify) RecyclerView mRvClassify;
  @Bind(R.id.swipeLayout) SwipeRefreshLayout mSwipeLayout;
  @Bind(R.id.title_bar) TopTitleBar mTitleBar;
  private ClassifyPageContract.Presenter mPresenter;

  public ClassifyFragment() {
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {

    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_classify, container, false);
    ButterKnife.bind(this, view);
    initTitleBar();
    mSwipeLayout.setOnRefreshListener(this);
    mSwipeLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
    mRvClassify.setLayoutManager(new LinearLayoutManager(getContext()));
    mPresenter = new ClassifyPagePresenter(this);
    mPresenter.subscribe();
    return view;
  }

  private void initTitleBar() {
    mTitleBar.setImmersive(true);
  }

  @Override public void onRefresh() {

  }

  @Override public void onLoadMoreRequested() {

  }

  @Override public void setProgressIndicator(boolean active) {

  }

  @Override public void showRvClassify(List<ClassifyModel> classifyModels) {
    ClassifyAdapter classifyAdapter = new ClassifyAdapter(R.layout.item_classify, classifyModels);
    classifyAdapter.setOnLoadMoreListener(this);
    classifyAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
    mRvClassify.setAdapter(classifyAdapter);
    mRvClassify.addOnItemTouchListener(new OnItemClickListener() {
      @Override public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view,
          final int position) {
        // TODO: 16/12/28 跳转到列表页
      }
    });
  }

  @Override public void showLoadingHomePageError() {

  }

  @Override public void showNetErrorPage() {

  }

  @Override public void setPresenter(ClassifyPageContract.Presenter presenter) {

  }

  private static class ClassifyAdapter extends BaseQuickAdapter<ClassifyModel, BaseViewHolder> {
    public ClassifyAdapter(int layoutResId, List data) {
      super(layoutResId, data);
    }

    @Override protected void convert(BaseViewHolder baseViewHolder, ClassifyModel classifyModel) {

    }
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }
}

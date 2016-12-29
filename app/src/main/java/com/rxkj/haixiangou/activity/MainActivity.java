package com.rxkj.haixiangou.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.rxkj.haixiangou.R;
import com.rxkj.haixiangou.fragment.ClassifyFragment;
import com.rxkj.haixiangou.fragment.HomeFragment;
import com.rxkj.haixiangou.fragment.MyFragment;
import com.rxkj.haixiangou.fragment.ShoppingCartFragment;
import com.rxkj.haixiangou.model.TabModel;
import java.util.ArrayList;

public class MainActivity extends BaseActivity {

  @Bind(R.id.tab_layout) CommonTabLayout mTabLayout;
  @Bind(R.id.fragment_container) FrameLayout mFragmentContainer;

  private String[] mTabs = { "首页", "消息", "联系人", "更多" };
  private int[] mIconUnselectIds = {
      R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect, R.mipmap.tab_contact_unselect,
      R.mipmap.tab_more_unselect
  };
  private int[] mIconSelectIds = {
      R.mipmap.tab_home_select, R.mipmap.tab_speech_select, R.mipmap.tab_contact_select,
      R.mipmap.tab_more_select
  };
  private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
  private final ArrayList<Fragment> mFragments = new ArrayList<Fragment>();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    initTab();
  }

  private void initTab() {
    mFragments.add(HomeFragment.newInstance());
    mFragments.add(ClassifyFragment.newInstance());
    mFragments.add(ShoppingCartFragment.newInstance());
    mFragments.add(MyFragment.newInstance());

    for (int i = 0; i < mTabs.length; i++) {
      mTabEntities.add(new TabModel(mTabs[i], mIconSelectIds[i], mIconUnselectIds[i]));
    }

    mTabLayout.setTabData(mTabEntities, this, R.id.fragment_container, mFragments);
    mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
      @Override public void onTabSelect(int position) {
        mTabLayout.setCurrentTab(position);
      }

      @Override public void onTabReselect(int position) {
        if (position == 0) {
        }
      }
    });
  }
}

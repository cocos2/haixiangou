package com.rxkj.haixiangou.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.rxkj.haixiangou.R;
import com.rxkj.haixiangou.model.TabModel;
import java.util.ArrayList;

public class MainActivity extends BaseActivity {

  @Bind(R.id.tab_layout) CommonTabLayout mTabLayout;

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

  private Fragment[] mFragments;
  private FragmentManager mFragmentManager;
  private FragmentTransaction mFragmentTransaction;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    initBottomTab();
    initFragment();
  }

  private void initFragment() {
    mFragments = new Fragment[4];
    mFragmentManager = getSupportFragmentManager();
    mFragments[0] = mFragmentManager.findFragmentById(R.id.frag_home);
    mFragments[1] = mFragmentManager.findFragmentById(R.id.frag_classify);
    mFragments[2] = mFragmentManager.findFragmentById(R.id.frag_shopping_cart);
    mFragments[3] = mFragmentManager.findFragmentById(R.id.frag_my);
  }

  private void initBottomTab() {
    for (int i = 0; i < mTabs.length; i++) {
      mTabEntities.add(new TabModel(mTabs[i], mIconSelectIds[i], mIconUnselectIds[i]));
    }
    mTabLayout.setTabData(mTabEntities);
    mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
      @Override public void onTabSelect(int position) {
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.hide(mFragments[0])
            .hide(mFragments[1])
            .hide(mFragments[2])
            .hide(mFragments[3]);
        mFragmentTransaction.show(mFragments[position]).commit();

      }

      @Override public void onTabReselect(int position) {
        if (position == 0) {
        }
      }
    });
  }
}

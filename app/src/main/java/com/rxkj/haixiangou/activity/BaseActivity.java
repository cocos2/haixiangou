package com.rxkj.haixiangou.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.rxkj.haixiangou.R;
import com.rxkj.haixiangou.app.AppManager;
import com.rxkj.haixiangou.util.DensityUtil;
import com.rxkj.haixiangou.util.UIUtils;
import com.rxkj.haixiangou.widget.LoadingProgressBar;
import com.rxkj.haixiangou.widget.SystemBarTintManager;
import com.rxkj.haixiangou.widget.TitleBarHelper;
import com.rxkj.haixiangou.widget.TopTitleBar;

import static com.rxkj.haixiangou.util.ApiVersion.hasKitKat;
import static com.rxkj.haixiangou.util.ApiVersion.hasLollipop;

/**
 * 创建时间: 2016/12/18 23:21 <br>
 * 作者: zhangbin <br>
 * 描述: 所有Activity基类
 */

public class BaseActivity extends FragmentActivity {
  protected int mTitleBarHeight = 47;
  /**
   * 模态对话框
   */
  public LoadingProgressBar mLoading;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mTitleBarHeight = DensityUtil.px2dip(this, getResources().getDimension(R.dimen.title_height));
    AppManager.getInst().pushActivity(this);
    initTintTitleBar();
    mLoading = new LoadingProgressBar(this);
  }

  /**
   * 初始化沉浸式状态栏
   */
  private void initTintTitleBar() {
    SystemBarTintManager tintManager = new SystemBarTintManager(this);
    // enable status bar tint
    tintManager.setStatusBarTintEnabled(true);
    // enable navigation bar tint
    tintManager.setNavigationBarTintEnabled(true);
    tintManager.setStatusBarTintResource(getResources().getColor(R.color.title_bar_color));
    if (hasKitKat() && !hasLollipop()) {
      // 透明状态栏
      getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      // 透明导航栏
      // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    } else if (hasLollipop()) {
      Window window = getWindow();
      window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
          | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
      window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
          // | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
          | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      window.setStatusBarColor(UIUtils.getColor(this, R.color.title_bar_color));
    }
  }

  protected void initTitleBar(TopTitleBar mTitlebar) {
    new TitleBarHelper(this, mTitlebar).init();
  }

  @Override protected void onDestroy() {

    AppManager.getInst().popActivity();
    super.onDestroy();
  }
}

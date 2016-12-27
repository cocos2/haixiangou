package com.rxkj.haixiangou.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.rxkj.haixiangou.R;
import com.rxkj.haixiangou.app.AppManager;
import com.rxkj.haixiangou.router.ActivityIntentFactory;
import com.rxkj.haixiangou.router.IntentListener;
import com.rxkj.haixiangou.util.DensityUtil;
import com.rxkj.haixiangou.util.UIUtils;
import com.rxkj.haixiangou.widget.LoadingProgressBar;
import com.rxkj.haixiangou.widget.SystemBarTintManager;
import com.rxkj.haixiangou.widget.TitleBarHelper;
import com.rxkj.haixiangou.widget.TopTitleBar;
import java.io.File;

import static com.rxkj.haixiangou.util.ApiVersion.hasKitKat;
import static com.rxkj.haixiangou.util.ApiVersion.hasLollipop;

/**
 * 创建时间: 2016/12/18 23:21 <br>
 * 作者: zhangbin <br>
 * 描述: 所有Activity基类
 */

public class BaseActivity extends FragmentActivity implements IntentListener {
  protected int mTitleBarHeight = 47;
  /**
   * 模态对话框
   */
  public LoadingProgressBar mLoading;
  private IntentListener mIntentListener;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mTitleBarHeight = DensityUtil.px2dip(getResources().getDimension(R.dimen.title_height));
    AppManager.getInst().pushActivity(this);
    initTintTitleBar();
    mLoading = new LoadingProgressBar(this);
    mIntentListener = new ActivityIntentFactory(this);
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
      window.setStatusBarColor(UIUtils.getColor(R.color.title_bar_color));
    }
  }

  protected void initTitleBar(TopTitleBar mTitlebar) {
    new TitleBarHelper(this, mTitlebar).init();
  }

  /**
   * 往容器里面添加fragment
   */
  public void addFragment(Fragment fragment) {
    mIntentListener.addFragment(fragment);
  }

  /**
   * 替换容器里面的fragment
   */
  public void replaceFragment(Fragment newFragment) {
    mIntentListener.replaceFragment(newFragment);
  }

  /**
   * fragment替换
   *
   * @param newFragment 碎片
   * @param addToBackStack 是否保存堆栈信息
   */
  public void replaceFragment(Fragment newFragment, boolean addToBackStack) {
    mIntentListener.replaceFragment(newFragment, addToBackStack);
  }

  /**
   * fragment替换
   *
   * @param contentID 容器ID
   * @param newFragment 碎片
   * @param addToBackStack 是否保存堆栈信息
   */
  public void replaceFragment(int contentID, Fragment newFragment, boolean addToBackStack) {
    mIntentListener.replaceFragment(contentID, newFragment, addToBackStack);
  }

  public void goToView(String path) {
    mIntentListener.goToView(path);
  }

  /**
   * 去裁剪图片
   *
   * @param path 图片地址
   * @param requestUri 裁剪回调地址
   * @param size 裁剪大小
   */
  public void goToCropImage(String path, Uri requestUri, int size) {
    mIntentListener.goToCropImage(path, requestUri, size);
  }

  /**
   * 通过地址查看图片
   *
   * @param path 图片地址
   */
  public void goToView(String path, Class<?> cls) {
    mIntentListener.goToView(path, cls);
  }

  /**
   * 单纯的页面跳转
   *
   * @param cls 跳转的页面
   */
  public void goToOthers(Class<?> cls) {
    mIntentListener.goToOthers(cls);
  }

  /**
   * 页面跳转并关闭当前页面
   *
   * @param cls 跳转的页面
   */
  public void goToOthersF(Class<?> cls) {
    mIntentListener.goToOthersF(cls);
  }

  /**
   * 带参数的页面跳转
   *
   * @param cls 跳转的页面
   * @param bundle 参数
   */
  public void goToOthers(Class<?> cls, Bundle bundle) {
    mIntentListener.goToOthers(cls, bundle);
  }

  /**
   * 带参数的页面跳转并关闭当前页面
   *
   * @param cls 跳转的页面
   * @param bundle 参数
   */
  public void goToOthersF(Class<?> cls, Bundle bundle) {
    mIntentListener.goToOthersF(cls, bundle);
  }

  /**
   * 带回调的页面跳转
   *
   * @param cls 跳转的页面
   * @param bundle 参数
   * @param requestCode 请求码
   */
  public void goToOthersForResult(Class<?> cls, Bundle bundle, int requestCode) {
    mIntentListener.goToOthersForResult(cls, bundle, requestCode);
  }

  /**
   * 设置回调
   *
   * @param cls 回调的页面
   * @param bundle 参数
   * @param resultCode 返回码
   */
  public void backForResult(Class<?> cls, Bundle bundle, int resultCode) {
    mIntentListener.backForResult(cls, bundle, resultCode);
  }

  /**
   * 跳转到网页
   *
   * @param url 网页地址
   */
  public void goToWeb(String url) {
    mIntentListener.goToWeb(url);
  }

  /**
   * 打电话
   *
   * @param telePhoneNum 电话号码
   */
  public void goToCall(String telePhoneNum) {
    mIntentListener.goToCall(telePhoneNum);
  }

  public void goToSms(String smsContent) {
    mIntentListener.goToSms(smsContent);
  }

  public void goToSms(String phone, String smsContent) {
    mIntentListener.goToSms(phone, smsContent);
  }

  /**
   * 安装应用
   */
  public void installApp(File file) {
    mIntentListener.installApp(file);
  }

  public void goToPhoto(File file) {
    mIntentListener.goToPhoto(file);
  }

  public void goToGallery() {
    mIntentListener.goToGallery();
  }

  @Override protected void onDestroy() {

    AppManager.getInst().popActivity();
    super.onDestroy();
  }
}

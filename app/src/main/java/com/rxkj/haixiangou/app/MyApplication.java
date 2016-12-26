package com.rxkj.haixiangou.app;

import android.app.Application;
import com.rxkj.haixiangou.imageloader.ImageLoader;
import com.rxkj.haixiangou.imageloader.ImageOptions;
import com.rxkj.haixiangou.sharepref.BaseSharedPreferences;
import com.rxkj.haixiangou.sharepref.ISharedPreferencesFactory;

/**
 * 创建时间: 2016/12/18 23:17 <br>
 * 作者: zhangbin <br>
 * 描述: 应用程序入口
 */

public class MyApplication extends Application {
  private static MyApplication sInstance = null;
  public ISharedPreferencesFactory mISharedPreferencesFactory;

  @Override public void onCreate() {
    sInstance = this;
    mISharedPreferencesFactory = new BaseSharedPreferences(this);
    ImageLoader.init(this,new ImageOptions());
    super.onCreate();
  }

  public static MyApplication getInstance() {
    return sInstance;
  }
}

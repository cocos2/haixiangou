package com.rxkj.haixiangou.sharepref;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * sharedPreferences操作工厂
 *
 * @author wanghl-a
 */
public interface ISharedPreferencesFactory {
  String ACCESS_TOKEN = "access_token";
  String HOME_SIGN = "home_sign";

  SharedPreferences getSharedPreferences();

  Editor getEditor();

  /**
   * 设置AccessToken
   */
  void setAccessToken(String accessToken);

  /**
   * 获取AccessToken
   */
  String getAccessToken();

  /**
   * 首页sign存储
   */
  void setHomeSign(String homeSign);

  /**
   * 首页sign获取
   */
  String getHomeSign();
}

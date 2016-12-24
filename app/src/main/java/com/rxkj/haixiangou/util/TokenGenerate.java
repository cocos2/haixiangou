package com.rxkj.haixiangou.util;

import android.text.TextUtils;
import com.rxkj.haixiangou.app.MyApplication;

/**
 * Created by kobe on 16/7/13.
 */
public class TokenGenerate {

  public static final String TOKEN = "hxg_token";

  public static String getToken() {
    String access_token = MyApplication.getInstance().mISharedPreferencesFactory.getAccessToken();
    StringBuilder sb = new StringBuilder();
    if (!TextUtils.isEmpty(access_token)) {
      sb.append(TOKEN);
      sb.append("=");
      sb.append(access_token);
    }
    return sb.toString();
  }
}

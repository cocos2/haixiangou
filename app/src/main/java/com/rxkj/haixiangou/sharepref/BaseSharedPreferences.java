package com.rxkj.haixiangou.sharepref;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * SharedPreferences数据存取工厂
 *
 * @author hlwang
 */
public class BaseSharedPreferences implements ISharedPreferencesFactory {
  private SharedPreferences sp;

  private Editor editor;

  public BaseSharedPreferences(Context context) {
    sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
    editor = sp.edit();
  }

  public SharedPreferences getSharedPreferences() {
    return sp;
  }

  public Editor getEditor() {
    return editor;
  }

  public void setAccessToken(String accessToken) {
    editor.putString(ACCESS_TOKEN, accessToken);
    editor.commit();
  }

  public String getAccessToken() {
    return sp.getString(ACCESS_TOKEN, null);
  }

  @Override public void setHomeSign(String homeSign) {
    editor.putString(HOME_SIGN, homeSign);
    editor.commit();
  }

  @Override public String getHomeSign() {
    return sp.getString(HOME_SIGN, null);
  }
}
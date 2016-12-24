package com.rxkj.haixiangou.util;

import android.os.Build;

/**
 * 创建时间: 2016/12/18 23:40 <br>
 * 作者: zhangbin <br>
 * 描述: sdk版本校验
 */

public class ApiVersion {
  public static boolean hasKitKat() {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
  }

  public static boolean hasLollipop() {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
  }
}

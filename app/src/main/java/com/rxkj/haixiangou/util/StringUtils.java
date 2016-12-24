package com.rxkj.haixiangou.util;

import android.text.TextUtils;

/**
 * 创建时间: 2016/12/20 13:44 <br>
 * 作者: zhangbin <br>
 * 描述: 字符串工具类
 */

public class StringUtils {
  /**
   * 判断字符串是否为空
   *
   * @param text 文字
   */
  public static boolean isEmpty(String text) {
    return TextUtils.isEmpty(text);
  }

  public static boolean isNotEmpty(String text) {
    return !isEmpty(text);
  }

  /**
   * 修正字符串
   *
   * @param text 文字
   */
  public static String trim(String text) {
    return isEmpty(text) ? "" : text.trim();
  }

}

package com.rxkj.haixiangou.router;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.rxkj.haixiangou.activity.BaseActivity;
import com.rxkj.haixiangou.activity.MainActivity;
import com.rxkj.haixiangou.util.ConstantUtil;
import com.rxkj.haixiangou.util.LogUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import static com.rxkj.haixiangou.router.UrlSchemeFields.HOME_PAGE;

/**
 * 创建时间: 2016/12/24 14:41 <br>
 * 作者: zhangbin <br>
 * 描述: 路由中心
 */

public class Router {
  // url解码
  @Nullable public static String urlDecode(String url) {
    if (url == null) {
      return null;
    }
    String strUrl;
    try {
      strUrl = URLDecoder.decode(url, "UTF-8");
      return strUrl;
    } catch (UnsupportedEncodingException | IllegalArgumentException e) {
      LogUtil.e(e.getMessage());
    }
    return null;
  }

  /**
   * 去掉url中的路径，留下请求参数部分
   *
   * @param strURL url地址
   * @return url请求参数部分
   */
  private static String TruncateUrlPage(String strURL) {
    if (strURL == null) {
      return null;
    }
    String strAllParam = null;
    strURL = strURL.trim();
    String[] arrSplit = strURL.split("[?]");
    if (strURL.length() > 1) {
      if (arrSplit.length > 1) {
        if (arrSplit[1] != null) {
          strAllParam = arrSplit[1];
        }
      }
    }

    return strAllParam;
  }

  /**
   * 解析出url参数中的键值对 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
   *
   * @param strUrlParam url地址
   * @return url请求参数部分
   */
  public static Map<String, String> parseParams(String strUrlParam) {
    Map<String, String> mapRequest = new HashMap<String, String>();
    if (strUrlParam == null) {
      return mapRequest;
    }
    String[] arrSplit = strUrlParam.split("[&]");
    for (String strSplit : arrSplit) {
      String[] arrSplitEqual = null;
      arrSplitEqual = strSplit.split("[=]");
      // 解析出键值
      if (arrSplitEqual.length > 1) {
        // 正确解析
        mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
      } else {
        if (arrSplitEqual[0] != "") {
          // 只有参数没有值，不加入
          mapRequest.put(arrSplitEqual[0], "");
        }
      }
    }
    return mapRequest;
  }

  /**
   * 解析出url参数中的键值对 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
   *
   * @param URL url地址
   * @return url请求参数部分
   */
  public static Map<String, String> URLRequest(String URL) {
    Map<String, String> mapRequest = new HashMap<>();
    String strUrlParam = TruncateUrlPage(URL);
    if (strUrlParam == null) {
      return mapRequest;
    }
    return parseParams(strUrlParam);
  }

  /**
   * 解析出url请求的路径，包括页面
   *
   * @param strURL url地址
   * @return url路径
   */
  public static String getSchemeWithoutParams(String strURL) {
    strURL = strURL.trim().toLowerCase();
    if (!strURL.contains("?")) {
      return strURL;
    }
    String strPage = "";
    String[] arrSplit = strURL.split("[?]");
    if (strURL.length() > 0) {
      if (arrSplit.length > 1) {
        if (arrSplit[0] != null) {
          strPage = arrSplit[0];
        }
      }
    }
    return strPage;
  }

  public static void route(String url, String title, final Context context) {
    if (TextUtils.isEmpty(url)) {
      return;
    }
    Bundle bundle = new Bundle();
    Map<String, String> params = URLRequest(url);
    if (url.startsWith("http")) {
      bundle.putString(ConstantUtil.URL, url);
      //context.goToOthers(JsBridgeWebViewActivity.class, bundle);
    } else if (url.startsWith("hxg://")) {
      switch (getSchemeWithoutParams(url)) {
        case HOME_PAGE:
          ((BaseActivity) context).goToOthers(MainActivity.class);
          break;
      }
    }
  }
}

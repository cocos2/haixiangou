package com.rxkj.haixiangou.util;

/**
 * 创建时间: 2016/12/19 00:09 <br>
 * 作者: zhangbin <br>
 * 描述: Uri管理
 */

public class BaseUriUtil {
  private static final String HTTPS = "https://";
  private static final String HTTP = "http://";
  public final static int VERSION_RELEASE = 0;// 线上
  public final static int VERSION_QA = 100;// QA环境
  public final static int VERSION_DEV = 200;//开发
  // 设置版本
  //public static int VERSION_URI_TYPE = BuildConfig.envType;
  public static int VERSION_URI_TYPE = VERSION_DEV;

  private static final String API_RELEASE = "www.haixiango.com/";
  private static final String API_QA = "172.30.16.222:7080/";
  private static final String API_DEV = "172.30.16.222:9001/";

  public static String getBaseUri() {
    String baseUri;
    switch (VERSION_URI_TYPE) {
      case VERSION_RELEASE:
        baseUri = HTTPS + API_RELEASE;
        break;
      case VERSION_QA:
        baseUri = HTTP + API_QA;
        break;
      case VERSION_DEV:
        baseUri = HTTP + API_DEV;
        break;
      default:
        baseUri = HTTP + API_DEV;
    }
    return baseUri;
  }
}

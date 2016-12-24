package com.rxkj.haixiangou.model;

/**
 * 创建时间: 2016/12/20 11:12 <br>
 * 作者: zhangbin <br>
 * 描述: 首页banner model
 */

public class BannerModel extends BaseResult {
  private String url;
  private String scheme;

  public String getScheme() {
    return scheme;
  }

  public void setScheme(String scheme) {
    this.scheme = scheme;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}

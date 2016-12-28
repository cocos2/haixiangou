package com.rxkj.haixiangou.model;

/**
 * 创建时间: 2016/12/28 14:55 <br>
 * 作者: zhangbin <br>
 * 描述: 分类实体类
 */

public class ClassifyModel {
  private String classify_id;
  private String name;
  private String icon_url;

  public String getClassify_id() {
    return classify_id;
  }

  public void setClassify_id(String classify_id) {
    this.classify_id = classify_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIcon_url() {
    return icon_url;
  }

  public void setIcon_url(String icon_url) {
    this.icon_url = icon_url;
  }
}

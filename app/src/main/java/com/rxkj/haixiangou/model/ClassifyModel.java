package com.rxkj.haixiangou.model;

import java.util.List;

/**
 * 创建时间: 2016/12/28 14:55 <br>
 * 作者: zhangbin <br>
 * 描述: 分类实体类
 */

public class ClassifyModel {

  /**
   * sign : 123948219
   * classify : [{"classify_id":"Google","icon_url":"http://www.google.com","name":"h5title"},{"classify_id":"Google","icon_url":"http://www.google.com","name":"h5title"},{"classify_id":"Google","icon_url":"http://www.google.com","name":"h5title"}]
   */

  private String sign;
  /**
   * classify_id : Google
   * icon_url : http://www.google.com
   * name : h5title
   */

  private List<ClassifyEntity> classify;

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public List<ClassifyEntity> getClassify() {
    return classify;
  }

  public void setClassify(List<ClassifyEntity> classify) {
    this.classify = classify;
  }

  public static class ClassifyEntity {
    private String classify_id;
    private String icon_url;
    private String name;

    public String getClassify_id() {
      return classify_id;
    }

    public void setClassify_id(String classify_id) {
      this.classify_id = classify_id;
    }

    public String getIcon_url() {
      return icon_url;
    }

    public void setIcon_url(String icon_url) {
      this.icon_url = icon_url;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }
}

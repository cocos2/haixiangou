package com.rxkj.haixiangou.imageloader;

import com.rxkj.haixiangou.R;

/**
 * 创建时间: 2016/10/24 15:35 <br>
 * 作者: zhangbin <br>
 * 描述: 图片显示配置类
 */

public class ImageOptions {
  /**
   * 图片宽度
   */
  private int mWidth;
  /**
   * 图片高度
   */
  private int mHeight;
  /**
   * loading时的加载时占位图片
   */
  private int mPlaceHolder = R.drawable.holder;
  /**
   * error时的占位图片
   */
  private int mErrorHolder = R.drawable.holder;
  /**
   * 图片形状
   */
  private Type mType;

  public Type getType() {
    return mType;
  }

  public ImageOptions setType(Type type) {
    mType = type;
    return this;
  }

  public enum Type {
    RECT, CIRCLE
  }

  public int getWidth() {
    return mWidth;
  }

  public ImageOptions setWidth(int width) {
    mWidth = width;
    return this;
  }

  public int getHeight() {
    return mHeight;
  }

  public ImageOptions setHeight(int height) {
    mHeight = height;
    return this;
  }

  public int getPlaceHolder() {
    return mPlaceHolder;
  }

  public ImageOptions setPlaceHolder(int placeHolder) {
    mPlaceHolder = placeHolder;
    return this;
  }

  public int getErrorHolder() {
    return mErrorHolder;
  }

  public ImageOptions setErrorHolder(int errorHolder) {
    mErrorHolder = errorHolder;
    return this;
  }
}

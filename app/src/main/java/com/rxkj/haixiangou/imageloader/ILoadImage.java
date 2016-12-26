package com.rxkj.haixiangou.imageloader;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import java.io.File;

/**
 * 创建时间: 2016/10/24 15:34 <br>
 * 作者: zhangbin <br>
 * 描述: 图片加载接口
 */

interface ILoadImage {
  /**
   * 从网络中加载图片
   */
  void loadImage(String path, @NonNull ImageView imageView);

  /**
   * 从资源中加载图片
   */
  void loadImage(@DrawableRes int resourceId, @NonNull ImageView imageView);

  /**
   * 从文件中加载图片
   */
  void loadImage(File file, @NonNull ImageView imageView);

  /**
   * 自定义配置,从网络中加载图片
   */
  void loadImage(String path, @NonNull ImageView imageView, ImageOptions options);

  /**
   * 自定义配置,从资源中加载图片
   */
  void loadImage(@DrawableRes int resourceId, @NonNull ImageView imageView, ImageOptions options);

  /**
   * 自定义配置,从文件中加载图片
   */
  void loadImage(File file, @NonNull ImageView imageView, ImageOptions options);
}

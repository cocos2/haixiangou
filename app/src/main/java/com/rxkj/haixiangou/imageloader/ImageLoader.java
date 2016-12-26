package com.rxkj.haixiangou.imageloader;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.io.File;

/**
 * 创建时间: 2016/10/24 15:46 <br>
 * 作者: zhangbin <br>
 * 描述: 图片加载实现类
 */

public class ImageLoader implements ILoadImage {
  private static volatile ImageLoader sInstance = null;
  private Context mContext;
  private ImageOptions mDefaultOptions;

  public static ImageLoader getInstance() {
    if (null == sInstance) {
      throw new IllegalStateException("ImageLoader must be init in Application");
    }
    return sInstance;
  }

  /**
   * application必须调用此方法进行初始化
   */
  public static void init(Context context, ImageOptions options) {
    if (null == sInstance) {
      synchronized (ImageLoader.class) {
        if (null == sInstance) {
          sInstance = new ImageLoader(context, options);
        }
      }
    }
  }

  private ImageLoader(Context context, ImageOptions options) {
    if (context == null) {
      throw new NullPointerException("Context can't be null");
    }
    if (options == null) {
      throw new NullPointerException("Options can't be null");
    }
    this.mContext = context.getApplicationContext();
    this.mDefaultOptions = options;
  }

  @Override public void loadImage(String path, @NonNull ImageView imageView) {
    Picasso.with(mContext)
        .load(path)
        .placeholder(mDefaultOptions.getPlaceHolder())
        .error(mDefaultOptions.getErrorHolder())
        .centerCrop()
        .fit()
        .into(imageView);
  }

  @Override public void loadImage(@DrawableRes int resourceId, @NonNull ImageView imageView) {
    if (resourceId == 0) {
      imageView.setImageDrawable(
          mContext.getResources().getDrawable(mDefaultOptions.getPlaceHolder()));
    } else {
      Picasso.with(mContext)
          .load(resourceId)
          .placeholder(mDefaultOptions.getPlaceHolder())
          .error(mDefaultOptions.getErrorHolder())
          .centerCrop()
          .fit()
          .into(imageView);
    }
  }

  @Override public void loadImage(File file, @NonNull ImageView imageView) {
    Picasso.with(mContext)
        .load(file)
        .placeholder(mDefaultOptions.getPlaceHolder())
        .error(mDefaultOptions.getErrorHolder())
        .centerCrop()
        .fit()
        .into(imageView);
  }

  @Override public void loadImage(String path, @NonNull ImageView imageView, ImageOptions options) {
    if (null == options) {
      loadImage(path, imageView);
      return;
    }
    RequestCreator creator = Picasso.with(mContext).load(path);
    displayImage(creator, imageView, options);
  }

  @Override public void loadImage(@DrawableRes int resourceId, @NonNull ImageView imageView,
      ImageOptions options) {
    if (null == options) {
      loadImage(resourceId, imageView);
      return;
    }
    if (resourceId == 0) {
      imageView.setImageDrawable(mContext.getResources().getDrawable(options.getPlaceHolder()));
    } else {
      RequestCreator creator = Picasso.with(mContext).load(resourceId);
      displayImage(creator, imageView, options);
    }
  }

  @Override public void loadImage(File file, @NonNull ImageView imageView, ImageOptions options) {
    if (null == options) {
      loadImage(file, imageView);
      return;
    }
    if (file == null) {
      imageView.setImageDrawable(mContext.getResources().getDrawable(options.getPlaceHolder()));
    } else {
      RequestCreator creator = Picasso.with(mContext).load(file);
      displayImage(creator, imageView, options);
    }
  }

  private void displayImage(RequestCreator creator, @NonNull ImageView imageView,
      ImageOptions options) {
    if (options.getWidth() != 0 && options.getHeight() != 0) {
      creator.resize(options.getWidth(), options.getHeight());
    } else {
      creator.fit();
    }
    if (options.getPlaceHolder() != 0) {
      creator.placeholder(options.getPlaceHolder());
    }
    if (options.getErrorHolder() != 0) {
      creator.error(options.getErrorHolder());
    }
    if (options.getType() == ImageOptions.Type.CIRCLE) {
      creator.transform(new CircleTransform());
    }
    creator.centerCrop().into(imageView);
  }
}

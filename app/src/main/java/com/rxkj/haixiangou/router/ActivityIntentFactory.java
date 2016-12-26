package com.rxkj.haixiangou.router;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import com.rxkj.haixiangou.R;
import com.rxkj.haixiangou.util.ConstantUtil;
import java.io.File;

/**
 * 创建时间: 2016/12/24 15:05 <br>
 * 作者: zhangbin <br>
 * 描述:Activity页面跳转
 */

public class ActivityIntentFactory extends IntentFactory {

  private FragmentActivity activity;

  public ActivityIntentFactory(FragmentActivity activity) {
    super(activity);
    this.activity = activity;
  }

  private FragmentTransaction initFragmentTransaction() {
    return activity.getSupportFragmentManager().beginTransaction();
  }

  public void addFragment(Fragment fragment) {
    FragmentTransaction transaction = initFragmentTransaction();
    transaction.add(R.id.simple_fragment, fragment);
    transaction.commitAllowingStateLoss();
  }

  public void replaceFragment(Fragment newFragment) {
    replaceFragment(R.id.simple_fragment, newFragment, true);
  }

  public void replaceFragment(Fragment newFragment, boolean addToBackStack) {
    replaceFragment(R.id.simple_fragment, newFragment, addToBackStack);
  }

  public void replaceFragment(int contentID, Fragment newFragment, boolean addToBackStack) {
    FragmentTransaction transaction = initFragmentTransaction();
    // transaction.setCustomAnimations(R.anim.push_left_in,
    // R.anim.push_left_out, R.anim.push_right_in,
    // R.anim.push_right_out);
    transaction.replace(contentID, newFragment);
    if (addToBackStack) {
      transaction.addToBackStack(null);
    }
    transaction.commitAllowingStateLoss();
  }

  public void goToCropImage(String path, Uri requestUri, int size) {
    File file = new File(path);
    if (file.exists()) {
      Intent intent = new Intent("com.android.camera.action.CROP");
      intent.setDataAndType(Uri.fromFile(file), "image/*");
      // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
      intent.putExtra("crop", "true");
      // aspectX aspectY 是宽高的比例
      intent.putExtra("aspectX", 1);
      intent.putExtra("aspectY", 1);
      // outputX outputY 是裁剪图片宽高
      intent.putExtra("outputX", size);
      intent.putExtra("outputY", size);
      intent.putExtra("return-data", false);
      intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
      intent.putExtra(MediaStore.EXTRA_OUTPUT, requestUri);
      activity.startActivityForResult(intent, ConstantUtil.CROP_PIC);
    }
  }

  public void goToOthersForResult(Class<?> cls, Bundle bundle, int requestCode) {
    Intent intent = new Intent(activity, cls);
    intent.putExtra(ConstantUtil.PARAM_INTENT, bundle);
    activity.startActivityForResult(intent, requestCode);
  }

  public void backForResult(Class<?> cls, Bundle bundle, int resultCode) {
    Intent intent = new Intent();
    if (cls != null) {
      intent.setClass(activity, cls);
    }
    intent.putExtra(ConstantUtil.PARAM_INTENT, bundle);
    activity.setResult(resultCode, intent);
    activity.finish();
  }

  public void goToPhoto(File file) {
    try {
      Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
      intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
      activity.startActivityForResult(intent, ConstantUtil.TAKE_PHOTOS);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void goToGallery() {
    try {
      Intent intent = new Intent();
      intent.setType("image/*");
      intent.setAction(Intent.ACTION_GET_CONTENT);
      activity.startActivityForResult(intent, ConstantUtil.SELECT_PICS);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
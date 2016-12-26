package com.rxkj.haixiangou.router;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import com.rxkj.haixiangou.R;
import com.rxkj.haixiangou.util.ConstantUtil;
import com.rxkj.haixiangou.util.StringUtils;
import com.rxkj.haixiangou.util.ToastUtil;
import java.io.File;

public class IntentFactory implements IntentListener {

  private Context context;

  public IntentFactory(Context context) {
    this.context = context;
  }

  public void goToView(String path) {
    if (StringUtils.isEmpty(path)) {
      return;
    }
    File file = new File(path);
    if (file.exists()) {
      Uri uri = Uri.fromFile(file);
      Intent intent = new Intent(Intent.ACTION_VIEW);
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      intent.setDataAndType(uri, "image/*");
      context.startActivity(intent);
    }
  }

  public void goToView(String path, Class<?> cls) {
    if (StringUtils.isEmpty(path)) {
      return;
    }
    File file = new File(path);
    if (file.exists()) {
      Intent intent = new Intent(context, cls);
      intent.putExtra("imagePath", path);
      context.startActivity(intent);
    }
  }

  public void goToOthers(Class<?> cls) {
    goToOthers(cls, null);
  }

  public void goToOthersF(Class<?> cls) {
    goToOthers(cls);
    ((Activity) context).finish();
  }

  public void goToOthers(Class<?> cls, Bundle bundle) {
    Intent intent = new Intent(context, cls);
    intent.putExtra(ConstantUtil.PARAM_INTENT, bundle);
    context.startActivity(intent);
  }

  public void goToOthersF(Class<?> cls, Bundle bundle) {
    goToOthers(cls, bundle);
    ((Activity) context).finish();
  }

  public void upToHome(Class<?> cls, Bundle bundle) {
    Intent intent = new Intent(context, cls);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    intent.putExtra(ConstantUtil.PARAM_INTENT, bundle);
    context.startActivity(intent);
    ((Activity) context).finish();
  }

  public void upToHome(Class<?> cls) {
    upToHome(cls, null);
  }

  public void homeAction() {
    Intent intent = new Intent(Intent.ACTION_MAIN);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// ע��
    intent.addCategory(Intent.CATEGORY_HOME);
    context.startActivity(intent);
  }

  public void goToWeb(String url) {
    Uri uri = Uri.parse(url);
    Intent it = new Intent(Intent.ACTION_VIEW, uri);
    it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(it);
  }

  public void goToCall(String telePhoneNum) {
    if (StringUtils.isEmpty(telePhoneNum)) {
      return;
    }
    try {
      Uri uri = Uri.parse("tel:" + telePhoneNum);
      if (uri != null) {
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        // Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED) {
          // TODO: Consider calling
          //    ActivityCompat#requestPermissions
          // here to request the missing permissions, and then overriding
          //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
          //                                          int[] grantResults)
          // to handle the case where the user grants the permission. See the documentation
          // for ActivityCompat#requestPermissions for more details.
          return;
        }
        context.startActivity(intent);
      }
    } catch (Exception e) {
      e.printStackTrace();
      ToastUtil.toast(R.string.no_tele_service);
    }
  }

  public void goToSms(String smsContent) {
    try {
      Intent intent = new Intent(Intent.ACTION_VIEW);
      intent.setType("vnd.android-dir/mms-sms");
      intent.putExtra("sms_body", StringUtils.trim(smsContent));
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      context.startActivity(intent);
    } catch (Exception e) {
      e.printStackTrace();
      ToastUtil.toast(R.string.no_sms_service);
    }
  }

  public void goToSms(String phone, String smsContent) {
    try {
      Uri smsToUri = Uri.parse("smsto:" + StringUtils.trim(phone));
      Intent intent = new Intent(android.content.Intent.ACTION_SENDTO, smsToUri);
      intent.putExtra("sms_body", StringUtils.trim(smsContent));
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      context.startActivity(intent);
    } catch (Exception e) {
      e.printStackTrace();
      ToastUtil.toast(R.string.no_sms_service);
    }
  }

  public void installApp(File file) {
    if (file == null || !file.exists()) {
      return;
    }
    Intent intent = new Intent();
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.setAction(Intent.ACTION_VIEW);
    intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
    context.startActivity(intent);
  }

  public void addFragment(Fragment fragment) {

  }

  public void replaceFragment(Fragment newFragment) {

  }

  public void replaceFragment(Fragment newFragment, boolean addToBackStack) {

  }

  public void replaceFragment(int contentID, Fragment newFragment, boolean addToBackStack) {

  }

  public void goToCropImage(String path, Uri requestUri, int size) {

  }

  public void goToOthersForResult(Class<?> cls, Bundle bundle, int requestCode) {

  }

  public void backForResult(Class<?> cls, Bundle bundle, int resultCode) {

  }

  public void goToPhoto(File file) {

  }

  public void goToGallery() {

  }
}

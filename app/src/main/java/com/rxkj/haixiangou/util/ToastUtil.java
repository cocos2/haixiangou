package com.rxkj.haixiangou.util;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.rxkj.haixiangou.R;
import com.rxkj.haixiangou.app.MyApplication;

/**
 * 创建时间: 2016/12/24 15:10 <br>
 * 作者: zhangbin <br>
 * 描述:toast工具类
 */

public class ToastUtil {
  private static final int TOAST_LENGTH_SHORT = 2000;
  private static final int NO_GRAVITY = -1;
  private static Toast sToast;
  private static String sLastestMsg;
  private static long sLastShowTime = 0;

  /**
   * Toast
   *
   * @param msg 消息
   */
  public static void toast(String msg) {
    toast(msg, NO_GRAVITY);
  }

  /**
   * Toast
   *
   * @param resId 消息ID
   */
  public static void toast(int resId) {
    toast(UIUtils.getString(resId));
  }

  /**
   * @param msg 消息内容
   * @param gravity 显示位置
   */
  public static void toast(String msg, int gravity) {
    if (TextUtils.isEmpty(msg)) {
      return;
    }
    if (sToast == null) {
      sToast = initToast(msg, gravity);
      sToast.show();
      sLastShowTime = System.currentTimeMillis();
    } else {
      if (msg.trim().equals(sLastestMsg)) {
        if (System.currentTimeMillis() - sLastShowTime > TOAST_LENGTH_SHORT) {
          sToast.show();
          sLastShowTime = System.currentTimeMillis();
        }
      } else {
        sToast.cancel();
        sToast = initToast(msg, gravity);
        sToast.show();
        sLastShowTime = System.currentTimeMillis();
      }
    }
    sLastestMsg = msg;
  }

  /**
   * @param resId 资源id
   * @param gravity 显示位置
   */
  public static void toast(int resId, int gravity) {
    toast(UIUtils.getString(resId), gravity);
  }

  /**
   * Toast
   *
   * @param msg 消息
   */
  public static void toastCenter(String msg) {
    toast(msg, Gravity.CENTER);
  }

  /**
   * 初始化Toast
   *
   * @param msg 消息
   * @param gravity 位置
   */
  private static Toast initToast(String msg, int gravity) {

    if (TextUtils.isEmpty(msg)) {
      return null;
    }
    Toast toast = Toast.makeText(MyApplication.getInstance(), msg.trim(), Toast.LENGTH_SHORT);
    View view = LayoutInflater.from(MyApplication.getInstance()).inflate(R.layout.view_toast, null);
    TextView tv_toast = (TextView) view.findViewById(R.id.tv_toast);
    tv_toast.setText(msg);
    if (gravity != NO_GRAVITY) {
      toast.setGravity(gravity, 0, 0);
    }
    toast.setView(view);
    return toast;
  }

  /**
   * cancel toast
   */
  public static void cancel() {

    if (sToast != null) {
      sToast.cancel();
      sToast = null;
    }
  }
}

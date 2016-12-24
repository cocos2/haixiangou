package com.rxkj.haixiangou.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.rxkj.haixiangou.R;
import com.rxkj.haixiangou.util.StringUtils;

/**
 * 模态对话框
 *
 * @author wanghl-a
 */
public class LoadingProgressBar extends Dialog {

  private Activity context;
  private TextView tv_content;
  private ProgressBar progressBar1;
  private View view;

  /**
   * 构造参数
   *
   * @param context 上下文
   */
  public LoadingProgressBar(Context context) {
    super(context, R.style.dialog_no_animation);
    this.context = (Activity) context;
    view = LayoutInflater.from(context).inflate(R.layout.lib_progress_bar, null);
    tv_content = (TextView) view.findViewById(R.id.tv_content);
    progressBar1 = (ProgressBar) view.findViewById(R.id.progressBar);
  }

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(view);
    setCanceledOnTouchOutside(false);
  }

  public void show() {
    progressBar1.setVisibility(View.VISIBLE);
    tv_content.setText(R.string.dialog_loading_data);
    super.show();
  }

  public void show(int resID) {
    if (tv_content != null) {
      tv_content.setText(resID);
    }
    show();
  }

  public void show(String msg) {
    if (tv_content != null) {
      tv_content.setText(StringUtils.trim(msg));
    }
    show();
  }

  public void successDismiss(String msg) {
    tv_content.setText(StringUtils.trim(msg));
  }

  public void errorDismiss(String msg) {
    tv_content.setText(StringUtils.trim(msg));
  }

  public void onBackPressed() {
    super.onBackPressed();
    context.finish();
  }
}

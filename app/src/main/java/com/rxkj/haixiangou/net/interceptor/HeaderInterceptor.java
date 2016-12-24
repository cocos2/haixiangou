package com.rxkj.haixiangou.net.interceptor;

import com.rxkj.haixiangou.app.MyApplication;
import com.rxkj.haixiangou.util.StringUtils;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Joker on 2016/2/19.
 */
public class HeaderInterceptor implements Interceptor {

  public HeaderInterceptor() {
  }

  /**
   * @throws IOException
   */
  @Override public Response intercept(Chain chain) throws IOException {
    String access_token = MyApplication.getInstance().mISharedPreferencesFactory.getAccessToken();
    Request.Builder builder = chain.request().newBuilder();
    if (!StringUtils.isEmpty(access_token)) {
      builder.addHeader("HXG-Access-Token", access_token);
    }
    return chain.proceed(builder.build());
  }
}

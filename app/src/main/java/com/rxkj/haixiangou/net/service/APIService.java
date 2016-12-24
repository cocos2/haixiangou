package com.rxkj.haixiangou.net.service;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.rxkj.haixiangou.net.interceptor.HeaderInterceptor;
import com.rxkj.haixiangou.util.BaseUriUtil;
import com.rxkj.haixiangou.util.LogUtil;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {

  private static OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
  private static Retrofit.Builder retrofitBuilder;
  private static final int TIME_OUT = 15000;

  private static Retrofit retrofit;

  static {
    if (LogUtil.isDebugChrome()) {
      APIService.httpClientBuilder.addNetworkInterceptor(new StethoInterceptor());
    }
    httpClientBuilder.connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS);
    httpClientBuilder.readTimeout(TIME_OUT, TimeUnit.MILLISECONDS);
    httpClientBuilder.writeTimeout(TIME_OUT, TimeUnit.MILLISECONDS);

    APIService.httpClientBuilder.addInterceptor(new HeaderInterceptor());

    //增加日志统计拦截器
    APIService.retrofitBuilder =
        new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .client(httpClientBuilder.build());
  }

  public static <S> S createService(Class<S> serviceClass) {
    //方便qa切换环境时能重新设置uribase
    if (retrofit == null || !HttpUrl.parse(BaseUriUtil.getBaseUri())
        .toString()
        .equals(retrofit.baseUrl().toString())) {
      retrofit = APIService.retrofitBuilder.baseUrl(BaseUriUtil.getBaseUri()).build();
    }
    return retrofit.create(serviceClass);
  }
}
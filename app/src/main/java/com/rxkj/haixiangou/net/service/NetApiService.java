package com.rxkj.haixiangou.net.service;

import com.rxkj.haixiangou.model.BaseResultData;
import com.rxkj.haixiangou.model.HomePageModel;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 创建时间: 2016/12/19 00:06 <br>
 * 作者: zhangbin <br>
 * 描述: 网络请求接口
 */

public interface NetApiService {
  @GET("v2/58624fb60f000003061754fe") Observable<BaseResultData<HomePageModel>> getHomePage(
      @Query("sign") String sign);
}

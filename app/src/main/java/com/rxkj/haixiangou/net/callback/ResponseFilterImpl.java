package com.rxkj.haixiangou.net.callback;

import com.rxkj.haixiangou.model.BaseResult;

/**
 * Created by kobe on 16/3/18.
 * 处理和服务端约定的错误码
 */
public class ResponseFilterImpl implements ResponseFilter<BaseResult> {

  @Override public void doFilter(BaseResult baseResult) {
    // 对一些特殊的错误码用eventbus通知到baseactivity去处理.
    //switch (baseResult.getError_code()) {
      //  case ConstantUtil.LIANJIA_IM_IS_REQUIRED:
      //  case ConstantUtil.LEANCLOUD_IM_IS_REQUIRED:
      //  case ConstantUtil.ERRORNO_INVALID_ACCESS_TOKEN:
      //  case ConstantUtil.ERRORNO_UNBIND_MOBILE:
      //    EventBus.getDefault().post(baseResult);
    //  break;
    //}
  }
}

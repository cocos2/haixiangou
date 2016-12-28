package com.rxkj.haixiangou.interf;

import com.rxkj.haixiangou.model.ClassifyModel;
import com.rxkj.haixiangou.presenter.IBasePresenter;
import com.rxkj.haixiangou.view.BaseView;
import java.util.List;

/**
 * 创建时间: 2016/12/18 23:21 <br>
 * 作者: zhangbin <br>
 * 描述: 首页接口
 */
public interface ClassifyPageContract extends IBasePresenter{

  interface View extends BaseView<Presenter> {
    void setProgressIndicator(boolean active);

    void showRvClassify(List<ClassifyModel> classifyModels);

    void showLoadingHomePageError();
  }

  interface Presenter extends IBasePresenter {

  }
}

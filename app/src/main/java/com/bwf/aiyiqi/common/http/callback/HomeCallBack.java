package com.bwf.aiyiqi.common.http.callback;

import com.bwf.aiyiqi.common.entity.ArticleBean;
import com.bwf.aiyiqi.common.entity.HomePicBean;

import java.util.List;

/**
 * Created by Lizhangfeng on 2016/12/6 0006.
 * Description:
 */

public interface HomeCallBack {

    void getHomePic(List<HomePicBean> homePicBeanList);

    void getHomeListData(List<ArticleBean> articleBeen);

}

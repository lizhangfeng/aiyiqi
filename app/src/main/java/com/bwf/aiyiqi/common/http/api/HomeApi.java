package com.bwf.aiyiqi.common.http.api;

import com.bwf.aiyiqi.common.entity.ArticleBean;
import com.bwf.aiyiqi.common.entity.HomePicBean;
import com.bwf.aiyiqi.common.http.HttpCallBack;
import com.bwf.aiyiqi.common.http.ServeInterface;
import com.bwf.aiyiqi.common.http.callback.HomeCallBack;
import com.bwf.aiyiqi.common.util.ToastUtil;
import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

/**
 * Created by Lizhangfeng on 2016/12/5 0005.
 * Description:
 */

public class HomeApi extends BaseApi {

    private HomeCallBack homeCallBack;

    private PullToRefreshLayout refreshLayout;

    public void setCallBack(HomeCallBack homeCallBack) {
        this.homeCallBack = homeCallBack;
    }

    public void setRefreshLayout(PullToRefreshLayout refreshLayout) {
        this.refreshLayout = refreshLayout;
    }

    public void getHomeData() {
        getHomePic();
        getHomeList("", "", "");
    }

    /**
     * 获取首页广告
     */
    public void getHomePic() {

        OkHttpUtils
                .get()
                .url(ServeInterface.HOME_PIC.getApiUrl())
                .build()
                .execute(new HttpCallBack<HomePicBean>() {
                    @Override
                    public void onSuccess(HomePicBean result, List<HomePicBean> homePicBeen, String response) {

                        if (homeCallBack != null) {
                            homeCallBack.getHomePic(homePicBeen);
                        }
                    }

                    @Override
                    public void onFail(String errMsg) {
                        ToastUtil.showToast(errMsg);
                    }
                });

    }

    /**
     * 获取首页列表
     *
     * @param page
     * @param type
     * @param id
     */
    public void getHomeList(String page, String type, String id) {
        OkHttpUtils
                .get()
                .addParams("page", page)
                .addParams("type", type)
                .addParams("id", id)
                .url(ServeInterface.HOME_LIST.getApiUrl())
                .build()
                .execute(new HttpCallBack<ArticleBean>() {
                    @Override
                    public void onSuccess(ArticleBean result, List<ArticleBean> articleBeen, String response) {

                        if (refreshLayout != null)
                            refreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);

                        if (homeCallBack != null) {
                            homeCallBack.getHomeListData(articleBeen);
                        }
                    }

                    @Override
                    public void onFail(String errMsg) {
                        ToastUtil.showToast(errMsg);
                        if (refreshLayout != null)
                            refreshLayout.refreshFinish(PullToRefreshLayout.FAIL);
                    }
                });
    }

}

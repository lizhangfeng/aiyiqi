package com.bwf.aiyiqi.common.http.api;

import com.bwf.aiyiqi.common.entity.OWnderModelResultBean;
import com.bwf.aiyiqi.common.entity.PostBean;
import com.bwf.aiyiqi.common.http.HttpCallBack;
import com.bwf.aiyiqi.common.http.ServeInterface;
import com.bwf.aiyiqi.common.http.callback.OwnersSayCallBack;
import com.bwf.aiyiqi.common.util.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

/**
 * Created by Lizhangfeng on 2016/12/7 0007.
 * Description: 业主说接口
 */

public class OwnerSayApi {
    private OwnersSayCallBack callBack;

    public void setCallBack(OwnersSayCallBack callBack) {
        this.callBack = callBack;
    }

    /**
     * 获取精华帖子、文章列表
     *
     * @param page
     */
    public void getEssenceList(String page) {
        OkHttpUtils
                .get()
                .url(ServeInterface.OWNERS_ESSENCE.getApiUrl())
                .addParams("page", page)
                .build()
                .execute(new HttpCallBack<PostBean>() {
                    @Override
                    public void onSuccess(PostBean result, List<PostBean> beanList, String response) {

                        if (callBack != null) {
                            callBack.getPostData(beanList);
                        }
                    }

                    @Override
                    public void onFail(String errMsg) {
                        ToastUtil.showToast(errMsg);
                    }
                });
    }

    /**
     * 获取最新帖子、文章列表
     *
     * @param page
     */
    public void getNewestList(String page) {
        OkHttpUtils
                .get()
                .url(ServeInterface.OWNERS_NEWEST.getApiUrl())
                .addParams("page", page)
                .build()
                .execute(new HttpCallBack<PostBean>() {
                    @Override
                    public void onSuccess(PostBean result, List<PostBean> beanList, String response) {

                        if (callBack != null) {
                            callBack.getPostData(beanList);
                        }
                    }

                    @Override
                    public void onFail(String errMsg) {
                        ToastUtil.showToast(errMsg);
                    }
                });
    }

    /**
     * 获取业主说-板块数据
     */
    public void getModelList() {
        OkHttpUtils
                .get()
                .url(ServeInterface.OWNERS_MODEL.getApiUrl())
                .build()
                .execute(new HttpCallBack<OWnderModelResultBean>() {
                    @Override
                    public void onSuccess(OWnderModelResultBean result, List<OWnderModelResultBean> beanList, String response) {

                        if (callBack != null)
                            callBack.getModelData(result);

                    }

                    @Override
                    public void onFail(String errMsg) {
                        ToastUtil.showToast(errMsg);
                    }
                });
    }


}

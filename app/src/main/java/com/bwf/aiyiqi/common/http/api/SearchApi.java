package com.bwf.aiyiqi.common.http.api;

import com.bwf.aiyiqi.common.entity.SearchBean;
import com.bwf.aiyiqi.common.http.HttpCallBack;
import com.bwf.aiyiqi.common.http.ServeInterface;
import com.bwf.aiyiqi.common.http.callback.SearchCallBack;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

/**
 * Created by Lizhangfeng on 2016/12/6 0006.
 * Description:
 */

public class SearchApi extends BaseApi {

    private SearchCallBack searchCallBack;

    public void setSearchCallBack(SearchCallBack searchCallBack) {
        this.searchCallBack = searchCallBack;
    }

    /**
     * 获取首页广告
     */
    public void getSearchResult(String page,String kw) {

        OkHttpUtils
                .get()
                .url(ServeInterface.SEARCH.getApiUrl())
                .addParams("page",page)
                .addParams("kw",kw)
                .build()
                .execute(new HttpCallBack<SearchBean>() {
                    @Override
                    public void onSuccess(SearchBean result, List<SearchBean> beanList, String response) {

                        if (searchCallBack != null) {
                            searchCallBack.getSearchResultData(beanList);
                        }
                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }

}

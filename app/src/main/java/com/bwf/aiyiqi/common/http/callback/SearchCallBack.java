package com.bwf.aiyiqi.common.http.callback;

import com.bwf.aiyiqi.common.entity.SearchBean;

import java.util.List;

/**
 * Created by Lizhangfeng on 2016/12/6 0006.
 * Description:
 */

public interface SearchCallBack {

    void getSearchResultData(List<SearchBean> beanList);

}

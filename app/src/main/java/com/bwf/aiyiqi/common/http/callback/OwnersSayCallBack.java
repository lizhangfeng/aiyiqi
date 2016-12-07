package com.bwf.aiyiqi.common.http.callback;

import com.bwf.aiyiqi.common.entity.OWnderModelResultBean;
import com.bwf.aiyiqi.common.entity.PostBean;

import java.util.List;

/**
 * Created by Lizhangfeng on 2016/12/7 0007.
 * Description:
 */

public interface OwnersSayCallBack {

    void getPostData(List<PostBean> postBeanList);

    void getModelData(OWnderModelResultBean result);

}

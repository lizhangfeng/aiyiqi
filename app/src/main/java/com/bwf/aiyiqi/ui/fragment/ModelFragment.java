package com.bwf.aiyiqi.ui.fragment;


import com.bwf.aiyiqi.R;
import com.bwf.aiyiqi.common.base.BaseDataBindingFragment;
import com.bwf.aiyiqi.common.entity.OWnderModelResultBean;
import com.bwf.aiyiqi.common.entity.PostBean;
import com.bwf.aiyiqi.common.http.api.OwnerSayApi;
import com.bwf.aiyiqi.common.http.callback.OwnersSayCallBack;
import com.bwf.aiyiqi.common.util.ToastUtil;

import java.util.List;

/**
 * 业主说-板块
 */
public class ModelFragment extends BaseDataBindingFragment implements OwnersSayCallBack{

    public static ModelFragment newInstance() {
        ModelFragment fragment = new ModelFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_model;
    }

    @Override
    protected void initData() {
        OwnerSayApi api = new OwnerSayApi();
        api.getModelList();
        api.setCallBack(this);
    }

    @Override
    public void getPostData(List<PostBean> postBeanList) {

    }

    @Override
    public void getModelData(OWnderModelResultBean result) {
        ToastUtil.showToast(result.toString());
    }
}

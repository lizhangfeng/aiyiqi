package com.bwf.aiyiqi.ui.fragment;


import android.support.v7.widget.LinearLayoutManager;

import com.bwf.aiyiqi.R;
import com.bwf.aiyiqi.common.base.BaseDataBindingFragment;
import com.bwf.aiyiqi.common.entity.OWnderModelResultBean;
import com.bwf.aiyiqi.common.entity.PostBean;
import com.bwf.aiyiqi.common.http.api.OwnerSayApi;
import com.bwf.aiyiqi.common.http.callback.OwnersSayCallBack;
import com.bwf.aiyiqi.databinding.FragmentNewestBinding;
import com.bwf.aiyiqi.ui.adapter.NewestAdapter;

import java.util.List;

/**
 * 业主说-最新页面
 */
public class NewestFragment extends BaseDataBindingFragment<FragmentNewestBinding> implements OwnersSayCallBack {

    private NewestAdapter adapter;
    private List<PostBean> postList;
    private int page = 1;

    public static NewestFragment newInstance() {
        NewestFragment fragment = new NewestFragment();
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_newest;
    }

    @Override
    protected void initData() {
        adapter = new NewestAdapter(getContext());
        viewDataBinding.rcPostList.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        viewDataBinding.rcPostList.setLayoutManager(layoutManager);

        //获取列表数据
        OwnerSayApi api = new OwnerSayApi();
        api.setCallBack(this);
        api.getEssenceList("" + page);
    }

    @Override
    public void getPostData(List<PostBean> postBeanList) {
        if (page == 1) {
            postList = postBeanList;
        } else
            postList.addAll(postBeanList);

        adapter.setDatas(postBeanList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getModelData(OWnderModelResultBean result) {

    }


}

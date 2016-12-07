package com.bwf.aiyiqi.ui.fragment;


import android.support.v7.widget.LinearLayoutManager;

import com.bwf.aiyiqi.R;
import com.bwf.aiyiqi.common.base.BaseDataBindingFragment;
import com.bwf.aiyiqi.common.entity.OWnderModelResultBean;
import com.bwf.aiyiqi.common.entity.PostBean;
import com.bwf.aiyiqi.common.http.api.OwnerSayApi;
import com.bwf.aiyiqi.common.http.callback.OwnersSayCallBack;
import com.bwf.aiyiqi.databinding.FragmentEssenceBinding;
import com.bwf.aiyiqi.ui.adapter.EssenceAdapter;

import java.util.List;

/**
 * 精华
 */
public class EssenceFragment extends BaseDataBindingFragment<FragmentEssenceBinding> implements OwnersSayCallBack {

    private int page = 1;
    private List<PostBean> postList;
    private EssenceAdapter essenceAdapter;


    public static EssenceFragment newInstance() {
        EssenceFragment fragment = new EssenceFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_essence;
    }

    @Override
    protected void initData() {

        essenceAdapter = new EssenceAdapter(getContext());
        viewDataBinding.rcPostList.setAdapter(essenceAdapter);
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

        essenceAdapter.setDatas(postBeanList);
        essenceAdapter.notifyDataSetChanged();
    }

    @Override
    public void getModelData(OWnderModelResultBean result) {

    }
}

package com.bwf.aiyiqi.common.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lizhangfeng on 2015/12/11.
 */
public abstract class BaseDataBindingFragment<T extends ViewDataBinding> extends Fragment {

    protected View rootView;

    protected T viewDataBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
            rootView = viewDataBinding.getRoot();
            initData();
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

        super.setUserVisibleHint(isVisibleToUser);
    }

    public abstract int getLayoutId();//获取布局id

    protected abstract void initData();//初始化数据

}

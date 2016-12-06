package com.bwf.aiyiqi.common.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwf.aiyiqi.R;
import com.bwf.aiyiqi.databinding.ItemHomeAdsBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lizhangfeng on 2016/12/5 0005.
 * Description:广告类的viewpager基类
 */

public abstract class BaseDataBindingPicPagerAdapter<T> extends PagerAdapter {

    protected List<T> datas;

    protected Context context;

    protected List<ItemHomeAdsBinding> viewDataBindings;

    public BaseDataBindingPicPagerAdapter(Context context) {
        this.context = context;
        viewDataBindings = new ArrayList<>();
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
        if (datas != null && !datas.isEmpty()) {
            for (T data : datas) {
                ItemHomeAdsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_home_ads, null, false);
                viewDataBindings.add(binding);
            }
        }
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ItemHomeAdsBinding binding = viewDataBindings.get(position);
        onBindView(binding, datas.get(position), position);
        if (binding.getRoot().getParent() == null) {//如果view被add过却没有remove则不add
            container.addView(binding.getRoot());
        }
        return binding.getRoot();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewDataBindings.get(position).getRoot());
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    protected abstract void onBindView(ItemHomeAdsBinding binding, T data, int position);

}

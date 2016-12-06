package com.bwf.aiyiqi.ui.adapter;

import android.content.Context;

import com.bwf.aiyiqi.common.base.BaseDataBindingPicPagerAdapter;
import com.bwf.aiyiqi.common.entity.HomePicBean;
import com.bwf.aiyiqi.common.tools.ImageLoader;
import com.bwf.aiyiqi.databinding.ItemHomeAdsBinding;

/**
 * Created by Lizhangfeng on 2016/12/5 0005.
 * Description: 首页广告栏
 */

public class HomeAdsAdapter extends BaseDataBindingPicPagerAdapter<HomePicBean> {

    public HomeAdsAdapter(Context context) {
        super(context);
    }

    @Override
    protected void onBindView(ItemHomeAdsBinding binding, HomePicBean data, int position) {
        ImageLoader.getInstance().disPlayImage(binding.imgHomePic,data.imagesrc);
    }


}

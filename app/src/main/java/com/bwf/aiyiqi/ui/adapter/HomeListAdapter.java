package com.bwf.aiyiqi.ui.adapter;

import android.content.Context;

import com.bwf.aiyiqi.R;
import com.bwf.aiyiqi.common.base.BaseDataBindingRecyclerViewAdapter;
import com.bwf.aiyiqi.common.entity.ArticleBean;
import com.bwf.aiyiqi.common.tools.ImageLoader;
import com.bwf.aiyiqi.databinding.ItemHomeListBinding;

/**
 * Created by Lizhangfeng on 2016/12/5 0005.
 * Description: 文章列表
 */

public class HomeListAdapter extends BaseDataBindingRecyclerViewAdapter<ArticleBean, ItemHomeListBinding> {

    public HomeListAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_home_list;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position, ArticleBean bean) {
        ImageLoader.getInstance().disPlayImage(holder.binding.imgUserhead, bean.avtUrl);
        ImageLoader.getInstance().disPlayImage(holder.binding.imgArticlePath, bean.path);
        holder.binding.setBean(bean);
    }

}

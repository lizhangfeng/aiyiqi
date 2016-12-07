package com.bwf.aiyiqi.ui.adapter;

import android.content.Context;

import com.bwf.aiyiqi.R;
import com.bwf.aiyiqi.common.base.BaseDataBindingRecyclerViewAdapter;
import com.bwf.aiyiqi.common.entity.PostBean;
import com.bwf.aiyiqi.common.tools.ImageLoader;
import com.bwf.aiyiqi.databinding.ItemNewestPostListBinding;

/**
 * Created by Lizhangfeng on 2016/12/5 0005.
 * Description: 最新列表
 */

public class NewestAdapter extends BaseDataBindingRecyclerViewAdapter<PostBean, ItemNewestPostListBinding> {

    public NewestAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_newest_post_list;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position, PostBean bean) {
        holder.binding.setBean(bean);
        ImageLoader.getInstance().disPlayImage(holder.binding.imgUserhead, bean.avtUrl);
    }

}

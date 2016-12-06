package com.bwf.aiyiqi.ui.adapter;

import android.content.Context;

import com.bwf.aiyiqi.R;
import com.bwf.aiyiqi.common.base.BaseDataBindingRecyclerViewAdapter;
import com.bwf.aiyiqi.common.entity.SearchBean;
import com.bwf.aiyiqi.common.tools.ImageLoader;
import com.bwf.aiyiqi.databinding.ItemSearchListBinding;

/**
 * Created by Lizhangfeng on 2016/12/5 0005.
 * Description: 文章列表
 */

public class SearchListAdapter extends BaseDataBindingRecyclerViewAdapter<SearchBean, ItemSearchListBinding> {

    public SearchListAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_search_list;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position, SearchBean bean) {
        holder.binding.setBean(bean);
        ImageLoader.getInstance().disPlayImage(holder.binding.imgUserhead, bean.avtUrl);
        if (bean.attachments != null) {
            ImageLoader.getInstance().disPlayImage(holder.binding.imgArticlePath, bean.attachments.get(0));
        }

    }

}

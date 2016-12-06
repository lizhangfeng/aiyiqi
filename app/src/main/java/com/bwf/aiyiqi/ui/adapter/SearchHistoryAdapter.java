package com.bwf.aiyiqi.ui.adapter;

import android.content.Context;

import com.bwf.aiyiqi.R;
import com.bwf.aiyiqi.common.base.BaseDataBindingRecyclerViewAdapter;
import com.bwf.aiyiqi.common.db.bean.SearchHistory;
import com.bwf.aiyiqi.databinding.ItemSearchHistoryBinding;

/**
 * Created by Lizhangfeng on 2016/12/6 0006.
 * Description:搜索历史
 */

public class SearchHistoryAdapter extends BaseDataBindingRecyclerViewAdapter<SearchHistory, ItemSearchHistoryBinding> {

    public SearchHistoryAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_search_history;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position, SearchHistory bean) {
        holder.binding.setBean(bean);
    }
}

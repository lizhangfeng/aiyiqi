package com.bwf.aiyiqi.common.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Lizhangfeng on 2016/9/13 0013.
 * Description: dataBinding的baseRecyclerAdapter
 */
public abstract class BaseDataBindingRecyclerViewAdapter<T, DB extends ViewDataBinding> extends RecyclerView.Adapter {

    public static final int TYPE_HEADER = 0;//type为0表示为headerView

    public static final int TYPE_NORMAL = 1;//type为1表示为正常的item

    protected Context context;
    //adapte 加载的数据
    public List<T> datas;

    //添加headerView
    private View headerView;

    //item点击事件
    private OnItemClickListener itemClickListener;

    public BaseDataBindingRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public List<T> getDatas() {
        return datas;
    }

    public View getHeaderView() {
        return headerView;
    }

    public void setHeaderView(View headerView) {
        this.headerView = headerView;
        notifyItemInserted(0);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (headerView != null && position == 0)
            return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headerView != null && viewType == TYPE_HEADER) {
            return new Holder(headerView);
        }

        DB binding = DataBindingUtil.inflate(LayoutInflater.from(context), getLayoutId(), parent, false);

        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (headerView != null && holder.getLayoutPosition() == 0)
            return;

        if (datas != null) {
            int realPos = getRealPosition(holder);
            if (realPos < datas.size()) {
                final T bean = datas.get(realPos);
                onBindViewHolder((Holder) holder, realPos, bean);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (itemClickListener != null) {
                            itemClickListener.onItemClick(position, bean);
                        }
                    }
                });

            }

        }
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return headerView == null ? position : position - 1;
    }


    @Override
    public int getItemCount() {

        if (headerView != null)
            return datas == null ? 1 : datas.size() + 1;

        return datas == null ? 0 : datas.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        public DB binding;

        public Holder(DB binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public Holder(View itemView) {
            super(itemView);
        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            final GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {

                    int SpanSize = layoutManager.getSpanCount();//
                    return getItemViewType(position) == 0 ? SpanSize : 1;
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);

        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams && holder.getLayoutPosition() == 0) {
            ((StaggeredGridLayoutManager.LayoutParams) lp).setFullSpan(true);
        }

    }

    public abstract int getLayoutId();

    public abstract void onBindViewHolder(Holder holder, int position, T bean);


    /**
     * 处理item点击监听
     *
     * @param <T>
     */
    public interface OnItemClickListener<T> {
        void onItemClick(int position, T data);
    }
}

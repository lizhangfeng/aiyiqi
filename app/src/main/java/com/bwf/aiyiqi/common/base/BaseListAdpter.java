package com.bwf.aiyiqi.common.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Lizhangfeng on 2016/8/16 0016.
 * Description:
 */
public abstract class BaseListAdpter<T, VH extends BaseListAdpter.ViewHolder> extends BaseAdapter {

    protected List<T> datas;

    protected Context context;

    protected View rootView;

    public BaseListAdpter(Context context) {
        this.context = context;
    }

    public BaseListAdpter(List<T> tList, Context context) {
        this.datas = tList;
        this.context = context;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public List<T> getDatas() {
        return datas;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas == null ? null : datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int postion, View convertView, ViewGroup viewGroup) {
        VH viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, getResourceId(), null);
            rootView = convertView;
            viewHolder = onCreateViewHolder(viewGroup);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (VH) convertView.getTag();
        }

        T t = datas.get(postion);

        if (t != null)
            onBindViewHolder(viewHolder, t, postion);

        return convertView;
    }

    public abstract int getResourceId();

    public abstract VH onCreateViewHolder(ViewGroup parent);

    public abstract void onBindViewHolder(VH holder, T t, int position);

    public class ViewHolder {

    }

    public <T extends View> T findViewByIdNoCast(int id){
        return (T) rootView.findViewById(id);
    }

}

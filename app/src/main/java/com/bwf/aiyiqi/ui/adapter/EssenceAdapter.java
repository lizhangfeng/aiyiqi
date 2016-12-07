package com.bwf.aiyiqi.ui.adapter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.bwf.aiyiqi.R;
import com.bwf.aiyiqi.common.base.BaseDataBindingRecyclerViewAdapter;
import com.bwf.aiyiqi.common.entity.HouseInfoBean;
import com.bwf.aiyiqi.common.entity.PostBean;
import com.bwf.aiyiqi.common.tools.ImageLoader;
import com.bwf.aiyiqi.common.util.StringUtil;
import com.bwf.aiyiqi.databinding.ItemPostListBinding;

/**
 * Created by Lizhangfeng on 2016/12/5 0005.
 * Description: 精华列表
 */

public class EssenceAdapter extends BaseDataBindingRecyclerViewAdapter<PostBean, ItemPostListBinding> {

    public EssenceAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_post_list;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position, PostBean bean) {
        holder.binding.setBean(bean);
        String post_title = getPostTitle(bean);
        holder.binding.tvPostTitle.setText(post_title);
        ImageLoader.getInstance().disPlayImage(holder.binding.imgUserhead, bean.avtUrl);
        if (bean.attachments != null && !bean.attachments.isEmpty())
            ImageLoader.getInstance().disPlayImage(holder.binding.imgArticlePath, bean.attachments.get(0));
    }

    public String getPostTitle(PostBean postBean) {
        if (StringUtil.isNotEmpty(postBean.houseInfo) && postBean.houseInfo.startsWith("{")) {
            HouseInfoBean houseInfoBean = JSON.parseObject(postBean.houseInfo, HouseInfoBean.class);
            if (houseInfoBean != null) {
                String desc = houseInfoBean.layout + "室" + houseInfoBean.style + "厅、" + houseInfoBean.area + "平、" + houseInfoBean.budget + "万";
                return desc;
            }
        }

        return "";
    }

}

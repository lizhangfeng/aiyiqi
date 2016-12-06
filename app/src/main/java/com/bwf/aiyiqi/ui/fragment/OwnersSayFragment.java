package com.bwf.aiyiqi.ui.fragment;


import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.bwf.aiyiqi.R;
import com.bwf.aiyiqi.common.base.BaseDataBindingFragment;
import com.bwf.aiyiqi.databinding.FragmentOwnersSayBinding;

/**
 * 业主说
 */
public class OwnersSayFragment extends BaseDataBindingFragment<FragmentOwnersSayBinding> implements View.OnClickListener {


    @Override
    public int getLayoutId() {
        return R.layout.fragment_owners_say;
    }

    @Override
    protected void initData() {
        viewDataBinding.imgPublish.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_publish:
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.publish_btn_rotate_01);
                viewDataBinding.imgPublish.startAnimation(animation);
                break;
        }
    }
}

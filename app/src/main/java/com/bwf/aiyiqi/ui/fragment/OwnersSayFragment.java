package com.bwf.aiyiqi.ui.fragment;


import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RadioGroup;

import com.bwf.aiyiqi.R;
import com.bwf.aiyiqi.common.base.BaseDataBindingFragment;
import com.bwf.aiyiqi.common.util.DisplayUtil;
import com.bwf.aiyiqi.databinding.FragmentOwnersSayBinding;
import com.bwf.aiyiqi.ui.adapter.OwnerPagerAdapter;
import com.bwf.aiyiqi.ui.view.PublishPopwindow;

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

        initViewPager();
    }

    /**
     * 初始化viewPager
     */
    public void initViewPager() {
        viewDataBinding.ownerIndicator.setLineLength(DisplayUtil.dip2px(getContext(), 51));
        viewDataBinding.ownerIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int postion) {
                switch (postion) {
                    case 0:
                        viewDataBinding.rbEssence.setChecked(true);
                        break;
                    case 1:
                        viewDataBinding.rbNewest.setChecked(true);
                        break;
                    case 2:
                        viewDataBinding.rbModel.setChecked(true);
                        break;

                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

        // radioGroup 改变监听
        viewDataBinding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {

                    case R.id.rb_essence:
                        viewDataBinding.ownerViewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_newest:
                        viewDataBinding.ownerViewPager.setCurrentItem(1);
                        break;
                    case R.id.rb_model:
                        viewDataBinding.ownerViewPager.setCurrentItem(2);
                        break;
                }

            }
        });

        OwnerPagerAdapter adapter = new OwnerPagerAdapter(getChildFragmentManager());
        viewDataBinding.ownerViewPager.setAdapter(adapter);
        viewDataBinding.ownerViewPager.setOffscreenPageLimit(3);
        viewDataBinding.ownerIndicator.setViewPager(viewDataBinding.ownerViewPager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_publish://发布帖子
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.publish_btn_rotate_01);
                viewDataBinding.imgPublish.startAnimation(animation);
                PublishPopwindow popwindow = new PublishPopwindow(getContext());
                popwindow.showPop(viewDataBinding.imgPublish);
                break;
        }
    }
}

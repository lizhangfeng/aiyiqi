package com.bwf.aiyiqi.ui.activity;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.bwf.aiyiqi.R;
import com.bwf.aiyiqi.common.base.BaseDataBindingActivity;
import com.bwf.aiyiqi.common.util.AnimationHepler;
import com.bwf.aiyiqi.common.util.IntentUtils;
import com.bwf.aiyiqi.databinding.ActivityWelcomeBinding;

/**
 * 欢迎页面
 */
public class WelcomeActivity extends BaseDataBindingActivity<ActivityWelcomeBinding> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void setUseDefaultTitleBarColor() {
        useDefaultTitleBarColor = false;
        super.setUseDefaultTitleBarColor();
    }

    @Override
    public void initData() {
        //淡出动画
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha_0_100);
        AnimationHepler.getInstance().startAnimation(this, viewDataBinding.imgWelcome, animation, new AnimationHepler.OnAnimEnd() {
            @Override
            public void end() {
                IntentUtils.openActivity(WelcomeActivity.this, MainActivity.class);
                finish();
            }
        });
    }

}

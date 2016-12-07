package com.bwf.aiyiqi.ui.activity;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.bwf.aiyiqi.R;
import com.bwf.aiyiqi.common.base.BaseDataBindingActivity;
import com.bwf.aiyiqi.common.share.SharePrefreceHelper;
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
    }

    @Override
    public void initData() {

        //判断是否是第一次进入app
        if (SharePrefreceHelper.getInstence(this).getNeedGuide()) {
            IntentUtils.openActivity(this, GuideActivity.class);
            SharePrefreceHelper.getInstence(this).setNeedGuide(false);
            finish();
        }

        //淡出动画
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha_0_100);
        animation.setDuration(1000);
        AnimationHepler.getInstance().startAnimation(this, viewDataBinding.imgWelcome, animation, new AnimationHepler.OnAnimEnd() {
            @Override
            public void end() {
                IntentUtils.openActivity(WelcomeActivity.this, MainActivity.class);
                finish();
            }
        });
    }

}

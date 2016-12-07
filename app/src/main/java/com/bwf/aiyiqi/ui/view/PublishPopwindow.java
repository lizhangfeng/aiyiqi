package com.bwf.aiyiqi.ui.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.PopupWindow;

import com.bwf.aiyiqi.R;
import com.bwf.aiyiqi.databinding.PopPublishBinding;

/**
 * Created by Lizhangfeng on 2016/12/6 0006.
 * Description:
 */

public class PublishPopwindow extends PopupWindow {


    public PublishPopwindow(Context context) {
        this(context, null);
    }

    public PublishPopwindow(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PublishPopwindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(final Context context) {
        final PopPublishBinding dataBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.pop_publish, null, false);
        //popWindow相关设置
        this.setContentView(dataBinding.getRoot());
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        this.setBackgroundDrawable(new BitmapDrawable());
//        this.setAnimationStyle(R.style.AppBaseTheme);
        dataBinding.tvPublishArticle.startAnimation(getTranslateAnimation(context, 400));
        dataBinding.tvShaidan.startAnimation(getTranslateAnimation(context, 600));
        dataBinding.tvNote.startAnimation(getTranslateAnimation(context, 800));
        dataBinding.tvPraise.startAnimation(getTranslateAnimation(context, 1000));

        dataBinding.imgPopPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //消失动画
                Animation animation = AnimationUtils.loadAnimation(context, R.anim.publish_btn_rotate_02);
                animation.setFillAfter(true);
                dataBinding.imgPopPublish.startAnimation(animation);

                Animation dismissAnimation = getTranslateAnimation2(context, 1000);
                dataBinding.tvPublishArticle.startAnimation(dismissAnimation);
                dataBinding.tvShaidan.startAnimation(getTranslateAnimation2(context, 800));
                dataBinding.tvNote.startAnimation(getTranslateAnimation2(context, 600));
                dataBinding.tvPraise.startAnimation(getTranslateAnimation2(context, 400));

                dismissAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        dismiss();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });


    }

    public void showPop(View view) {
        if (!isShowing()) {
            showAtLocation(view, 0, 0, 0);
        }
    }

    public Animation getTranslateAnimation(Context context, int drution) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.pop_translate_01);
        animation.setDuration(drution);
        return animation;
    }

    public Animation getTranslateAnimation2(Context context, int drution) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.pop_translate_02);
        animation.setFillAfter(true);
        animation.setDuration(drution);
        return animation;
    }

}

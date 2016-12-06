package com.bwf.aiyiqi.ui.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import static com.uuzuche.lib_zxing.camera.CameraManager.init;

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
    public void init(Context context) {
        View view = View.inflate(context, com.bwf.aiyiqi.R.layout.pop_car_list, null);
        //popWindow相关设置
        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setAnimationStyle(com.bwf.aiyiqi.R.style.PopupAnimation);


    }

    public void showPop(View view) {
        if (!isShowing()) {
            showAsDropDown(view);
        }
    }

}

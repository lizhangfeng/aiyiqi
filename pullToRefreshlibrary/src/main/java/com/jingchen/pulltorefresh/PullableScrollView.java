package com.jingchen.pulltorefresh;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * 下拉刷新的ScrollView支持滑动淡入淡出
 */
public class PullableScrollView extends ScrollView implements Pullable {

    private int fadeHeight = -1;

    private int fadeColor = -1;//默认为背景颜色

    private View fadeView;

    public PullableScrollView(Context context) {
        super(context);
    }

    public PullableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullableScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 设置淡入淡出参数
     *
     * @param fadeView
     * @param fadeHeight
     */
    public void setFade(View fadeView, int fadeHeight) {
        this.fadeHeight = fadeHeight;
        this.fadeView = fadeView;
    }

    /**
     * 设置淡入淡出参数
     *
     * @param fadeView
     * @param fadeHeight
     * @param color
     */
    public void setFade(View fadeView, int fadeHeight, int color) {
        this.fadeHeight = fadeHeight;
        this.fadeView = fadeView;
        this.fadeColor = color;
    }

    @Override
    public boolean canPullDown() {
        if (getScrollY() == 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean canPullUp() {
//		if (getScrollY() >= (getChildAt(0).getHeight() - getMeasuredHeight()))
//			return true;
//		else
        return false;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldX, int oldY) {
        super.onScrollChanged(x, y, oldX, oldY);
        if (fadeHeight != -1 && fadeView != null) {
            if (y <= 0) {
                fadeView.setBackgroundColor(Color.TRANSPARENT);
            } else if (y > 0 && y < fadeHeight) {
                float scale = (float) y / fadeHeight;
                fadeView.setBackgroundColor(Color.argb((int) (scale * 255), 0, 160, 81));
            } else {
                fadeView.setBackgroundColor(Color.rgb(0, 160, 81));
            }
        }
    }


}

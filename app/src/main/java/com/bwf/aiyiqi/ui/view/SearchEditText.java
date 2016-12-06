package com.bwf.aiyiqi.ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.bwf.aiyiqi.R;


/**
 * Created by lizhangfeng on 16/6/7.
 * description: 搜索输入框
 */
public class SearchEditText extends EditText {

    private Drawable deleteImg;

    private Drawable left;

    public SearchEditText(Context context) {
        super(context);
        init();
    }

    public SearchEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public SearchEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    public void init() {

        left = getContext().getResources().getDrawable(R.mipmap.search_new);
        deleteImg = getContext().getResources().getDrawable(R.mipmap.img_cancel);

        setEnabled(true);
//        addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                setDrawable();
//            }
//        });

        //点击drawRight图标事件
        setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //getCompoundDrawables() 可以获取一个长度为4的数组，
                //存放drawableLeft，Right，Top，Bottom四个图片资源对象
                //index=2 表示的是 drawableRight 图片资源对象
                Drawable drawable = getCompoundDrawables()[2];
                if (drawable == null)
                    return false;

                if (event.getAction() != MotionEvent.ACTION_UP)
                    return false;

                //drawable.getIntrinsicWidth() 获取drawable资源图片呈现的宽度
                if (event.getX() > getWidth() - getPaddingRight()
                        - drawable.getIntrinsicWidth()) {
                    setText("");
                }
                return false;
            }
        });

    }

    //设置删除图片
    public void setDrawable() {

        if (deleteImg == null)//防止被回收置null
        {
            deleteImg = getContext().getResources().getDrawable(R.mipmap.img_cancel);
            left = getContext().getResources().getDrawable(R.mipmap.search_new);
        }

        if (length() < 1)
            setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
        else
            setCompoundDrawablesWithIntrinsicBounds(left, null, deleteImg, null);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

}

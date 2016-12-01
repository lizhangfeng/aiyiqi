package com.bwf.aiyiqi.common.util;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by Lizhangfeng on 2016/11/30 0030.
 * Description:
 */

public class KeyBoardUtil {

    /**
     * 本段代码用来处理如果输入法还显示的话就消失掉输入键盘
     */
    public static void dismissSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManage = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManage.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示键盘
     *
     * @param view
     */
    public static void showKeyboard(Activity activity,View view) {
        try {
            InputMethodManager inputMethodManage = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManage.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

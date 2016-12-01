package com.bwf.aiyiqi.common.util;

import android.widget.Toast;

import com.bwf.aiyiqi.ui.App;


/**
 * Created by lizhangfeng on 16/3/31.
 * description: toast帮助类
 */
public class ToastUtil {

    /**
     * 短时间显示Toast
     *
     * @param info 显示的内容
     */
    public static void showToast(String info) {
        Toast.makeText(App.getAppContext(), info, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param info 显示的内容
     */
    public static void showToastLong(String info) {
        Toast.makeText(App.getAppContext(), info, Toast.LENGTH_LONG).show();
    }

    /**
     * 短时间显示Toast
     */
    public static void showToast(int resId) {
        Toast.makeText(App.getAppContext(), App.getAppContext().getString(resId), Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     */
    public static void showToastLong(int resId) {
        Toast.makeText(App.getAppContext(), resId, Toast.LENGTH_LONG).show();
    }

}

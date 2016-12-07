package com.bwf.aiyiqi.common.share;

import android.content.Context;

/**
 * Created by Lizhangfeng on 2016/7/13 0013.
 * Description:
 */
public class SharePrefreceHelper extends PrefrenceWrapper {

    private static SharePrefreceHelper sharePrefreceHelper;

    private SharePrefreceHelper(Context context) {
        super(context);
    }

    public static SharePrefreceHelper getInstence(Context context) {
        if (sharePrefreceHelper == null)
            sharePrefreceHelper = new SharePrefreceHelper(context);
        return sharePrefreceHelper;
    }

    /**
     * 是否需要走引导页面
     *
     * @param flag
     */
    public void setNeedGuide(boolean flag) {
        setBoolean("NeedGuide", flag);
    }

    public boolean getNeedGuide() {
        return getBoolean("NeedGuide", true);
    }



}

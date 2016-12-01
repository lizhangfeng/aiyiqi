package com.bwf.aiyiqi.common.tools;

import android.app.ActivityManager;
import android.content.Context;

import com.bwf.aiyiqi.common.util.LogUtil;
import com.bwf.aiyiqi.ui.App;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by Lizhangfeng on 2016/11/22 0022.
 * Description: 全局异常的捕获
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static CrashHandler crashHandler;

    //系统默认的异常信息捕获handler
    private Thread.UncaughtExceptionHandler defaultHandler;

    private CrashHandler() {

    }

    public static CrashHandler newInstance() {
        if (crashHandler == null)
            crashHandler = new CrashHandler();
        return crashHandler;
    }

    public void init() {
        defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }


    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {

        if (!handException(throwable)) {//交给系统处理异常
            defaultHandler.uncaughtException(thread, throwable);
        } else {//此处自己处理错误系统
            //此处可以上传到服务器了（把设备和Android系统的基本信息传过去）

//            if (true) {//如果当前app在前台
//                //此处可以进行跳转欢迎页面或者弹出一个Dialog （new Thread （）｛run(){   new Handler().post(){}}｝）
//            } else {
//                //退出app
                System.exit(0);
                ActivityManager activityManager = (ActivityManager) App.getAppContext().getSystemService(Context.ACTIVITY_SERVICE);
                activityManager.restartPackage(App.getAppContext().getPackageName());
//            }

        }

    }

    /**
     * 如果返回false 交给系统处理
     * 如果return true 自己处理
     *
     * @param throwable
     * @return
     */
    public boolean handException(Throwable throwable) {

        if (throwable == null)
            return false;

        String errorMsg = getStack(throwable);

        return true;

    }

    public String getStack(Throwable throwable) {

        String errorMsg = "";

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(baos);
            throwable.printStackTrace(printStream);
            errorMsg = baos.toString();
        } catch (Exception e) {
            errorMsg = e.toString();
        }

        LogUtil.e("errorMsg : " + errorMsg);

        return errorMsg;

    }

}

package com.bwf.aiyiqi.ui;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.https.HttpsUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Lizhangfeng on 2016/11/30 0030.
 * Description:应用的Application
 */

public class App extends Application {

    private static App app;


    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        //初始化Fresco
        Fresco.initialize(this);

        initOkHttp();
    }


    public static App getApplication() {
        return app;
    }

    public static Context getAppContext() {
        return app.getApplicationContext();
    }

    public void initOkHttp() {

        //cookie的自动化管理
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));

        //https的支持
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                //.addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();


        OkHttpUtils.initClient(okHttpClient);
    }

}

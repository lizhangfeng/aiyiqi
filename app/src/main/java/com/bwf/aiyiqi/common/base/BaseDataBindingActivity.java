package com.bwf.aiyiqi.common.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.bwf.aiyiqi.R;
import com.bwf.aiyiqi.common.tools.AppManager;
import com.bwf.aiyiqi.common.tools.SystemStatusManager;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by Lizhangfeng on 2016/7/13 0013.
 * Description: Activity基本
 */
public abstract class BaseDataBindingActivity<T extends ViewDataBinding> extends AppCompatActivity {

    protected boolean useDefaultTitleBarColor;//状态栏颜色是否使用默认颜色
    protected T viewDataBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            //被杀死之后重新创建
            getSaveData(savedInstanceState);
        }
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        initBase();
        setUseDefaultTitleBarColor();
        if (useDefaultTitleBarColor) {
            //改变状态栏颜色;注意：此处一旦设置 android:fitsSystemWindows="false"将无效
            setTitleBarColor(R.color.bg_color);
        }
        initData();
    }

    /**
     * 通用的一些基本设置
     */
    private void initBase() {
        //如果存在actionBar，就隐藏(也可以通过主题AppTheme.NoActionBar隐藏)
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        //将新建的activity添加到stack里进行管理
        AppManager.getInstance().addActivity(this);

        //设置状态栏透明
        setTranslucentStatus();

        //状态栏使用默认主题颜色
        useDefaultTitleBarColor = true;

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        //此处activity被杀死可以保存数据
        super.onSaveInstanceState(outState, outPersistentState);
    }

    /**
     * 获取activity被杀死时候保存的数据
     *
     * @param savedInstanceState
     */
    public void getSaveData(Bundle savedInstanceState) {

    }

    public abstract int getLayoutId();//获取布局id

    public abstract void initData();//初始化数据

    public void setUseDefaultTitleBarColor() {
    }

    /**
     * 状态栏透明只有Android 4.4 以上才支持
     */
    public void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            window.setAttributes(layoutParams);
        }
    }

    /**
     * 设置状态栏背景颜色
     */
    public void setTitleBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }
        SystemStatusManager tintManager = new SystemStatusManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(color);
    }

    @Override
    protected void onDestroy() {
        AppManager.getInstance().remove(this);
        //注：unregister官方是放入到onStop方法中，真实开发一般是放入onDestroy即当被销毁才取消注册
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}

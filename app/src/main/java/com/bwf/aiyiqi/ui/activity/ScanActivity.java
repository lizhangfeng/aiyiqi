package com.bwf.aiyiqi.ui.activity;

import android.graphics.Bitmap;

import com.bwf.aiyiqi.R;
import com.bwf.aiyiqi.common.base.BaseDataBindingActivity;
import com.bwf.aiyiqi.common.util.ToastUtil;
import com.bwf.aiyiqi.databinding.ActivityScanBinding;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

/**
 * 二维码
 */
public class ScanActivity extends BaseDataBindingActivity<ActivityScanBinding> implements CodeUtils.AnalyzeCallback {

    private CaptureFragment captureFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_scan;
    }

    @Override
    public void setUseDefaultTitleBarColor() {
        useDefaultTitleBarColor = false;
    }

    @Override
    public void initData() {
        captureFragment = (CaptureFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_scan);
        captureFragment.setAnalyzeCallback(this);
    }

    @Override
    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
        ToastUtil.showToast("二维码内容为： " + result);
        finish();
    }

    @Override
    public void onAnalyzeFailed() {
        ToastUtil.showToast("扫码失败! ");
        finish();
    }
}

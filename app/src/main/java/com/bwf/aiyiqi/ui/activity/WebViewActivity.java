package com.bwf.aiyiqi.ui.activity;

import com.bwf.aiyiqi.R;
import com.bwf.aiyiqi.common.Constants;
import com.bwf.aiyiqi.common.base.BaseDataBindingActivity;
import com.bwf.aiyiqi.databinding.ActivityWebViewBinding;

/**
 * 加载网页的WebView
 */
public class WebViewActivity extends BaseDataBindingActivity<ActivityWebViewBinding> {

    private String url;

    @Override
    public int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    public void initData() {

        String url = getIntent().getStringExtra(Constants.URL);
        viewDataBinding.webview.loadUrl(url);

    }
}

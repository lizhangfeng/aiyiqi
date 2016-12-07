package com.bwf.aiyiqi.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.bwf.aiyiqi.R;
import com.bwf.aiyiqi.common.base.BaseActivity;
import com.bwf.aiyiqi.common.util.ToastUtil;
import com.bwf.aiyiqi.ui.fragment.HomeFragment;
import com.bwf.aiyiqi.ui.fragment.MessageFragment;
import com.bwf.aiyiqi.ui.fragment.MineFragment;
import com.bwf.aiyiqi.ui.fragment.OwnersSayFragment;

/**
 * MainTab页面
 */
public class MainActivity extends BaseActivity implements Handler.Callback {

    private FragmentTabHost fragmentTabHost;

    private TabWidget tabWidget;

    private Class fragments[] = {HomeFragment.class, OwnersSayFragment.class, MessageFragment.class, MineFragment.class};

    /* tab文字和图片 */
    private String[] titles = {"首页", "业主说", "消息", "我的"};

    private Integer icons_normal[] = {R.mipmap.main_tab_home_normal, R.mipmap.main_tab_community_normal, R.mipmap.main_tab_msg_normal, R.mipmap.main_tab_my_normal};

    private Integer icons_select[] = {R.mipmap.main_tab_home_selected, R.mipmap.main_tab_community_selected, R.mipmap.main_tab_msg_selected, R.mipmap.main_tab_my_selected};

    private Handler handler;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void beforeInitView() {
        handler = new Handler(this);
    }

    @Override
    public void initView() {
        fragmentTabHost = findViewByIdNoCast(android.R.id.tabhost);
    }

    @Override
    public void initData() {
        //初始化
        fragmentTabHost.setup(this, getSupportFragmentManager(), R.id.layout_frame);

        tabWidget = fragmentTabHost.getTabWidget();
        tabWidget.setDividerDrawable(null);

        for (int i = 0; i < fragments.length; i++) {

            TabHost.TabSpec tabSpec = fragmentTabHost.newTabSpec(titles[i]).setIndicator(getItemView(i));
            fragmentTabHost.addTab(tabSpec, fragments[i], null);

        }

        //添加tab点击监听
        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabName) {

                for (int i = 0; i < tabWidget.getChildCount(); i++) {
                    View view = tabWidget.getChildTabViewAt(i);
                    TextView tab_title = (TextView) view.findViewById(R.id.tab_title);
                    ImageView tab_icon = (ImageView) view.findViewById(R.id.tab_icon);
                    if (tabName.equals(tab_title.getText().toString())) {//点击的tab
                        tab_icon.setImageResource(icons_select[i]);
                        tab_title.setTextColor(getResources().getColor(R.color.main_tab_select));
                    } else {
                        tab_icon.setImageResource(icons_normal[i]);
                        tab_title.setTextColor(getResources().getColor(R.color.main_tab_normal));
                    }

                }

            }
        });
    }

    public View getItemView(int position) {

        View view = View.inflate(this, R.layout.tab, null);
        TextView tab_title = (TextView) view.findViewById(R.id.tab_title);
        ImageView tab_icon = (ImageView) view.findViewById(R.id.tab_icon);
        tab_title.setText(titles[position]);
        if (position == 0) {
            tab_icon.setImageResource(icons_select[position]);
            tab_title.setTextColor(getResources().getColor(R.color.main_tab_select));
        } else {
            tab_icon.setImageResource(icons_normal[position]);
            tab_title.setTextColor(getResources().getColor(R.color.main_tab_normal));
        }

        return view;
    }


    @Override
    public void onClick(View view) {

    }

    private static final int TIMES = 2000;

    private boolean isBack = true;

    /**
     * 按下监听
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {//按下返回键

            if (isBack) {
                ToastUtil.showToast(getString(R.string.exit_app));
                isBack = false;
                handler.sendEmptyMessageDelayed(1, 2000);
            } else {
                //退出app
                System.exit(0);
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean handleMessage(Message msg) {

        switch (msg.what) {
            case 1:
                isBack = true;
                break;
        }

        return false;
    }

    @Override
    protected void onDestroy() {
        if (handler != null)
            handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}

package com.bwf.aiyiqi.ui.activity;

import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.bwf.aiyiqi.R;
import com.bwf.aiyiqi.common.base.BaseActivity;
import com.bwf.aiyiqi.ui.fragment.HomeFragment;
import com.bwf.aiyiqi.ui.fragment.MessageFragment;
import com.bwf.aiyiqi.ui.fragment.MineFragment;
import com.bwf.aiyiqi.ui.fragment.OwnersSayFragment;

/**
 * MainTab页面
 */
public class MainActivity extends BaseActivity {

    private FragmentTabHost fragmentTabHost;

    private TabWidget tabWidget;

    private Class fragments[] = {HomeFragment.class, OwnersSayFragment.class, MessageFragment.class, MineFragment.class};

    /* tab文字和图片 */
    private String[] titles = {"首页", "业主说", "消息", "我的"};

    private Integer icons_normal[] = {R.mipmap.main_tab_home_normal, R.mipmap.main_tab_community_normal, R.mipmap.main_tab_msg_normal, R.mipmap.main_tab_my_normal};

    private Integer icons_select[] = {R.mipmap.main_tab_home_selected, R.mipmap.main_tab_community_selected, R.mipmap.main_tab_msg_selected, R.mipmap.main_tab_my_selected};

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void beforeInitView() {

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
}

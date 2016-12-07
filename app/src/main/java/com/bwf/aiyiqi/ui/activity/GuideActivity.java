package com.bwf.aiyiqi.ui.activity;

import android.view.View;
import android.widget.RadioGroup;

import com.bwf.aiyiqi.R;
import com.bwf.aiyiqi.common.base.BaseDataBindingActivity;
import com.bwf.aiyiqi.common.util.IntentUtils;
import com.bwf.aiyiqi.databinding.ActivityGuideBinding;
import com.bwf.aiyiqi.ui.view.wheel.DateWheelPicker;

import java.util.Calendar;

/**
 * 了解使用者
 */
public class GuideActivity extends BaseDataBindingActivity<ActivityGuideBinding> implements RadioGroup.OnCheckedChangeListener {

    /* 滚动的日期 */
    private int currentYear, currentMonth, currentDay;

    /* 选择的日期 */
    private int selectYear, selectMonth, selectDay;

    @Override
    public int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    public void initData() {
        //获取当前年月日
        Calendar calendar = Calendar.getInstance();
        selectYear = calendar.get(Calendar.YEAR);
        selectMonth = calendar.get(Calendar.MONTH) + 1;
        selectDay = calendar.get(Calendar.DAY_OF_MONTH);

        viewDataBinding.wheelDatapicker.setDefaultDate(selectYear, selectMonth, selectDay);

        viewDataBinding.wheelDatapicker.setOnChangeListener(new DateWheelPicker.OnChangeListener() {
            @Override
            public void onChange(int year, int month, int day, int day_of_week) {
                currentYear = year;
                currentMonth = month;
                currentDay = day;
            }
        });

        viewDataBinding.rgSelectSex.setOnCheckedChangeListener(this);
        viewDataBinding.rgDecorationStep.setOnCheckedChangeListener(this);
        viewDataBinding.btnGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.openActivity(GuideActivity.this, MainActivity.class);
                finish();
            }
        });
    }

    private boolean checkStep;
    private boolean checkSex;

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (radioGroup == viewDataBinding.rgDecorationStep) {
            checkStep = true;
        } else if (radioGroup == viewDataBinding.rgSelectSex) {
            checkSex = true;
        }

        if (checkSex && checkStep) {
            viewDataBinding.btnGoHome.setEnabled(true);
            viewDataBinding.btnGoHome.setBackgroundColor(getResources().getColor(R.color.main_tab_select));
        } else {
            viewDataBinding.btnGoHome.setEnabled(false);
            viewDataBinding.btnGoHome.setBackgroundColor(getResources().getColor(R.color.about_you_btn_normal));
        }

    }
}

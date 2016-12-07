package com.bwf.aiyiqi.ui.view.wheel.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwf.aiyiqi.R;


/**
 * Created by lizhangfeng on 16/4/14.
 * description: 年月日选择日期的picker
 */
public class DateWheelAdapter extends AbstractWheelTextAdapter {

    /* 选择的位置 */
    private int selectPosition;

    private int minValue, maxValue;

    private String type;

    public DateWheelAdapter(Context context, int minValue, int maxValue, String type) {
        super(context, R.layout.nation_layout, NO_RESOURCE);
        setItemTextResource(R.id.nation_name);
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.type = type;
    }

    public int getSelectPosition() {
        return selectPosition;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinAndMaxValue(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public int getItemsCount() {
        return maxValue - minValue + 1;
    }

    @Override
    public View getItem(int index, View convertView, ViewGroup parent) {
        View view = super.getItem(index, convertView, parent);
        TextView tv = (TextView) view.findViewById(R.id.nation_name);
        tv.setText("" + (minValue + index) + type);
        if (index == selectPosition) {//选中日期的字体颜色
            tv.setTextColor(parent.getResources().getColor(R.color.main_tab_select));
        } else if (index - selectPosition == -1) {
//            tv.setTextColor(Color.parseColor("#B4B4B4"));
            //字体颜色渐变
            LinearGradient mLinearGradient = new LinearGradient(0, 0, 0, tv.getPaint().getTextSize(), Color.WHITE, Color.parseColor("#919191"), Shader.TileMode.CLAMP);
            tv.getPaint().setShader(mLinearGradient);
        } else if (index - selectPosition == 1) {
            //字体颜色渐变
            LinearGradient mLinearGradient = new LinearGradient(0, 0, 0, tv.getPaint().getTextSize(), Color.parseColor("#919191"), Color.WHITE, Shader.TileMode.CLAMP);
            tv.getPaint().setShader(mLinearGradient);
        } else {
            tv.setTextColor(Color.parseColor("#D8D8D8"));
        }

        return view;
    }

    @Override
    protected CharSequence getItemText(int index) {
        if (index >= 0 && index < getItemsCount()) {
            int value = minValue + index;
            return Integer.toString(value);
        }
        return null;
    }
}


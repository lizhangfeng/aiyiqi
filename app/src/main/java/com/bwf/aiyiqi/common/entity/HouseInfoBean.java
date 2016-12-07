package com.bwf.aiyiqi.common.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lizhangfeng on 2016/12/7 0007.
 * Description: 房屋信息
 */

public class HouseInfoBean implements Parcelable {
    /**
     * stage : 1
     * community : 哈哈
     * layout : 2
     * area : 100
     * budget : 15.0
     * style : 1
     */

    public String stage;
    public String community;
    public String layout;
    public String area;
    public String budget;
    public String style;


    @Override
    public String toString() {
        return "HouseInfoBean{" +
                "stage='" + stage + '\'' +
                ", community='" + community + '\'' +
                ", layout='" + layout + '\'' +
                ", area='" + area + '\'' +
                ", budget='" + budget + '\'' +
                ", style='" + style + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.stage);
        dest.writeString(this.community);
        dest.writeString(this.layout);
        dest.writeString(this.area);
        dest.writeString(this.budget);
        dest.writeString(this.style);
    }

    public HouseInfoBean() {
    }

    protected HouseInfoBean(Parcel in) {
        this.stage = in.readString();
        this.community = in.readString();
        this.layout = in.readString();
        this.area = in.readString();
        this.budget = in.readString();
        this.style = in.readString();
    }

    public static final Parcelable.Creator<HouseInfoBean> CREATOR = new Parcelable.Creator<HouseInfoBean>() {
        @Override
        public HouseInfoBean createFromParcel(Parcel source) {
            return new HouseInfoBean(source);
        }

        @Override
        public HouseInfoBean[] newArray(int size) {
            return new HouseInfoBean[size];
        }
    };
}

package com.bwf.aiyiqi.common.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lizhangfeng on 2016/12/6 0006.
 * Description:
 */

public class ForumBean implements Parcelable {

    /**
     * fid : 2
     * name : 北京装修论坛
     */

    public String fid;
    public String name;

    @Override
    public String toString() {
        return "ForumBean{" +
                "fid='" + fid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fid);
        dest.writeString(this.name);
    }

    public ForumBean() {
    }

    protected ForumBean(Parcel in) {
        this.fid = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<ForumBean> CREATOR = new Parcelable.Creator<ForumBean>() {
        @Override
        public ForumBean createFromParcel(Parcel source) {
            return new ForumBean(source);
        }

        @Override
        public ForumBean[] newArray(int size) {
            return new ForumBean[size];
        }
    };
}

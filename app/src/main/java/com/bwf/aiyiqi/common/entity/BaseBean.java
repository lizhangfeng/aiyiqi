package com.bwf.aiyiqi.common.entity;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lizhangfeng on 2016/11/25 0025.
 * Description: 基类
 */

public class BaseBean extends BaseObservable implements Parcelable {

    public String error;//error为0表示请求成功
    public String message;//code对应的信息
    public String data;//返回数据
    public String newCount;//
    public String currentPage;//
    public String totalCount;//


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.error);
        dest.writeString(this.message);
        dest.writeString(this.data);
        dest.writeString(this.newCount);
        dest.writeString(this.currentPage);
        dest.writeString(this.totalCount);
    }

    public BaseBean() {
    }

    protected BaseBean(Parcel in) {
        this.error = in.readString();
        this.message = in.readString();
        this.data = in.readString();
        this.newCount = in.readString();
        this.currentPage = in.readString();
        this.totalCount = in.readString();
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", data='" + data + '\'' +
                ", newCount='" + newCount + '\'' +
                ", currentPage='" + currentPage + '\'' +
                ", totalCount='" + totalCount + '\'' +
                '}';
    }
}

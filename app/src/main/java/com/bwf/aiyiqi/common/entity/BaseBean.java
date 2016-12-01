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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.error);
        dest.writeString(this.message);
        dest.writeString(this.data);
    }

    public BaseBean() {
    }

    protected BaseBean(Parcel in) {
        this.error = in.readString();
        this.message = in.readString();
        this.data = in.readString();
    }

    public static final Parcelable.Creator<BaseBean> CREATOR = new Parcelable.Creator<BaseBean>() {
        @Override
        public BaseBean createFromParcel(Parcel source) {
            return new BaseBean(source);
        }

        @Override
        public BaseBean[] newArray(int size) {
            return new BaseBean[size];
        }
    };

    @Override
    public String toString() {
        return "BaseResponse{" +
                "error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}

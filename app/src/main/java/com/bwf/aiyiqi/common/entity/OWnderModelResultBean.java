package com.bwf.aiyiqi.common.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Lizhangfeng on 2016/12/7 0007.
 * Description:
 */

public class OWnderModelResultBean implements Parcelable {

    public List<OwnerModelBean> all;
    public List<OwnerModelBean> choose;


    @Override
    public String toString() {
        return "OWnderModelResultBean{" +
                "all=" + all +
                ", choose=" + choose +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.all);
        dest.writeTypedList(this.choose);
    }

    public OWnderModelResultBean() {
    }

    protected OWnderModelResultBean(Parcel in) {
        this.all = in.createTypedArrayList(OwnerModelBean.CREATOR);
        this.choose = in.createTypedArrayList(OwnerModelBean.CREATOR);
    }

    public static final Parcelable.Creator<OWnderModelResultBean> CREATOR = new Parcelable.Creator<OWnderModelResultBean>() {
        @Override
        public OWnderModelResultBean createFromParcel(Parcel source) {
            return new OWnderModelResultBean(source);
        }

        @Override
        public OWnderModelResultBean[] newArray(int size) {
            return new OWnderModelResultBean[size];
        }
    };
}

package com.bwf.aiyiqi.common.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lizhangfeng on 2016/12/7 0007.
 * Description: 板块
 */

public class OwnerModelBean extends BaseBean implements Parcelable {


    /**
     * id : 22268
     * title : 一起美装
     * icon : http://static-news.17house.com/web/bbs/201605/06/1754441718.jpg
     * threadsnum : 90
     * postsnum : 207
     */

    public String id;
    public String title;
    public String icon;
    public String threadsnum;
    public int postsnum;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.icon);
        dest.writeString(this.threadsnum);
        dest.writeInt(this.postsnum);
    }

    public OwnerModelBean() {
    }

    protected OwnerModelBean(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.icon = in.readString();
        this.threadsnum = in.readString();
        this.postsnum = in.readInt();
    }

    public static final Parcelable.Creator<OwnerModelBean> CREATOR = new Parcelable.Creator<OwnerModelBean>() {
        @Override
        public OwnerModelBean createFromParcel(Parcel source) {
            return new OwnerModelBean(source);
        }

        @Override
        public OwnerModelBean[] newArray(int size) {
            return new OwnerModelBean[size];
        }
    };

    @Override
    public String toString() {
        return "OwnerModelBean{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", threadsnum='" + threadsnum + '\'' +
                ", postsnum=" + postsnum +
                '}';
    }
}

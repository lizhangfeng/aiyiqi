package com.bwf.aiyiqi.common.entity;

import android.os.Parcel;

/**
 * Created by Lizhangfeng on 2016/12/5 0005.
 * Description:首页广告
 */

public class HomePicBean extends BaseBean {

    /**
     * title : 岁末家装狂欢趴
     * imagesrc : http://appmanager.17house.com/upload/20161203/58428ed396b31_t.jpg
     * imagesrc2 : http://appmanager.17house.com/upload/20161203/58428ed3c8b51_t.jpg
     * tid :
     * type : 4
     * banner_url : http://wap.17house.com/tuan/2716.html
     */

    public String title;
    public String imagesrc;
    public String imagesrc2;
    public String tid;
    public String type;
    public String banner_url;


    @Override
    public String toString() {
        return "HomePicBean{" +
                "title='" + title + '\'' +
                ", imagesrc='" + imagesrc + '\'' +
                ", imagesrc2='" + imagesrc2 + '\'' +
                ", tid='" + tid + '\'' +
                ", type='" + type + '\'' +
                ", banner_url='" + banner_url + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.title);
        dest.writeString(this.imagesrc);
        dest.writeString(this.imagesrc2);
        dest.writeString(this.tid);
        dest.writeString(this.type);
        dest.writeString(this.banner_url);
    }

    public HomePicBean() {
    }

    protected HomePicBean(Parcel in) {
        super(in);
        this.title = in.readString();
        this.imagesrc = in.readString();
        this.imagesrc2 = in.readString();
        this.tid = in.readString();
        this.type = in.readString();
        this.banner_url = in.readString();
    }

    public static final Creator<HomePicBean> CREATOR = new Creator<HomePicBean>() {
        @Override
        public HomePicBean createFromParcel(Parcel source) {
            return new HomePicBean(source);
        }

        @Override
        public HomePicBean[] newArray(int size) {
            return new HomePicBean[size];
        }
    };
}

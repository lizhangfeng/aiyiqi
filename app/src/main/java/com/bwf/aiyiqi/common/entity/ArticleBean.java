package com.bwf.aiyiqi.common.entity;

import android.os.Parcel;

/**
 * Created by Lizhangfeng on 2016/12/5 0005.
 * Description: 文章
 */

public class ArticleBean extends BaseBean {

    /**
     * type : 3
     * id : 1219601
     * title : 20年老师傅装修上千套房的22条忠告，我家错了一半！
     * author : long-北京
     * uid : 1531237
     * avtUrl : http://bbs.17house.com/uc_server/avatar.php?uid=1531237&size=big
     * dateline : 2小时前
     * sort : 1480921486
     * views : 23
     * replies : 0
     * like_num : 1
     * path : http://static-news.17house.com/bbs/forum/201612/05/145240vpns1lps5glpiooz.jpg!w720
     * forum : {"fid":"2","name":"北京装修论坛"}
     * followed : 0
     */

    public String type;
    public String id;
    public String title;
    public String author;
    public String uid;
    public String avtUrl;
    public String dateline;
    public String sort;
    public String views;
    public String replies;
    public String like_num;
    public String path;
    public ForumBean forum;
    public String followed;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.type);
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.author);
        dest.writeString(this.uid);
        dest.writeString(this.avtUrl);
        dest.writeString(this.dateline);
        dest.writeString(this.sort);
        dest.writeString(this.views);
        dest.writeString(this.replies);
        dest.writeString(this.like_num);
        dest.writeString(this.path);
        dest.writeParcelable(this.forum, flags);
        dest.writeString(this.followed);
    }

    public ArticleBean() {
    }

    protected ArticleBean(Parcel in) {
        super(in);
        this.type = in.readString();
        this.id = in.readString();
        this.title = in.readString();
        this.author = in.readString();
        this.uid = in.readString();
        this.avtUrl = in.readString();
        this.dateline = in.readString();
        this.sort = in.readString();
        this.views = in.readString();
        this.replies = in.readString();
        this.like_num = in.readString();
        this.path = in.readString();
        this.forum = in.readParcelable(ForumBean.class.getClassLoader());
        this.followed = in.readString();
    }

    public static final Creator<ArticleBean> CREATOR = new Creator<ArticleBean>() {
        @Override
        public ArticleBean createFromParcel(Parcel source) {
            return new ArticleBean(source);
        }

        @Override
        public ArticleBean[] newArray(int size) {
            return new ArticleBean[size];
        }
    };

    @Override
    public String toString() {
        return "ArticleBean{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", uid='" + uid + '\'' +
                ", avtUrl='" + avtUrl + '\'' +
                ", dateline='" + dateline + '\'' +
                ", sort='" + sort + '\'' +
                ", views='" + views + '\'' +
                ", replies='" + replies + '\'' +
                ", like_num='" + like_num + '\'' +
                ", path='" + path + '\'' +
                ", forum=" + forum +
                ", followed='" + followed + '\'' +
                '}';
    }
}

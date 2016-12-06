package com.bwf.aiyiqi.common.entity;

import android.os.Parcel;

import java.util.List;

/**
 * Created by Lizhangfeng on 2016/12/6 0006.
 * Description:
 */

public class SearchBean extends BaseBean {


    /**
     * tid : 1217210
     * fid : 4997
     * groupname :
     * author : cqhjcm
     * authorid : 1522721
     * avtUrl : http://bbs.17house.com/uc_server/avatar.php?uid=1522721&size=big
     * subject : 重庆帝彤木门：双十一木门出哪几招呢？
     * dateline : 2016-11-17
     * lastpost : 2016-11-17
     * lastposter : cqhjcm
     * views : 14
     * replies : 0
     * fname : 【广告专区】
     * fstatus : 1
     * attachments : ["http://static-news.17house.com/bbs/forum/201611/17/145526yv1vv4ed33cxtk1r.jpg!w720","http://static-news.17house.com/bbs/forum/201611/17/145523chhjb4azj7gojar7.jpg!w720"]
     * displayorder : 0
     * typeid : 0
     * digest : 0
     * zan : 0
     * attachment : 2
     * sharetimes : 0
     * message : 　　这两天人们津津乐道的莫不是双十一，服装、食品、家居等都在争抢电商狂欢节市场。可以说，为了能够获得更大份额的市场以及盈利，大家都在使尽浑身解数，各出奇招。大牌企业大出风头搞预售，而板材企业的双十一又 ...
     */

    public String tid;
    public String fid;
    public String groupname;
    public String author;
    public String authorid;
    public String avtUrl;
    public String subject;
    public String dateline;
    public String lastpost;
    public String lastposter;
    public String views;
    public String replies;
    public String fname;
    public String fstatus;
    public String displayorder;
    public String typeid;
    public String digest;
    public String zan;
    public String attachment;
    public String sharetimes;
    public String message;
    public List<String> attachments;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.tid);
        dest.writeString(this.fid);
        dest.writeString(this.groupname);
        dest.writeString(this.author);
        dest.writeString(this.authorid);
        dest.writeString(this.avtUrl);
        dest.writeString(this.subject);
        dest.writeString(this.dateline);
        dest.writeString(this.lastpost);
        dest.writeString(this.lastposter);
        dest.writeString(this.views);
        dest.writeString(this.replies);
        dest.writeString(this.fname);
        dest.writeString(this.fstatus);
        dest.writeString(this.displayorder);
        dest.writeString(this.typeid);
        dest.writeString(this.digest);
        dest.writeString(this.zan);
        dest.writeString(this.attachment);
        dest.writeString(this.sharetimes);
        dest.writeString(this.message);
        dest.writeStringList(this.attachments);
    }

    public SearchBean() {
    }

    protected SearchBean(Parcel in) {
        super(in);
        this.tid = in.readString();
        this.fid = in.readString();
        this.groupname = in.readString();
        this.author = in.readString();
        this.authorid = in.readString();
        this.avtUrl = in.readString();
        this.subject = in.readString();
        this.dateline = in.readString();
        this.lastpost = in.readString();
        this.lastposter = in.readString();
        this.views = in.readString();
        this.replies = in.readString();
        this.fname = in.readString();
        this.fstatus = in.readString();
        this.displayorder = in.readString();
        this.typeid = in.readString();
        this.digest = in.readString();
        this.zan = in.readString();
        this.attachment = in.readString();
        this.sharetimes = in.readString();
        this.message = in.readString();
        this.attachments = in.createStringArrayList();
    }

    public static final Creator<SearchBean> CREATOR = new Creator<SearchBean>() {
        @Override
        public SearchBean createFromParcel(Parcel source) {
            return new SearchBean(source);
        }

        @Override
        public SearchBean[] newArray(int size) {
            return new SearchBean[size];
        }
    };

    @Override
    public String toString() {
        return "SearchBean{" +
                "tid='" + tid + '\'' +
                ", fid='" + fid + '\'' +
                ", groupname='" + groupname + '\'' +
                ", author='" + author + '\'' +
                ", authorid='" + authorid + '\'' +
                ", avtUrl='" + avtUrl + '\'' +
                ", subject='" + subject + '\'' +
                ", dateline='" + dateline + '\'' +
                ", lastpost='" + lastpost + '\'' +
                ", lastposter='" + lastposter + '\'' +
                ", views='" + views + '\'' +
                ", replies='" + replies + '\'' +
                ", fname='" + fname + '\'' +
                ", fstatus='" + fstatus + '\'' +
                ", displayorder='" + displayorder + '\'' +
                ", typeid='" + typeid + '\'' +
                ", digest='" + digest + '\'' +
                ", zan='" + zan + '\'' +
                ", attachment='" + attachment + '\'' +
                ", sharetimes='" + sharetimes + '\'' +
                ", message='" + message + '\'' +
                ", attachments=" + attachments +
                '}';
    }
}

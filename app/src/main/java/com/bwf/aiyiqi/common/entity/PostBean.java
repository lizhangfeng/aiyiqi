package com.bwf.aiyiqi.common.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Lizhangfeng on 2016/12/7 0007.
 * Description: 帖子
 */

public class PostBean extends BaseBean implements Parcelable {


    /**
     * tid : 1223277
     * pid : 2695235
     * fid : 2
     * fname : 北京装修论坛
     * fstatus : 1
     * subject : 全职妈妈改造50平米老房，唯有爱和美食不可辜负
     * message :
     * author : ponyo
     * authorid : 1395089
     * avtUrl : http://bbs.17house.com/uc_server/avatar.php?uid=1395089&size=big
     * dateline : 8小时前
     * views : 514
     * replies : 4
     * digest : 1
     * zan : 8
     * favtimes : 0
     * houseInfo : {"stage":"1","community":"哈哈","layout":"2","area":"100","budget":"15.0","style":"1"}
     * atlist : []
     * tags : [{"tagid":"22010","tagname":"生日礼物"},{"tagid":"24916","tagname":"板门店"},{"tagid":"4002","tagname":"儿童房"},{"tagid":"14624","tagname":"装修网"},{"tagid":"13528","tagname":"网名"}]
     * attachments : ["http://static-news.17house.com/bbs/forum/201612/06/162738a8sdlls8twzlwzt2.jpg!w720","http://static-news.17house.com/bbs/forum/201612/06/162744x4bombtd1pojsi8m.jpg!w720","http://static-news.17house.com/bbs/forum/201612/06/162744pj4mal9qq6qmmb75.jpg!w720","http://static-news.17house.com/bbs/forum/201612/06/162741kdpdwd6oqrswd8hd.jpg!w720","http://static-news.17house.com/bbs/forum/201612/06/162747su265x7c2z5y2zd2.jpg!w720","http://static-news.17house.com/bbs/forum/201612/06/162757emddldeue9ald9ap.jpg!w720","http://static-news.17house.com/bbs/forum/201612/06/162740i1a047nriiiaiawp.jpg!w720","http://static-news.17house.com/bbs/forum/201612/06/162754dixrvudxdv6xdfre.jpg!w720","http://static-news.17house.com/bbs/forum/201612/06/162749kc51cjt95sczww3w.jpg!w720","http://static-news.17house.com/bbs/forum/201612/06/162753be0kci447ojzc33e.jpg!w720","http://static-news.17house.com/bbs/forum/201612/06/162754od5xgvg1e0et501z.jpg!w720","http://static-news.17house.com/bbs/forum/201612/06/162748zbb411jztdbta88j.jpg!w720","http://static-news.17house.com/bbs/forum/201612/06/162746vbsx5c5s5vzbxcyx.jpg!w720","http://static-news.17house.com/bbs/forum/201612/06/162800a5fu6miuiwtuh1su.jpg!w720","http://static-news.17house.com/bbs/forum/201612/06/162756k4f8fkffjcot2et4.jpg!w720","http://static-news.17house.com/bbs/forum/201612/06/162752xj1u8a8wo89gij5i.jpg!w720","http://static-news.17house.com/bbs/forum/201612/06/162755hgdn8m7ggzhdudss.jpg!w720","http://static-news.17house.com/bbs/forum/201612/06/162751a63uzibyim63595p.jpg!w720","http://static-news.17house.com/bbs/forum/201612/06/162758x6j4hy4wiwdogzyz.jpg!w720","http://static-news.17house.com/bbs/forum/201612/06/162757m7c82re2e22rw293.jpg!w720"]
     */

    public String tid;
    public String pid;
    public String fid;
    public String fname;
    public String fstatus;
    public String subject;
    public String message;
    public String author;
    public String authorid;
    public String avtUrl;
    public String dateline;
    public String views;
    public String replies;
    public String digest;
    public String zan;
    public String favtimes;
    public String houseInfo;
    public List<TagsBean> tags;
    public List<String> attachments;

    public static class TagsBean implements Parcelable {
        /**
         * tagid : 22010
         * tagname : 生日礼物
         */

        public String tagid;
        public String tagname;


        @Override
        public String toString() {
            return "TagsBean{" +
                    "tagid='" + tagid + '\'' +
                    ", tagname='" + tagname + '\'' +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.tagid);
            dest.writeString(this.tagname);
        }

        public TagsBean() {
        }

        protected TagsBean(Parcel in) {
            this.tagid = in.readString();
            this.tagname = in.readString();
        }

        public static final Parcelable.Creator<TagsBean> CREATOR = new Parcelable.Creator<TagsBean>() {
            @Override
            public TagsBean createFromParcel(Parcel source) {
                return new TagsBean(source);
            }

            @Override
            public TagsBean[] newArray(int size) {
                return new TagsBean[size];
            }
        };
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tid);
        dest.writeString(this.pid);
        dest.writeString(this.fid);
        dest.writeString(this.fname);
        dest.writeString(this.fstatus);
        dest.writeString(this.subject);
        dest.writeString(this.message);
        dest.writeString(this.author);
        dest.writeString(this.authorid);
        dest.writeString(this.avtUrl);
        dest.writeString(this.dateline);
        dest.writeString(this.views);
        dest.writeString(this.replies);
        dest.writeString(this.digest);
        dest.writeString(this.zan);
        dest.writeString(this.favtimes);
        dest.writeTypedList(this.tags);
        dest.writeStringList(this.attachments);
    }

    public PostBean() {
    }

    protected PostBean(Parcel in) {
        this.tid = in.readString();
        this.pid = in.readString();
        this.fid = in.readString();
        this.fname = in.readString();
        this.fstatus = in.readString();
        this.subject = in.readString();
        this.message = in.readString();
        this.author = in.readString();
        this.authorid = in.readString();
        this.avtUrl = in.readString();
        this.dateline = in.readString();
        this.views = in.readString();
        this.replies = in.readString();
        this.digest = in.readString();
        this.zan = in.readString();
        this.favtimes = in.readString();
        this.tags = in.createTypedArrayList(TagsBean.CREATOR);
        this.attachments = in.createStringArrayList();
    }

    public static final Parcelable.Creator<PostBean> CREATOR = new Parcelable.Creator<PostBean>() {
        @Override
        public PostBean createFromParcel(Parcel source) {
            return new PostBean(source);
        }

        @Override
        public PostBean[] newArray(int size) {
            return new PostBean[size];
        }
    };

    @Override
    public String toString() {
        return "PostBean{" +
                "tid='" + tid + '\'' +
                ", pid='" + pid + '\'' +
                ", fid='" + fid + '\'' +
                ", fname='" + fname + '\'' +
                ", fstatus='" + fstatus + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", author='" + author + '\'' +
                ", authorid='" + authorid + '\'' +
                ", avtUrl='" + avtUrl + '\'' +
                ", dateline='" + dateline + '\'' +
                ", views='" + views + '\'' +
                ", replies='" + replies + '\'' +
                ", digest='" + digest + '\'' +
                ", zan='" + zan + '\'' +
                ", favtimes='" + favtimes + '\'' +
                ", houseInfo=" + houseInfo +
                ", tags=" + tags +
                ", attachments=" + attachments +
                '}';
    }
}

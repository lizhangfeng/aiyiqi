package com.bwf.aiyiqi.common.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Lizhangfeng on 2016/12/6 0006.
 * Description:
 */
@Entity
public class SearchHistory {

    @Id(autoincrement = true)
    private Long id;//自增长id

    private String content;//搜索的内容

    @Property(nameInDb = "TIME")//可以指定数据库中存储名称
    private long time;//搜索的时间

    @Generated(hash = 662971995)
    public SearchHistory(Long id, String content, long time) {
        this.id = id;
        this.content = content;
        this.time = time;
    }

    @Generated(hash = 1905904755)
    public SearchHistory() {
    }

    public void setSearchHistory(SearchHistory history){
        setTime(history.getTime());
        setContent(history.getContent());
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }


}

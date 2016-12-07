package com.bwf.aiyiqi.common.http;

/**
 * Created by Lizhangfeng on 2016/11/30 0030.
 * Description:接口存储
 */

public enum ServeInterface {

    //首页广告
    HOME_PIC("http://appapi.17house.com/AppManagerApi.php?version=1&action=getownerinfo&cityId=2&model=android"),

    //首页列表
    HOME_LIST("http://bbs.17house.com/motnt/index.php?a=appindex&c=index&id=1218226&uuid=86305803367590&pageSize=10&uid=1633055&m=misc&type=3&page=1&haspermission=yes&model=android&sessionToken=6U49kCYKE260RqvPqEdFsBGskNQStKhm&app_version=android_com.aiyiqi.galaxy_1.1"),

    //搜索结果
    SEARCH("http://bbs.17house.com/motnt/index.php?a=searchForum&c=search&uuid=86305803367590&pageSize=10&m=search&page=1&haspermission=yes&kw=%E5%8F%8C%E5%8D%81%E4%B8%80%0A&model=android&sessionToken=&app_version=android_com.aiyiqi.galaxy_1.1"),

    //精华
    OWNERS_ESSENCE("http://bbs.17house.com/motnt/index.php?a=allThread&c=forumThreadList&mode=digest&uuid=86305803367590&pageSize=10&m=forum&page=1&haspermission=yes&model=android&sessionToken=&app_version=android_com.aiyiqi.galaxy_1.1"),
    //最新
    OWNERS_NEWEST("http://bbs.17house.com/motnt/index.php?a=allThread&c=forumThreadList&uuid=86305803367590&pageSize=10&cityName=%E6%88%90%E9%83%BD&m=forum&page=1&haspermission=yes&model=android&sessionToken=&app_version=android_com.aiyiqi.galaxy_1.1"),
    //板块
    OWNERS_MODEL("http://bbs.17house.com/motnt/index.php?a=miscForum&uuid=86305803367590&cityName=%E6%88%90%E9%83%BD&m=misc&haspermission=yes&cityId=2&model=android&sessionToken=&app_version=android_com.aiyiqi.galaxy_1.1"),;


    private String apiUrl;

    ServeInterface(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }
}

package com.bwf.aiyiqi.common.http;

/**
 * Created by Lizhangfeng on 2016/11/30 0030.
 * Description:接口存储
 */

public enum ServeInterface {

    HOME_PIC("http://appapi.17house.com/AppManagerApi.php?version=1&action=getownerinfo&cityId=2&model=android"),

    HOME_LIST("http://bbs.17house.com/motnt/index.php?a=appindex&c=index&id=1218226&uuid=86305803367590&pageSize=10&uid=1633055&m=misc&type=3&page=1&haspermission=yes&model=android&sessionToken=6U49kCYKE260RqvPqEdFsBGskNQStKhm&app_version=android_com.aiyiqi.galaxy_1.1"),

    SEARCH("http://bbs.17house.com/motnt/index.php?a=searchForum&c=search&uuid=86305803367590&pageSize=10&m=search&page=1&haspermission=yes&kw=%E5%8F%8C%E5%8D%81%E4%B8%80%0A&model=android&sessionToken=&app_version=android_com.aiyiqi.galaxy_1.1"),

    ;


    private String apiUrl;

    ServeInterface(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }
}

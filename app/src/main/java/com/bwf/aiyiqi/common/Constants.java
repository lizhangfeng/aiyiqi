package com.bwf.aiyiqi.common;

import android.os.Environment;

import java.io.File;

/**
 * Created by lizhangfeng on 16/3/29.
 * <p>
 * description: 常量类
 */
public class Constants {

    public static final String DB_NAME = "aiyiqi_db";//数据库名称

    public static final String URL = "url";//

    /* 图片保存路径 */
    public static final String PHOTO_URI = "/BWF/DEMOS/Image";

    /**
     * 用来标识请求照相功能的activity
     */
    public static final int CAMERA_WITH_DATA = 3021;
    /**
     * 用来标识请求相册的activity
     */
    public static final int PHOTO_PICKED_WITH_DATA = 3022;
    /**
     * 用跳转剪切
     */
    public static final int CROP_BIG_PICTURE = 3024;
    /**
     * 拍照的照片存储位置
     */
    public static final File PHOTO_DIR = new File(Environment.getExternalStorageDirectory() + Constants.PHOTO_URI);


}

package com.bwf.aiyiqi.common.db;

import android.database.sqlite.SQLiteDatabase;

import com.bwf.aiyiqi.common.Constants;
import com.bwf.aiyiqi.common.db.bean.DaoMaster;
import com.bwf.aiyiqi.common.db.bean.DaoSession;
import com.bwf.aiyiqi.ui.App;

/**
 * Created by Lizhangfeng on 2016/12/6 0006.
 * Description: greenDao的封装
 */

public class GreenDaoUtils {

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private static GreenDaoUtils greenDaoUtils;

    private GreenDaoUtils(){}

    public static GreenDaoUtils getInstance(){
        if (greenDaoUtils==null){
            greenDaoUtils=new GreenDaoUtils();
        }
        return greenDaoUtils;
    }

    private void initGreenDao(){
        mHelper=new DaoMaster.DevOpenHelper(App.getAppContext(), Constants.DB_NAME,null);
        db=mHelper.getWritableDatabase();
        mDaoMaster=new DaoMaster(db);
        mDaoSession=mDaoMaster.newSession();
    }

    public DaoSession getmDaoSession() {
        if (mDaoMaster==null){
            initGreenDao();
        }
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        if (db==null){
            initGreenDao();
        }
        return db;
    }

}

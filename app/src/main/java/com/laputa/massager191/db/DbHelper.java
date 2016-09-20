package com.laputa.massager191.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/9/14.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Laputa";
    private static final int DB_VERSION = 1;

    public static String TABLE_HISTORY = "History";
    public static String HISTORY_DATE = "HISTORY_DATE";
    public static String HISTORY_ID = "HISTORY_ID";
    public static String HISTORY_ADDRESS = "HISTORY_ADDRESS";
    public static String HISTORY_POWER = "HISTORY_POWER";
    public static String HISTORY_PATTERN = "HISTORY_PATTERN";

    public DbHelper(Context context) {
        //第三个参数CursorFactory指定在执行查询时获得一个游标实例的工厂类,设置为null,代表使用系统默认的工厂类
        super(context, DB_NAME, null, DB_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + TABLE_HISTORY + " ("
                + HISTORY_ID + " integer primary key autoincrement" +
                ", " + HISTORY_DATE + " varchar(8)" +
                ", " + HISTORY_ADDRESS + "  varchar(20)" +
                ", " + HISTORY_POWER + "  INTEGER" +
                ", " + HISTORY_PATTERN + "  INTEGER" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

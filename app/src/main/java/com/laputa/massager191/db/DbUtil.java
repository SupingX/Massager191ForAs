package com.laputa.massager191.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.laputa.massager191.bean.History;
import com.laputa.massager191.util.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/9/14.
 */
public class DbUtil {

    private final SQLiteDatabase db;
    private static DbUtil dbUtil;

    public static DbUtil getInstance(Context context) {
        if (dbUtil == null) {
            dbUtil = new DbUtil(context);
        }
        return dbUtil;
    }

    private DbUtil(Context context) {
        DbHelper helper = new DbHelper(context);
        db = helper.getWritableDatabase();
    }

    public boolean add(History history) {
        // TODO: 2016/9/14  add
     /*   String sql = "";
        db.execSQL("insert into book (name, author, pages, price) values(?, ?, ?, ?)",
                new String[] { "The Da Vinci Code", "Dan Brown", "454", "16.96" });
        */
        ContentValues values = new ContentValues();
        values.put(DbHelper.HISTORY_ADDRESS, history.getAddress());
        values.put(DbHelper.HISTORY_DATE, history.getDate());
        values.put(DbHelper.HISTORY_PATTERN, history.getModel());
        values.put(DbHelper.HISTORY_POWER, history.getPower());
        return db.insert(DbHelper.TABLE_HISTORY, null, values) != -1;
    }

    public boolean delete(String date) {
        // TODO: 2016/9/14  delete
        ContentValues values = new ContentValues();
        try {
            if (date != null) {
                String sql = "delete from " + DbHelper.TABLE_HISTORY
                        + " where " + DbHelper.HISTORY_DATE + " like ?";
                db.execSQL(sql, new String[]{date + "%"});
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    public boolean update(History history) {
        // TODO: 2016/9/14  update
        try {
            if (history != null) {
                String sql = "update " + DbHelper.TABLE_HISTORY + " set "
                        + DbHelper.HISTORY_ADDRESS + " = ?"
                        + "," + DbHelper.HISTORY_PATTERN + " = ?"
                        + "," + DbHelper.HISTORY_POWER + " = ?"
                        + " where " + DbHelper.HISTORY_DATE + " like ? ";
                db.execSQL(sql, new String[]{history.getAddress(), history.getModel() + "", history.getPower() + "", history.getDate() + "%"});
                return true;
            }
        } catch (Exception e) {

        }
        return false;

    }


    public List<History> find(String date) {
        // TODO: 2016/9/14  find
        String sql;
        Cursor cursor;
        try {
            if (date != null) {
                sql = "select * from " + DbHelper.TABLE_HISTORY + " where "+DbHelper.HISTORY_DATE+" like ?";
                cursor = db.rawQuery(sql, new String[]{(date + "%")});
            } else {
                sql = "select * from " + DbHelper.TABLE_HISTORY;
                cursor = db.rawQuery(sql, null);
            }
            Log.e("","cursor : " + cursor);
            if (cursor != null) {
                List<History> list = new ArrayList<>();
                while (cursor.moveToNext()) {
                    String _date = cursor.getString(cursor.getColumnIndex(DbHelper.HISTORY_DATE));
                    String _address = cursor.getString(cursor.getColumnIndex(DbHelper.HISTORY_ADDRESS));
                    int _power = cursor.getInt(cursor.getColumnIndex(DbHelper.HISTORY_POWER));
                    int _pattern = cursor.getInt(cursor.getColumnIndex(DbHelper.HISTORY_PATTERN));
                    History history = new History(_date, _power, _pattern);
                    list.add(history);
                }
                cursor.close();
                return list;
            }
        } catch (Exception e) {
        }
        return null;
    }


    public void loadDataForTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 100; i > 0; i--) {
                       Log.e("","i :" + i);
                        Calendar c = Calendar.getInstance();
                        c.setTime( DateUtil.getDateOfDiffDay(new Date(),i));
                        String date = DateUtil.dateToString(c.getTime(),"yyyyMMdd hh:mm:ss");
                        int power = i;
                        int pattern = i % 10;
                        History history = new History(date, power, pattern);
                        add(history);
                        Thread.sleep(100);
                    }
                }catch (Exception e){

                }
            }
        }).start();
    }

}

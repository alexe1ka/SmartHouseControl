package com.example.alexe1ka.iotalexe1ka.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by alexe1ka on 08.01.2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "temperBase.db";
    public static final String DATABASE_TABLE_NAME = "temperature";
    public static final String DATABASE_COLUMN_ID = "id";
    public static final String DATABASE_COLUMN_TEMPVALUE = "tempvalue";
    public static final String DATABASE_COLUMN_TIME = "time";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table temperature" + "(id integer primary key,tempvalue text,time text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS temperature");
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String tempvalue, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tempvalue", tempvalue);
        contentValues.put("time", time);
        db.insert("temperature", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from contacts where id=" + id + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, DATABASE_TABLE_NAME);
        return numRows;
    }

    public boolean updateData(Integer id, String tempvalue, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tempvalue", tempvalue);
        contentValues.put("time", time);
        db.update("temperature", contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteContact(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts", "id = ? ", new String[]{Integer.toString(id)});
    }

    public ArrayList<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from contacts", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(DATABASE_TABLE_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
}

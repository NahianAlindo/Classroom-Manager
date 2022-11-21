package com.nrifaat26.classroommanager;

/**
 * Created by Nahian Alindo on 6/8/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mylist.db";
    public static final String TABLE_NAME = "mylist_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "Period";
    public static final String COL3 = "Room";
    public static final String COL4 = "Alloc_status";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " Period TEXT , "+" Room TEXT ,"+" Alloc_status TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String Period, String Room, String Alloc_status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, Period);
        contentValues.put(COL3, Room);
        contentValues.put(COL4, Alloc_status);

        long result = db.insertOrThrow(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Cursor getListContents(String period){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT Period,Room FROM mylist_data WHERE  Alloc_status=0 AND Period=\""+period+"\"", null);
        //Cursor data2 = db.rawQuery("SELECT Room,Period FROM mylist_data WHERE  Alloc_status=0 ", null);
        return data;
    }
}
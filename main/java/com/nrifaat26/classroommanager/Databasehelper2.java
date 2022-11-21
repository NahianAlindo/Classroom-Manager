package com.nrifaat26.classroommanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Environment;

/**
 * Created by Nahian Alindo on 7/16/2018.
 */

public class Databasehelper2 extends SQLiteOpenHelper {
private static String DB_NAME="Manual_Routine.db";
private static String DB_PATH="";
private SQLiteDatabase myDatabase;
public final Context myContext;
    public Databasehelper2(Context context) {
        super(context, DB_NAME, null, 1);
        if (Build.VERSION.SDK_INT>=15){
            DB_PATH=context.getApplicationInfo().dataDir+"/databases/";

        }
        else{
            DB_PATH= Environment.getDataDirectory()+"/data/"+context.getPackageName()+"/databases/";
        }
        this.myContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

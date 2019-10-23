package com.webee.test.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final int VERSION_DB = 1;


    private static final String TABLE_DEVICES = "devices";
    private static final String CREATE_TABLE_DEVICES = "CREATE TABLE " + TABLE_DEVICES +
            " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "macAddress  CHAR(50), name TEXT ," +
            "admissionDate DATE)";

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DEVICES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

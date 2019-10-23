package com.webee.test.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.webee.test.model.Device;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final int VERSION_DB = 1;
    public static final String NAME_DB = "webee";


    public static final String TABLE_DEVICES = "devices";
    private static final String CREATE_TABLE_DEVICES = "CREATE TABLE " + TABLE_DEVICES +
            " (" + DeviceDao.FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DeviceDao.FIELD_MAC_ADDRESS + " CHAR(17) NOT NULL, name TEXT NOT NULL," +
            DeviceDao.FIELD_ADMISSION_DATE +" DATE)";

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

package com.webee.test.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.concurrent.atomic.AtomicInteger;

abstract class DAO {

    private static DataBaseHelper dbHelper;

    private AtomicInteger mOpenCounter = new AtomicInteger();
    private SQLiteDatabase db;

    public DAO(Context context) {
        dbHelper = new DataBaseHelper(context, DataBaseHelper.NAME_DB, null,DataBaseHelper.VERSION_DB);
    }

    public synchronized SQLiteDatabase openDatabase(){
        if(mOpenCounter.incrementAndGet() == 1){
            db = dbHelper.getWritableDatabase();
        }
        return db;
    }

    public synchronized void closeDatabase(){
        if(mOpenCounter.decrementAndGet() == 0){
            db.close();
        }
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}

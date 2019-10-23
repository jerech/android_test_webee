package com.webee.test.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.webee.test.model.Device;
import com.webee.test.util.AppUtils;
import com.webee.test.util.AppUtilsDB;

import java.util.ArrayList;
import java.util.HashMap;

public class DeviceDao extends DAO {

    public static final String FIELD_ID = "id";
    public static final String FIELD_MAC_ADDRESS = "macAddress";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_ADMISSION_DATE = "admissionDate";

    private static DeviceDao deviceDao;

    public static DeviceDao getInstance(Context context) {

        if (deviceDao == null)
            deviceDao = new DeviceDao(context);
        return deviceDao;
    }

    private DeviceDao(Context context) {
        super(context);
    }

    public void insert(Device data) {
        openDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_MAC_ADDRESS, data.getMacAddress());
        values.put(FIELD_NAME, data.getName());
        values.put(FIELD_ADMISSION_DATE, AppUtils.formatDate(data.getAdmissionDate(), "yyyy-MM-dd"));


        getDb().insert(DataBaseHelper.TABLE_DEVICES, null, values);

        closeDatabase();

        Log.i("TAG", "Insert Device");
    }

    private void update(Device data) {

        openDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_MAC_ADDRESS, data.getMacAddress());
        values.put(FIELD_NAME, data.getName());
        values.put(FIELD_ADMISSION_DATE, AppUtils.formatDate(data.getAdmissionDate(), "yyyy-MM-dd"));

        String[] args = new String[]{data.getId() + ""};
        getDb().update(DataBaseHelper.TABLE_DEVICES, values, "id=?", args);

        closeDatabase();

        Log.i("TAG", "Update Device");
    }

    public ArrayList<Device> getAll(HashMap<String, String> wheres, String ordenCampo,
                                               String ordenTipo){

        openDatabase();
        ArrayList<Device> all = new ArrayList<Device>();

        String query = AppUtilsDB.getQuery(DataBaseHelper.TABLE_DEVICES, wheres, ordenCampo, ordenTipo);
        String selectionArgs[] = AppUtilsDB.getSelectionArgs(wheres);

        Cursor cursor = getDb().rawQuery(query , selectionArgs);

        if(cursor.moveToFirst()){
            do{
                Device temp = new Device(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        AppUtils.parseDate(cursor.getString(3), "yyyy-MM-dd")
                );

                all.add(temp);

            }while(cursor.moveToNext());
        }

        closeDatabase();

        return all;
    }
}

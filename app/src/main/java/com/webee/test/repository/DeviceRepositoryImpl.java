package com.webee.test.repository;

import android.content.Context;
import android.graphics.Movie;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.webee.test.database.DeviceDao;
import com.webee.test.model.Device;

import java.util.HashMap;
import java.util.List;

public class DeviceRepositoryImpl implements IDeviceRepository {

    private Context context;

    public DeviceRepositoryImpl(Context context) {
        this.context = context;
    }

    @Override
    public void insertDevice(Device device) {
        new InsertMovieAsyncTask(context).execute(device);
    }

    @Override
    public void updateDevice(Device device) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LiveData<List<Device>> getAllDevices() {
        MutableLiveData mutableLiveData = new MutableLiveData();
        new GetAllMoviesTask(context, mutableLiveData).execute();
        return mutableLiveData;
    }

    private static class InsertMovieAsyncTask extends AsyncTask<Device, Void, Void> {

        private DeviceDao deviceDao;

        public InsertMovieAsyncTask(Context context) {
            this.deviceDao = DeviceDao.getInstance(context);
        }

        @Override
        protected Void doInBackground(Device... devices) {
            deviceDao.insert(devices[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void v){

        }
    }

    private static class GetAllMoviesTask extends AsyncTask<Void, Void, List<Device>> {
        private DeviceDao deviceDao;
        private MutableLiveData<List<Device>> mutableLiveData;

        private GetAllMoviesTask(Context context, MutableLiveData mutableLiveData) {
            this.deviceDao = DeviceDao.getInstance(context);
            this.mutableLiveData = mutableLiveData;
        }

        @Override
        protected List<Device> doInBackground(Void... voids) {
            HashMap<String, String> wheres = new HashMap<>();
            List<Device> devices = deviceDao.getAll(wheres, DeviceDao.FIELD_ID, "asc");
            return devices;
        }

        @Override
        protected void onPostExecute(List<Device> devices){

            mutableLiveData.setValue(devices);
        }
    }


}

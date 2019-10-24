package com.webee.test.viewmodel;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.webee.test.model.Device;
import com.webee.test.repository.DeviceRepositoryImpl;
import com.webee.test.repository.IDeviceRepository;
import com.webee.test.util.AppUtils;

import java.util.Date;
import java.util.List;


public class AddDeviceViewModel extends AndroidViewModel {

    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> macAddress = new ObservableField<>();
    public final ObservableField<String> date = new ObservableField<>();

    public final ObservableField<String> errorName = new ObservableField<>();
    public final ObservableField<String> errorMacAddress = new ObservableField<>();
    public final ObservableField<String> errorDate = new ObservableField<>();

    private IDeviceRepository deviceRepository;

    public AddDeviceViewModel(@NonNull Application application) {
        super(application);
        deviceRepository = new DeviceRepositoryImpl(application);
    }

    public void insertDevice() {
        if(!isValid()) {
            return;
        }
        deviceRepository.insertDevice(new Device(0, macAddress.get(), name.get(), AppUtils.parseDate(date.get(), "dd/MM/yyyy")));
    }


    public LiveData<List<Device>> getAllDevices() {
        return deviceRepository.getAllDevices();
    }

    private boolean isValid() {
        errorName.set(null);
        errorMacAddress.set(null);
        errorDate.set(null);

        boolean valid = true;
        if(TextUtils.isEmpty(name.get())) {
            errorName.set("Obligatory field!");
            valid = false;
        }

        if(TextUtils.isEmpty(macAddress.get()) || macAddress.get().length() < 17) {
            errorMacAddress.set("Too short!");
            valid = false;
        }

        Date limitDate = AppUtils.parseDate("01-01-2018", "dd-MM-yyyy");
        Date deviceDate = AppUtils.parseDate(date.get(), "dd/MM/yyyy");
        if(deviceDate == null || deviceDate.before(limitDate)){
            errorDate.set("Invalid Date!");
            valid = false;
        }
        return valid;
    }
}

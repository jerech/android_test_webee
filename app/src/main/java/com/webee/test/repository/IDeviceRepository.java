package com.webee.test.repository;

import androidx.lifecycle.LiveData;

import com.webee.test.model.Device;

import java.util.List;

public interface IDeviceRepository {

    /**
     *
     * Insert new device in storage
     *
     * @param @{Device}
     * @return
     */
    public void insertDevice(Device device);

    /**
     *
     * Update new device in storage
     *
     * @param @{Device}
     * @return
     */
    public void updateDevice(Device device);


    /**
     *
     * Get all devices in storage
     *
     * @param
     * @return @{List<Device>}
     */
    public LiveData<List<Device>> getAllDevices();

}

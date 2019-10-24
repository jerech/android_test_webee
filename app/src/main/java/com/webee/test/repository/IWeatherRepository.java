package com.webee.test.repository;

import androidx.lifecycle.LiveData;

import com.webee.test.model.Device;
import com.webee.test.model.Weather;

import java.util.List;

public interface IWeatherRepository {



    /**
     *
     * Get weather
     *
     * @param
     * @return @{Weather}
     */
    public LiveData<Weather> getWeatherLiveData();

    public void callApiWeather(String city);

}

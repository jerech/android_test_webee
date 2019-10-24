package com.webee.test.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.webee.test.model.Device;
import com.webee.test.model.Weather;
import com.webee.test.repository.DeviceRepositoryImpl;
import com.webee.test.repository.IDeviceRepository;
import com.webee.test.repository.IWeatherRepository;
import com.webee.test.repository.WeatherRepositoryImpl;
import com.webee.test.util.AppUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class WeatherViewModel extends AndroidViewModel {

    private IWeatherRepository weatherRepository;
    Disposable disposable;

    public WeatherViewModel(@NonNull Application application) {
        super(application);
        weatherRepository = new WeatherRepositoryImpl(application);
    }


    public LiveData<Weather> getWeather() {

        disposable = Observable.interval(0, 4000,
                TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::callWeather);

        return weatherRepository.getWeatherLiveData();
    }

    private void callWeather(Long aLong) {
        weatherRepository.callApiWeather("Cordoba,arg");
    }


    public void stopTimer(){
        disposable.dispose();
    }





}

package com.webee.test.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.webee.test.R;
import com.webee.test.api.ApiClient;
import com.webee.test.api.ApiInterface;
import com.webee.test.database.DeviceDao;
import com.webee.test.dto.WeatherDTO;
import com.webee.test.model.Device;
import com.webee.test.model.Weather;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepositoryImpl implements IWeatherRepository {

    private Context context;
    private Disposable disposable;
    private ApiInterface apiService;
    private MutableLiveData mutableLiveData;

    public WeatherRepositoryImpl(Context context) {
        this.context = context;
        apiService = ApiClient.getClient(context)
                .create(ApiInterface.class);
    }


    @Override
    public LiveData<Weather> getWeatherLiveData() {
        mutableLiveData = new MutableLiveData();

        return mutableLiveData;
    }

    @Override
    public void callApiWeather(String city) {
        Observable<WeatherDTO> observable = apiService.getWeatherData(city, context.getString(R.string.api_key_weather), "metric");
        observable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .map(result -> result)
                .subscribe(this::handleResults, this::handleError);
    }


    private void handleResults(WeatherDTO weatherDTO) {
        if(weatherDTO != null) {
            Weather weather = new Weather();
            if(weatherDTO.getWeathers().size()>0) {
                weather.setDescription(weatherDTO.getWeathers().get(0).getDescription());
                weather.setMain(weatherDTO.getWeathers().get(0).getMain());
            }

            weather.setHumidity(weatherDTO.getMain().getHumidity());
            weather.setPressure(weatherDTO.getMain().getPressure());
            weather.setTemp(weatherDTO.getMain().getTemp());
            mutableLiveData.setValue(weather);
        }

    }

    private void handleError(Throwable t) {
        mutableLiveData.setValue(null);
    }


}

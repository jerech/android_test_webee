package com.webee.test.api;

import com.webee.test.dto.WeatherDTO;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jeremias on 3/05/17.
 */

public interface ApiInterface {



    @GET("weather")
    Observable<WeatherDTO> getWeatherData(@Query("q") String q,
                                          @Query("APPID") String APPID,
                                          @Query("units") String units);


}

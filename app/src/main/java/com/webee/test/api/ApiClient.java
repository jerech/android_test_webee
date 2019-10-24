package com.webee.test.api;

import android.content.Context;
import android.content.Intent;

import com.google.gson.GsonBuilder;
import com.webee.test.R;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jeremias on 24/10/19.
 */

public class ApiClient {


    private static Retrofit retrofit = null;
    private static ApiClient self = null;


    private ApiClient(){
    }




    public static Retrofit getClient(Context context) {

        if(self==null){
            self = new ApiClient();
        }
        String url = context.getResources().getString(R.string.url_api_weather);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .build();
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().excludeFieldsWithoutExposeAnnotation() .create()))
                    .build();
        }
        return retrofit;
    }




}

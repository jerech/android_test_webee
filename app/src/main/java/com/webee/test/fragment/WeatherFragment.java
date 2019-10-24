package com.webee.test.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webee.test.R;
import com.webee.test.model.Device;
import com.webee.test.model.Weather;
import com.webee.test.viewmodel.AddDeviceViewModel;
import com.webee.test.viewmodel.WeatherViewModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class WeatherFragment extends Fragment {

    private WeatherViewModel viewModel;

    private TextView tvTemp;
    private TextView tvDescription;
    private TextView tvHumidity;
    private TextView tvPressure;

    public WeatherFragment() {
        // Required empty public constructor
    }

    public static WeatherFragment newInstance() {
        WeatherFragment fragment = new WeatherFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);

        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        tvTemp = view.findViewById(R.id.tvTemp);
        tvDescription = view.findViewById(R.id.tvDescription);
        tvHumidity = view.findViewById(R.id.tvHumidity);
        tvPressure = view.findViewById(R.id.tvPressure);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        viewModel.getWeather().observe(this, weather -> {
            if(weather != null) {
                tvTemp.setText(String.valueOf(weather.getTemp()) + " C°");
                tvDescription.setText(weather.getDescription());
                tvHumidity.setText(weather.getHumidity() + " %");
                tvPressure.setText(weather.getPressure() + " hpa");
            }

        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onPause() {
        super.onPause();

        viewModel.stopTimer();
    }


}

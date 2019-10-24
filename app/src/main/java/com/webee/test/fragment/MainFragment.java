package com.webee.test.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.webee.test.R;


public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        Button btnAddDevice = view.findViewById(R.id.btnAddDevice);
        btnAddDevice.setOnClickListener( v -> {
                Navigation.findNavController(getActivity(), R.id.navHostFragment).navigate(R.id.addDeviceFragment);
            }
        );

        Button btnListDevices = view.findViewById(R.id.btnListDevices);
        btnListDevices.setOnClickListener(v-> {
                Navigation.findNavController(getActivity(), R.id.navHostFragment).navigate(R.id.listDevicesFragment);
        });

        Button btnWeather = view.findViewById(R.id.btnWeather);
        btnWeather.setOnClickListener(v -> {
                Navigation.findNavController(getActivity(), R.id.navHostFragment).navigate(R.id.weatherFragment);
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}

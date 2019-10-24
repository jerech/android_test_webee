package com.webee.test.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.webee.test.R;
import com.webee.test.model.Device;
import com.webee.test.util.AppUtils;

public class DeviceDetailFragment extends Fragment {

    private Device device;

    public DeviceDetailFragment() {
        // Required empty public constructor
    }


    public static DeviceDetailFragment newInstance() {
        DeviceDetailFragment fragment = new DeviceDetailFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           device = (Device) getArguments().getSerializable("device");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_device_detail, container, false);

        EditText etName = view.findViewById(R.id.etName);
        etName.setText(device.getName());

        EditText etMacAddress = view.findViewById(R.id.etMacAddress);
        etMacAddress.setText(device.getMacAddress());

        EditText etDate = view.findViewById(R.id.etDate);
        etDate.setText(AppUtils.formatDate(device.getAdmissionDate(), "dd/MM/yyyy"));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

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

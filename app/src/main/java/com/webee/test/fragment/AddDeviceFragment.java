package com.webee.test.fragment;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.webee.test.R;
import com.webee.test.databinding.FragmentAddDeviceBinding;
import com.webee.test.dialog.DatePickerFragment;
import com.webee.test.model.Device;
import com.webee.test.util.AppUtils;
import com.webee.test.viewmodel.AddDeviceViewModel;

import java.util.Calendar;

public class AddDeviceFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private AddDeviceViewModel viewModel;
    private Button btnAddDevice;
    private EditText etDate;

    public static AddDeviceFragment newInstance() {
        return new AddDeviceFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        FragmentAddDeviceBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_add_device, container, false);
        viewModel = ViewModelProviders.of(this).get(AddDeviceViewModel.class);
        binding.setModel(viewModel);

        View view = binding.getRoot();

        btnAddDevice = view.findViewById(R.id.btnAdd);
        btnAddDevice.setOnClickListener(v -> {
            viewModel.insertDevice();
          }
        );
        etDate = view.findViewById(R.id.etDate);
        etDate.setOnClickListener(v -> showDatePickerDialog());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        viewModel.getDeviceMutableLiveData().observe(this, device -> {
            if(device != null) {
                Toast.makeText(getContext(), "added device!", Toast.LENGTH_LONG).show();
                getActivity().onBackPressed();
            }
        });


    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(this);
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        etDate.setText(AppUtils.formatDate(calendar.getTime(), "dd/MM/yyyy"));

    }
}

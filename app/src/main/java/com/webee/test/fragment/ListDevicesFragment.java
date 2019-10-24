package com.webee.test.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.webee.test.R;
import com.webee.test.adapter.DevicesAdapter;
import com.webee.test.model.Device;
import com.webee.test.viewmodel.AddDeviceViewModel;

import java.util.List;


public class ListDevicesFragment extends Fragment implements DevicesAdapter.ItemClickListener{

    private RecyclerView rvDevices;
    private DevicesAdapter adapter;
    private AddDeviceViewModel viewModel;

    public ListDevicesFragment() {
        // Required empty public constructor
    }

    public static ListDevicesFragment newInstance() {
        ListDevicesFragment fragment = new ListDevicesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_devices, container, false);

        rvDevices = view.findViewById(R.id.rvDevices);
        adapter = new DevicesAdapter(this);

        rvDevices.setItemAnimator(new DefaultItemAnimator());
        rvDevices.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvDevices.setLayoutManager(linearLayoutManager);

        viewModel = ViewModelProviders.of(this).get(AddDeviceViewModel.class);
        viewModel.getAllDevices().observe(this, new Observer<List<Device>>() {
            @Override
            public void onChanged(List<Device> devices) {
                adapter.setDevices(devices);
            }
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

    @Override
    public void onItemClick(Device item) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("device", item);
        Navigation.findNavController(getActivity(), R.id.navHostFragment).navigate(R.id.deviceDetailFragment, bundle);
    }
}

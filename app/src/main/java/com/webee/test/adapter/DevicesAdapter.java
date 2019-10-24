package com.webee.test.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.webee.test.R;
import com.webee.test.model.Device;
import com.webee.test.util.AppUtils;

import java.util.ArrayList;
import java.util.List;

public class DevicesAdapter extends RecyclerView.Adapter<DevicesAdapter.MyViewHolder> {

    private ItemClickListener listener;
    private List<Device> listDevices;

    public interface ItemClickListener {
        void onItemClick(Device item);
    }

    public DevicesAdapter(ItemClickListener listener) {
        this.listener = listener;
        listDevices = new ArrayList<>();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvMacAddress, tvDate;

        public MyViewHolder(View rootView) {
            super(rootView);
            tvName = (TextView) rootView.findViewById(R.id.tvName);
            tvMacAddress = (TextView) rootView.findViewById(R.id.tvMacAddress);
            tvDate = (TextView) rootView.findViewById(R.id.tvDate);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_device, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Device device = listDevices.get(position);

        holder.tvName.setText(device.getName());
        holder.tvDate.setText(AppUtils.formatDate(device.getAdmissionDate(), "dd/MM/yyyy"));
        holder.tvMacAddress.setText(device.getMacAddress());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(device);
            }
        });





    }

    public void setDevices(List<Device> devices){
        this.listDevices = devices;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listDevices.size();
    }
}

package com.gvtech.serviceathome.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.models.Availability;
import com.gvtech.serviceathome.models.BusinessService;

import java.util.ArrayList;

public class AvailabilityAdapter extends RecyclerView.Adapter<AvailabilityAdapter.MyViewHolder> {

    private ArrayList<Availability> services;
    private Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtDay,txtStartTime,txtEndTime;

        public MyViewHolder(View view) {
            super(view);
            txtDay = (TextView)view.findViewById(R.id.txt_day);
            txtStartTime = (TextView)view.findViewById(R.id.txt_start_time);
            txtEndTime = (TextView)view.findViewById(R.id.txt_end_time);
        }
    }
    public AvailabilityAdapter(Context context, ArrayList<Availability> services) {
        this.services = services;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_availability, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtDay.setText(services.get(position).getDay());
        holder.txtStartTime.setText(services.get(position).getStartTime()+"");
        holder.txtEndTime.setText(services.get(position).getEndTime()+"");
    }

    @Override
    public int getItemCount() {
        return services.size();
    }
}

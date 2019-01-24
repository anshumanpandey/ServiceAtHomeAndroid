package com.gvtech.serviceathome.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.models.Availability;
import com.gvtech.serviceathome.models.BusinessService;
import com.gvtech.serviceathome.models.ProfessionalDetailsModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AvailabilityAdapter extends RecyclerView.Adapter<AvailabilityAdapter.MyViewHolder> {

    private List<ProfessionalDetailsModel.Availability> services;
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
    public AvailabilityAdapter(Context context, List<ProfessionalDetailsModel.Availability> services) {
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



        holder.txtStartTime.setText(getDate(services.get(position).getStartTime()));
        holder.txtEndTime.setText(getDate(services.get(position).getEndTime()));
    }

    @Override
    public int getItemCount() {
        return services.size();
    }


    private String getDate(String s){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date date = null;
        try {
            date = dateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String t = date.getHours()+":"+String.format("%02d",date.getMinutes());
        Log.d("date-time",t);
        return t;
    }
}

package com.gvtech.serviceathome.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.models.BusinessService;

import java.util.ArrayList;

public class BusinessServiceAdapter extends RecyclerView.Adapter<BusinessServiceAdapter.MyViewHolder> {

    private ArrayList<BusinessService> services;
    private Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CheckBox chkService;
        public TextView price;

        public MyViewHolder(View view) {
            super(view);
            chkService = (CheckBox) view.findViewById(R.id.chk_service);
            price = (TextView)view.findViewById(R.id.txt_service_price);
        }
    }
    public BusinessServiceAdapter(Context context, ArrayList<BusinessService> services) {
        this.services = services;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_business_service, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.chkService.setText(services.get(position).getName());
        holder.price.setText("$ "+services.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return services.size();
    }
}

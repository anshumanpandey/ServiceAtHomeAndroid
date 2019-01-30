package com.gvtech.serviceathome.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gvtech.serviceathome.Listners.ServiceDataListener;
import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.models.Service;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class ServiceSearchHomeAdapter extends RecyclerView.Adapter<ServiceSearchHomeAdapter.MyViewHolder> {

    private List<Service.ServiceItem> services;
    private ServiceDataListener listener;
    private Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;

        public MyViewHolder(View view) {
            super(view);
            txtName = (TextView) view.findViewById(R.id.txt_service_name);
        }
    }
    public ServiceSearchHomeAdapter(Context context, List<Service.ServiceItem> services, ServiceDataListener listener) {
        this.services = services;
        this.mContext = context;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_search_service, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtName.setText(services.get(position).getName());
        holder.itemView.setOnClickListener(v -> {
            listener.onChooseServiceItem(services.get(position));
        });
    }

    public void updateData(List<Service.ServiceItem> services){

        this.services = services;
        this.notifyDataSetChanged();

    }

    public void clearData(){

        this.services.clear();
        this.notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return services.size();
    }
}

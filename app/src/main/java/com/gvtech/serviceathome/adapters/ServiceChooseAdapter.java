package com.gvtech.serviceathome.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gvtech.serviceathome.Listners.ServiceDataListener;
import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.activities.user.ServiceItemActivity;
import com.gvtech.serviceathome.models.CustomerHomeModel;
import com.gvtech.serviceathome.models.Service;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ServiceChooseAdapter extends RecyclerView.Adapter<ServiceChooseAdapter.MyViewHolder> {

    private List<Service.ServiceItem> services;
    private ServiceDataListener listener;
    private Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public RoundedImageView imgThumb;

        public MyViewHolder(View view) {
            super(view);
            txtName = (TextView) view.findViewById(R.id.txt_service_name);
            imgThumb = view.findViewById(R.id.riv_service_thumb);
        }
    }
    public ServiceChooseAdapter(Context context, List<Service.ServiceItem> services, ServiceDataListener listener) {
        this.services = services;
        this.mContext = context;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_choose_service, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtName.setText(services.get(position).getName());
//        String url = services.get(position).getImageUrl();
//        if (url != null)
//        if (!url.isEmpty()){
//            Picasso.with(mContext).load(url).placeholder(R.drawable.placeholder).into(holder.imgThumb);
//        }
        holder.itemView.setOnClickListener(v -> {
            listener.onChooseServiceItem(services.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return services.size();
    }
}

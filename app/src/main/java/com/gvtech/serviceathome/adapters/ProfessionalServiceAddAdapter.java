package com.gvtech.serviceathome.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gvtech.serviceathome.Listners.ServiceDataListener;
import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.dialogs.AddServiceDialog;
import com.gvtech.serviceathome.models.Service;
import com.gvtech.serviceathome.models.ServiceItem;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProfessionalServiceAddAdapter extends RecyclerView.Adapter<ProfessionalServiceAddAdapter.MyViewHolder> {

    private List<Service.ServiceItem> services;
    private Activity mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public RoundedImageView imgThumb;

        public MyViewHolder(View view) {
            super(view);
            txtName = (TextView) view.findViewById(R.id.txt_service_name);
            imgThumb = view.findViewById(R.id.riv_service_thumb);
        }
    }
    public ProfessionalServiceAddAdapter(Activity context, List<Service.ServiceItem> services) {
        this.services = services;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_professional_service_add, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtName.setText(services.get(position).getName());

        String url = services.get(position).getExtraInfo();
        if (url != null)
        if (!url.isEmpty()){
            Picasso.with(mContext).load(url).placeholder(R.drawable.placeholder).into(holder.imgThumb);
        }

        holder.itemView.setOnClickListener(v -> {
            new AddServiceDialog(mContext, services.get(position)).show();
        });
    }

    @Override
    public int getItemCount() {
        return services.size();
    }
}

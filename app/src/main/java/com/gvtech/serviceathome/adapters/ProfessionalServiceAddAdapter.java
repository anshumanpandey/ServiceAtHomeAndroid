package com.gvtech.serviceathome.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.dialogs.AddServiceDialog;
import com.gvtech.serviceathome.models.ServiceItem;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class ProfessionalServiceAddAdapter extends RecyclerView.Adapter<ProfessionalServiceAddAdapter.MyViewHolder> {

    private ArrayList<ServiceItem> services;
    private Activity a;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public RoundedImageView imgThumb;

        public MyViewHolder(View view) {
            super(view);
            txtName = (TextView) view.findViewById(R.id.txt_service_name);
            imgThumb = view.findViewById(R.id.riv_service_thumb);
        }
    }
    public ProfessionalServiceAddAdapter(Activity a) {
//        this.services = services;
        this.a = a;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_professional_service_add, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //holder.txtName.setText(services.get(position).getName());

        holder.itemView.setOnClickListener(v -> {
            new AddServiceDialog(a).show();
        });
    }

    @Override
    public int getItemCount() {
        return 20;
    }
}

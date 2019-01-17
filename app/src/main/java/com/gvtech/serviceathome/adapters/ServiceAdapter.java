package com.gvtech.serviceathome.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.activities.user.ServiceItemActivity;
import com.gvtech.serviceathome.models.CustomerHomeModel;
import com.gvtech.serviceathome.models.Service;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.MyViewHolder> {

    private List<CustomerHomeModel.Categorie> services;
    private Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public ImageView imgThumb;

        public MyViewHolder(View view) {
            super(view);
            txtName = (TextView) view.findViewById(R.id.txt_service_name);
            imgThumb = view.findViewById(R.id.img_service_url);
        }
    }
    public ServiceAdapter(Context context, List<CustomerHomeModel.Categorie> services) {
        this.services = services;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_service, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtName.setText(services.get(position).getName());
        String url = services.get(position).getImageUrl();
        if (!url.isEmpty()){
            Picasso.with(mContext).load(services.get(position).getImageUrl()).into(holder.imgThumb);
        }
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext,ServiceItemActivity.class);
            intent.putExtra("categoryId",services.get(position).getId());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return services.size();
    }
}

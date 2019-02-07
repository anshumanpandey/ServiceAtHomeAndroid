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
import com.gvtech.serviceathome.activities.user.BusinessDetailsActivity;
import com.gvtech.serviceathome.models.ProfessionalDetailsModel;
import com.gvtech.serviceathome.models.ServiceItem;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProfessionalServiceAdapter extends RecyclerView.Adapter<ProfessionalServiceAdapter.MyViewHolder> {

    private List<ProfessionalDetailsModel.Services> services;
    private Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName,txtPrice,txtTime;
        public RoundedImageView imgThumb;
        public ImageView imgEdit, imgDelete;

        public MyViewHolder(View view) {
            super(view);
            imgThumb = view.findViewById(R.id.riv_service_thumb);
            txtName = (TextView) view.findViewById(R.id.txt_service_name);
            txtPrice = (TextView) view.findViewById(R.id.txt_price);
            txtTime = (TextView) view.findViewById(R.id.txt_time);
            imgEdit =  view.findViewById(R.id.img_edit);
            imgDelete =  view.findViewById(R.id.img_delete);
        }
    }
    public ProfessionalServiceAdapter(Context context, List<ProfessionalDetailsModel.Services> services) {
        this.services = services;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_professional_service, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtName.setText(services.get(position).getServiceName());
        holder.txtPrice.setText(services.get(position).getPrice()+"");
        holder.txtTime.setText(services.get(position).getDuration()+" min");

//        String url = services.get(position).getImage();
//        if (url != null)
//            if (!url.isEmpty()){
//                Picasso.with(mContext).load(url).placeholder(R.drawable.placeholder).into(holder.imgThumb);
//            }
        holder.imgEdit.setOnClickListener(v -> {

        });
        holder.imgDelete.setOnClickListener(v -> {

        });

    }

    @Override
    public int getItemCount() {
        return services.size();
    }
}

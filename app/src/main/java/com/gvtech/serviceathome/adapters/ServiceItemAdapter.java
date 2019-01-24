package com.gvtech.serviceathome.adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Rating;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.activities.user.BusinessDetailsActivity;
import com.gvtech.serviceathome.models.ProfessionalServiceModel;
import com.gvtech.serviceathome.models.ServiceItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ServiceItemAdapter extends RecyclerView.Adapter<ServiceItemAdapter.MyViewHolder> {

    private List<ProfessionalServiceModel.Professional> services;
    private Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtType;
        public ImageView imgThumb;
        public RatingBar ratingBar;

        public MyViewHolder(View view) {
            super(view);
            txtName = (TextView) view.findViewById(R.id.txt_service_name);
            txtType = (TextView) view.findViewById(R.id.txt_service_type);
            imgThumb = view.findViewById(R.id.img_service_url);
            ratingBar = view.findViewById(R.id.ratingBar);
        }
    }
    public ServiceItemAdapter(Context context, List<ProfessionalServiceModel.Professional> services) {
        this.services = services;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_single_service, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ProfessionalServiceModel.Professional professional = services.get(position);
        holder.txtName.setText(professional.getBusinessName());
        holder.txtType.setText(professional.getWhatYouAre());
        holder.ratingBar.setRating(professional.getRating());

        String url = professional.getProfileImage();
        if (url != null)
        if (!url.isEmpty()){
            Picasso.with(mContext).load(url).placeholder(R.drawable.placeholder).into(holder.imgThumb);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext,BusinessDetailsActivity.class);
            intent.putExtra("professionalId",professional.getProfessionalID());
            intent.putExtra("userId",professional.getID());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return services.size();
    }
}

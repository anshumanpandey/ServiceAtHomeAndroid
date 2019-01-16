package com.gvtech.serviceathome.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.models.Availability;
import com.gvtech.serviceathome.models.Gallery;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyViewHolder> {

    private ArrayList<Gallery> services;
    private Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public RoundedImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            imageView = (RoundedImageView) view.findViewById(R.id.riv_gallery);
        }
    }
    public GalleryAdapter(Context context, ArrayList<Gallery> services) {
        this.services = services;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_gallery, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Picasso.with(mContext).load(services.get(position).getUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return services.size();
    }
}

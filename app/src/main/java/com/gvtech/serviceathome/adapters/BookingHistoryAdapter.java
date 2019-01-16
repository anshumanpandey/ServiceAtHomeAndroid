package com.gvtech.serviceathome.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.activities.user.BusinessDetailsActivity;
import com.gvtech.serviceathome.models.ServiceItem;

import java.util.ArrayList;

public class BookingHistoryAdapter extends RecyclerView.Adapter<BookingHistoryAdapter.MyViewHolder> {

    private ArrayList<ServiceItem> services;
    private Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder {
//        public TextView txtName;
//        public ImageView imgThumb;

        public MyViewHolder(View view) {
            super(view);
//            txtName = (TextView) view.findViewById(R.id.txt_service_name);
//            imgThumb = view.findViewById(R.id.img_service_url);
        }
    }
    public BookingHistoryAdapter(Context context, ArrayList<ServiceItem> services) {
        this.services = services;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_booking_history, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        holder.txtName.setText(services.get(position).getName());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext,BusinessDetailsActivity.class);
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return services.size();
    }
}

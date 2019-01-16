package com.gvtech.serviceathome.activities.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.adapters.BookingHistoryAdapter;
import com.gvtech.serviceathome.adapters.ServiceItemAdapter;
import com.gvtech.serviceathome.data.LoadData;

public class BookingHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_history);

        RecyclerView recyclerView = findViewById(R.id.recycler_booking_history);

        BookingHistoryAdapter bookingHistoryAdapter = new BookingHistoryAdapter(getApplicationContext(),LoadData.loadServiseItemData());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(bookingHistoryAdapter);
    }
}

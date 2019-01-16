package com.gvtech.serviceathome.activities.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.adapters.ServiceItemAdapter;
import com.gvtech.serviceathome.data.LoadData;

public class ServiceItemActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_item);

        recyclerView = findViewById(R.id.recycler_service_item);

        ServiceItemAdapter serviceAdapter = new ServiceItemAdapter(getApplicationContext(),LoadData.loadServiseItemData());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(serviceAdapter);
    }
}

package com.gvtech.serviceathome.activities.professional;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.adapters.GalleryAdapter;
import com.gvtech.serviceathome.data.LoadData;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        RecyclerView recyclerView = findViewById(R.id.recycler_gallery);

        GalleryAdapter galleryAdapter = new GalleryAdapter(getApplicationContext(),LoadData.loadGalleryItemData());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(galleryAdapter);

    }
}

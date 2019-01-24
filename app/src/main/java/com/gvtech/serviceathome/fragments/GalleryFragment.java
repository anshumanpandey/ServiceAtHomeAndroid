package com.gvtech.serviceathome.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gvtech.serviceathome.Listners.TabDataListener;
import com.gvtech.serviceathome.Listners.TabDataLoad;
import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.activities.user.BusinessDetailsActivity;
import com.gvtech.serviceathome.adapters.GalleryAdapter;
import com.gvtech.serviceathome.adapters.ServiceAdapter;
import com.gvtech.serviceathome.data.LoadData;
import com.gvtech.serviceathome.models.ProfessionalDetailsModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment implements TabDataListener {
    RecyclerView recyclerView;

    public GalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_gallery);
        TabDataLoad tabDataLoad = new TabDataLoad(this);
        ((BusinessDetailsActivity)this.getActivity()).setTabListener(tabDataLoad);


    }

    @Override
    public void noDataLoad(Object o) {

        Log.d("ssssss","gallery");
        ProfessionalDetailsModel res = (ProfessionalDetailsModel) o;
        GalleryAdapter galleryAdapter = new GalleryAdapter(getActivity().getApplicationContext(),res.getResultObject().getGallery());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(),2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(galleryAdapter);
    }
}

package com.gvtech.serviceathome.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gvtech.serviceathome.Listners.TabDataLoad;
import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.Listners.TabDataListener;
import com.gvtech.serviceathome.activities.user.BusinessDetailsActivity;
import com.gvtech.serviceathome.adapters.BusinessServiceAdapter;
import com.gvtech.serviceathome.data.LoadData;
import com.gvtech.serviceathome.models.ProfessionalDetailsModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFragment extends Fragment implements TabDataListener {

    RecyclerView recyclerService;


    public ServicesFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_services, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TabDataLoad tabDataLoad = new TabDataLoad(this);
        ((BusinessDetailsActivity)this.getActivity()).setTabListener(tabDataLoad);

        recyclerService = view.findViewById(R.id.recycler_service);
    }

    @Override
    public void noDataLoad(Object o) {
        ProfessionalDetailsModel res = (ProfessionalDetailsModel) o;
        Log.d("ssssss","service");
        BusinessServiceAdapter businessServiceAdapter = new BusinessServiceAdapter(getActivity().getApplicationContext(), res.getResultObject().getServices());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerService.setLayoutManager(mLayoutManager);
        recyclerService.setItemAnimator(new DefaultItemAnimator());
        recyclerService.setAdapter(businessServiceAdapter);



    }
}

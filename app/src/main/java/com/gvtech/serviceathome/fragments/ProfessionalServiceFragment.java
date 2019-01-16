package com.gvtech.serviceathome.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.activities.professional.AddServiceActivity;
import com.gvtech.serviceathome.adapters.ProfessionalServiceAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfessionalServiceFragment extends Fragment {

    Button btnAddService;

    public ProfessionalServiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_professional, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_service);
        btnAddService = view.findViewById(R.id.btn_add_service);

        btnAddService.setOnClickListener(v -> {
            ((AddServiceActivity)this.getActivity()).replaceFragment(new ProfessionalServiceItemFragment(), true);
        });

        ProfessionalServiceAdapter professionalServiceAdapter = new ProfessionalServiceAdapter(getActivity().getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(professionalServiceAdapter);
    }
}

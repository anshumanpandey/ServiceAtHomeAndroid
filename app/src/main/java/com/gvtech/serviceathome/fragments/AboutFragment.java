package com.gvtech.serviceathome.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gvtech.serviceathome.Listners.TabDataListener;
import com.gvtech.serviceathome.Listners.TabDataLoad;
import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.activities.user.BusinessDetailsActivity;
import com.gvtech.serviceathome.models.ProfessionalDetailsModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment implements TabDataListener {

    TextView txtAbout;
    TextView txtSpeciality;
    TextView txtProfessions;

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TabDataLoad tabDataLoad = new TabDataLoad(this);
        ((BusinessDetailsActivity)this.getActivity()).setTabListener(tabDataLoad);

        txtAbout = view.findViewById(R.id.txt_about);
        txtProfessions = view.findViewById(R.id.txt_profession);
        txtSpeciality = view.findViewById(R.id.txt_speciality);
        txtSpeciality.setText("");
    }

    @Override
    public void noDataLoad(Object o) {

        Log.d("ssssss","about");
        ProfessionalDetailsModel res = (ProfessionalDetailsModel) o;

        txtSpeciality.setText("");
        txtAbout.setText(res.getResultObject().getProfessionalDetail().getServiceInformation());
        for (ProfessionalDetailsModel.Speciality sp :res.getResultObject().getSpeciality())
            txtSpeciality.setText(txtSpeciality.getText().toString()+sp.getSpeciality()+"\n");

        txtProfessions.setText(res.getResultObject().getProfessionalDetail().getWhatYouAre());

    }
}

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
import android.widget.Toast;

import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.activities.professional.AddServiceActivity;
import com.gvtech.serviceathome.adapters.ProfessionalServiceAdapter;
import com.gvtech.serviceathome.dialogs.LoaderDialog;
import com.gvtech.serviceathome.models.ProfessionalDetailsModel;
import com.gvtech.serviceathome.service.ApiClient.ApiClient;
import com.gvtech.serviceathome.service.ApiInterface.ApiInterface;
import com.gvtech.serviceathome.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfessionalServiceFragment extends Fragment {

    Button btnAddService;
    private CompositeDisposable disposable = new CompositeDisposable();
    private ApiInterface apiService;
    private LoaderDialog loaderDialog;
    private RecyclerView recyclerView;


    public ProfessionalServiceFragment() {
        // Required empty public constructor
        apiService = ApiClient.getClient().create(ApiInterface.class);
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

        loaderDialog = new LoaderDialog(getActivity());

        recyclerView = view.findViewById(R.id.recycler_service);
        btnAddService = view.findViewById(R.id.btn_add_service);

        btnAddService.setOnClickListener(v -> {
            ((AddServiceActivity)this.getActivity()).replaceFragment(new ProfessionalServiceItemFragment(), true);
        });



        loadProfessionalDataFromRemote(23020,1);
    }


    private void loadProfessionalDataFromRemote(int professionalId, int userId){
        loaderDialog.show();
        apiService.getProfessionalDetail(Constants.APP_NAME, professionalId, userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProfessionalDetailsModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(ProfessionalDetailsModel response) {
                        if (response.getResultCode().equals(Constants.RESULT_SUCCESS)){
                            updateUI(response.getResultObject().getServices());
                        }
                        else {
                            Toast.makeText(getActivity().getApplicationContext(),response.getResultMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        loaderDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {
                        loaderDialog.dismiss();
                    }
                });
    }

    private void updateUI(List<ProfessionalDetailsModel.Services> services){
        ProfessionalServiceAdapter professionalServiceAdapter =
                new ProfessionalServiceAdapter(getActivity().getApplicationContext(),services);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(professionalServiceAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        disposable.clear();
    }

}

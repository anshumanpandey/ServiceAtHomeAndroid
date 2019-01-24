package com.gvtech.serviceathome.fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.activities.user.SearchProfessionalActivity;
import com.gvtech.serviceathome.adapters.ServiceItemAdapter;
import com.gvtech.serviceathome.dialogs.LoaderDialog;
import com.gvtech.serviceathome.models.ProfessionalServiceModel;
import com.gvtech.serviceathome.models.Service;
import com.gvtech.serviceathome.service.ApiClient.ApiClient;
import com.gvtech.serviceathome.service.ApiInterface.ApiInterface;
import com.gvtech.serviceathome.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchProfessionalFragment extends Fragment {

    EditText edtSelectService,txtDateTime,edtPincode;
    Button btnDateTime,btnSearch;
    final Calendar myCalendar = Calendar.getInstance();
    private CompositeDisposable disposable = new CompositeDisposable();
    private ApiInterface apiService;
    private LoaderDialog loaderDialog;
    private String serviceId = "";
    private String serviceName = "";
    private String serviceDate ="";
    private CardView cardView;
    private RecyclerView recyclerView;
    private LinearLayout llNoItem;

    public SearchProfessionalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_search_professional, container, false);

        Bundle bundle = this.getArguments();
        apiService = ApiClient.getClient().create(ApiInterface.class);
        loaderDialog = new LoaderDialog(getActivity());

        edtSelectService = view.findViewById(R.id.edt_select_service);
        txtDateTime = view.findViewById(R.id.txt_datetime);
        edtSelectService = view.findViewById(R.id.edt_select_service);
        edtPincode = view.findViewById(R.id.edt_pincode);
        btnDateTime = view.findViewById(R.id.btnDateTime);
        btnSearch = view.findViewById(R.id.btn_search);
        cardView = view.findViewById(R.id.cd_search);
        recyclerView = view.findViewById(R.id.recycler_service);
        llNoItem = view.findViewById(R.id.ll_no_item_found);

        recyclerView.setVisibility(View.GONE);
        cardView.setVisibility(View.VISIBLE);
        btnSearch.setOnClickListener(searchListener);

        edtSelectService.setOnTouchListener((v, event) -> {

            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    Bundle b = new Bundle();
                    b.putString("date", txtDateTime.getText().toString());
                    ChooseServiceFragment chooseServiceFragment = new ChooseServiceFragment();
                    chooseServiceFragment.setArguments(b);
                    ((SearchProfessionalActivity)this.getActivity()).replaceFragment(chooseServiceFragment,false);
                    break;
            }
            return false;
        });


        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        btnDateTime.setOnClickListener(v -> {
            new DatePickerDialog(getActivity(), date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });
        if(bundle != null){
            serviceId = bundle.getString("itemId");
            serviceName = bundle.getString("itemName");
            serviceDate = bundle.getString("date");
            edtSelectService.setText(serviceName);
            txtDateTime.setText(serviceDate);
        }

        txtDateTime.setText(getCurrentDate());

        return view;
    }

    private String getCurrentDate(){
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("YYYY/MM/dd");
        return df.format(c);
    }

    private View.OnClickListener searchListener = v -> {

//       if (validateField(txtDateTime,"Please choose date")
//               && validateField(edtSelectService,"Please select service")
//               && validateField(edtPincode,"Please enter post code")){
            String postcode = edtPincode.getText().toString();
            serviceDate = txtDateTime.getText().toString();
            searchProfessionalDataRemote(serviceId,serviceDate,postcode,1);
      // }
    };

    private boolean validateField(EditText editText, String err){
        editText.setError(null);
        if (editText.getText().toString().length() < 1){
            editText.setError(err);
            return false;
        }
        return true;
    }

    private void updateLabel() {
        String myFormat = "YYYY/MM/dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        txtDateTime.setText(sdf.format(myCalendar.getTime()));
    }


    private void searchProfessionalDataRemote(String service, String datetime, String postcode, int pageIndex){
        loaderDialog.show();
        apiService.searchProfessional(Constants.APP_NAME, datetime, service, postcode, pageIndex)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProfessionalServiceModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(ProfessionalServiceModel professionalServiceModel) {
                        if (professionalServiceModel.getResultCode().equals(Constants.RESULT_SUCCESS)){
                            //loadAdapter(professionalServiceModel.getProfessionals());
                            if (professionalServiceModel.getProfessionals().size() > 0){
                                cardView.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                                llNoItem.setVisibility(View.GONE);
                                ServiceItemAdapter serviceAdapter = new ServiceItemAdapter(getActivity(), professionalServiceModel.getProfessionals());
                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                                recyclerView.setLayoutManager(mLayoutManager);
                                recyclerView.setItemAnimator(new DefaultItemAnimator());
                                recyclerView.setAdapter(serviceAdapter);

                            }else {
                                llNoItem.setVisibility(View.VISIBLE);
                                cardView.setVisibility(View.GONE);
                            }
                        }
                        else {
                            Toast.makeText(getActivity(),professionalServiceModel.getResultMessage(),Toast.LENGTH_SHORT).show();
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

    @Override
    public void onDetach() {
        super.onDetach();
        disposable.clear();
    }


}

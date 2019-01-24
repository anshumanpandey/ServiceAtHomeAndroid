package com.gvtech.serviceathome.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gvtech.serviceathome.Listners.ServiceDataListener;
import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.activities.user.SearchProfessionalActivity;
import com.gvtech.serviceathome.adapters.ServiceChooseAdapter;
import com.gvtech.serviceathome.dialogs.LoaderDialog;
import com.gvtech.serviceathome.models.Service;
import com.gvtech.serviceathome.service.ApiClient.ApiClient;
import com.gvtech.serviceathome.service.ApiInterface.ApiInterface;
import com.gvtech.serviceathome.utils.Constants;


import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseServiceFragment extends Fragment implements ServiceDataListener {

    private RecyclerView recyclerView;
    private CompositeDisposable disposable = new CompositeDisposable();
    private ApiInterface apiService;
    private LoaderDialog loaderDialog;

    public ChooseServiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_choose, container, false);
        recyclerView = view.findViewById(R.id.recycler_search_list);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        loaderDialog = new LoaderDialog(getActivity());

        loadServiceItemDataRemote();

        return view;
    }

    private void loadAdapter(List<Service.ServiceItem> serviceItemList){
        ServiceChooseAdapter serviceAdapter = new ServiceChooseAdapter(this.getActivity(), serviceItemList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(serviceAdapter);
    }

    private void loadServiceItemDataRemote(){
        loaderDialog.show();
        apiService.getAllServices(Constants.APP_NAME)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Service>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(Service service) {
                        if (service.getResultCode().equals(Constants.RESULT_SUCCESS)){
                            loadAdapter(service.getServiceItems());
                        }
                        else {
                            Toast.makeText(getActivity(),service.getResultMessage(),Toast.LENGTH_SHORT).show();
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

    @Override
    public void onChooseServiceItem(Service.ServiceItem serviceItem) {

        Bundle dateBundle = this.getArguments();
        String date = dateBundle.getString("date");

        Bundle bundle = new Bundle();
        bundle.putInt("itemId", serviceItem.getId());
        bundle.putString("itemName", serviceItem.getName());
        bundle.putString("date", date);
        SearchProfessionalFragment fragment = new SearchProfessionalFragment();
        fragment.setArguments(bundle);
        ((SearchProfessionalActivity)this.getActivity()).replaceFragment(fragment,false);

    }
}

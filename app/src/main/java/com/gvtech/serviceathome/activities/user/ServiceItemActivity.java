package com.gvtech.serviceathome.activities.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.activities.professional.ProfessionalHomeActivity;
import com.gvtech.serviceathome.adapters.ServiceItemAdapter;
import com.gvtech.serviceathome.data.LoadData;
import com.gvtech.serviceathome.dialogs.LoaderDialog;
import com.gvtech.serviceathome.models.ProfessionalServiceModel;
import com.gvtech.serviceathome.models.postModel.UserLoginResponseModel;
import com.gvtech.serviceathome.service.ApiClient.ApiClient;
import com.gvtech.serviceathome.service.ApiInterface.ApiInterface;
import com.gvtech.serviceathome.utils.Constants;
import com.gvtech.serviceathome.utils.SharedStore;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ServiceItemActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private CompositeDisposable disposable = new CompositeDisposable();
    private ApiInterface apiService;
    private LoaderDialog loaderDialog;

    private static int categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_item);

        recyclerView = findViewById(R.id.recycler_service_item);

        categoryId = Objects.requireNonNull(getIntent().getExtras()).getInt("categoryId");

        apiService = ApiClient.getClient().create(ApiInterface.class);
        loaderDialog = new LoaderDialog(this);

        searchProfessionalServiceRemote(categoryId);
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return false;
    }


    private void searchProfessionalServiceRemote(int categoryId){
        loaderDialog.show();
        apiService.searchProfessionalByCategory(Constants.APP_NAME,categoryId,1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProfessionalServiceModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(ProfessionalServiceModel response) {
                        if (response.getResultCode().equals(Constants.RESULT_SUCCESS)){

                            if (response.getProfessionals().size() < 1){
                                findViewById(R.id.ll_no_item_found).setVisibility(View.VISIBLE);
                            }else {
                                findViewById(R.id.ll_no_item_found).setVisibility(View.GONE);
                                ServiceItemAdapter serviceAdapter = new ServiceItemAdapter(getApplicationContext(), response.getProfessionals());
                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                recyclerView.setLayoutManager(mLayoutManager);
                                recyclerView.setItemAnimator(new DefaultItemAnimator());
                                recyclerView.setAdapter(serviceAdapter);
                            }


                        }
                        else {
                            Toast.makeText(getApplicationContext(),response.getResultMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        loaderDialog.dismiss();
                    }
                });
    }
}

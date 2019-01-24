package com.gvtech.serviceathome.activities.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.gvtech.serviceathome.Listners.TabDataListener;
import com.gvtech.serviceathome.Listners.TabDataLoad;
import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.activities.professional.ProfessionalHomeActivity;
import com.gvtech.serviceathome.adapters.TabAdapter;
import com.gvtech.serviceathome.dialogs.LoaderDialog;
import com.gvtech.serviceathome.models.ProfessionalDetailsModel;
import com.gvtech.serviceathome.models.postModel.UserLoginResponseModel;
import com.gvtech.serviceathome.service.ApiClient.ApiClient;
import com.gvtech.serviceathome.service.ApiInterface.ApiInterface;
import com.gvtech.serviceathome.utils.Constants;
import com.gvtech.serviceathome.utils.SharedStore;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BusinessDetailsActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private RoundedImageView rivProfile;
    private ImageView imgBack;
    private CollapsingToolbarLayout toolbarLayout;
    private CompositeDisposable disposable = new CompositeDisposable();
    private ApiInterface apiService;
    private LoaderDialog loaderDialog;
    private List<TabDataLoad> tabDataLoad = new ArrayList<>();

    private int professionalId;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbarLayout.setTitle(" ");

        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.viewPager);

        rivProfile = findViewById(R.id.riv_profile);
        imgBack = findViewById(R.id.img_background);

        apiService = ApiClient.getClient().create(ApiInterface.class);
        loaderDialog = new LoaderDialog(this);

        professionalId = Objects.requireNonNull(getIntent().getExtras()).getInt("professionalId");
        userId = getIntent().getExtras().getInt("userId");

        loadProfessionalDataFromRemote(professionalId, userId);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return false;
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
                            ProfessionalDetailsModel.ProfessionalDetail professionalDetail = response.getResultObject().getProfessionalDetail();
                            String url = professionalDetail.getProfileImage();
                            if (url != null)
                                if (!url.isEmpty()){
                                    Picasso.with(getApplicationContext()).load(url).placeholder(R.drawable.demo_user).into(rivProfile);
                                }
                            String urlBack = professionalDetail.getCoverImage();
                            if (urlBack != null)
                                if (!urlBack.isEmpty()){
                                    Picasso.with(getApplicationContext()).load(urlBack).placeholder(R.drawable.bg_demo_a).into(imgBack);
                                }

                            toolbarLayout.setTitle(professionalDetail.getBusinessName());

                            loadAllTab(response);
                            for (int i =0 ; i < tabDataLoad.size(); i++)
                                tabDataLoad.get(i).setDataLoad(response);
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


    private void loadAllTab(ProfessionalDetailsModel detailsModel){
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.services)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.availability)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.gallery)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.about_Me)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final TabAdapter adapter = new TabAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                for (int i =0 ; i < tabDataLoad.size(); i++)
                    tabDataLoad.get(i).setDataLoad(detailsModel);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }

    public void setTabListener(TabDataLoad tabListener){
        this.tabDataLoad.add(tabListener);
    }

}

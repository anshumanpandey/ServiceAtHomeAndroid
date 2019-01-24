package com.gvtech.serviceathome.activities.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.activities.professional.ProfessionalHomeActivity;
import com.gvtech.serviceathome.activities.user.AccountActivity;
import com.gvtech.serviceathome.activities.user.BookingHistoryActivity;
import com.gvtech.serviceathome.activities.user.CalendarActivity;
import com.gvtech.serviceathome.activities.user.SearchProfessionalActivity;
import com.gvtech.serviceathome.adapters.ServiceAdapter;
import com.gvtech.serviceathome.data.LoadData;
import com.gvtech.serviceathome.dialogs.ChangePasswordDialog;
import com.gvtech.serviceathome.dialogs.LoaderDialog;
import com.gvtech.serviceathome.models.CustomerHomeModel;
import com.gvtech.serviceathome.models.postModel.UserLoginResponseModel;
import com.gvtech.serviceathome.service.ApiClient.ApiClient;
import com.gvtech.serviceathome.service.ApiInterface.ApiInterface;
import com.gvtech.serviceathome.utils.Constants;
import com.gvtech.serviceathome.utils.SharedStore;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {


    private SliderLayout mSlider;
    private RecyclerView recyclerService;
    private ServiceAdapter serviceAdapter;
    private CompositeDisposable disposable = new CompositeDisposable();
    private ApiInterface apiService;
    private LoaderDialog loaderDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSlider = (SliderLayout)findViewById(R.id.slider);
        recyclerService = findViewById(R.id.recycler_service);

        apiService = ApiClient.getClient().create(ApiInterface.class);
        loaderDialog = new LoaderDialog(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


//        HashMap<String,String> url_maps = new HashMap<String, String>();
//        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
//        url_maps.put("Big Bang Theory", "https://corporateindiamart.files.wordpress.com/2017/12/advertising-banner-100.jpg");
//        url_maps.put("House of Cards", "http://ornatets.com/images/webdesign/online-banner-advertising.jpg");
//        url_maps.put("Game of Thrones", "https://ps.w.org/adrotate/assets/banner-772x250.jpg");
//
//        for(String name : url_maps.keySet()){
//            TextSliderView textSliderView = new TextSliderView(this);
//            // initialize a SliderLayout
//            textSliderView
//                    .description(name)
//                    .image(url_maps.get(name))
//                    .setScaleType(BaseSliderView.ScaleType.CenterCrop)
//                    .setOnSliderClickListener(this);
//
//            //add your extra information
//            textSliderView.bundle(new Bundle());
//            textSliderView.getBundle()
//                    .putString("extra",name);
//
//            mSlider.addSlider(textSliderView);
//        }
//        mSlider.setPresetTransformer(SliderLayout.Transformer.ZoomOutSlide);
//        mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
//        mSlider.setCustomAnimation(new DescriptionAnimation());
//        mSlider.setDuration(4000);
//        mSlider.addOnPageChangeListener(this);

        getDashboardRemote();
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_account_details) {
            Intent intent = new Intent(getApplicationContext(),AccountActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_search_professional) {
            Intent intent = new Intent(getApplicationContext(),SearchProfessionalActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_booking_history) {
            Intent intent = new Intent(getApplicationContext(),BookingHistoryActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_calendar_events) {
            Intent intent = new Intent(getApplicationContext(),CalendarActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_change_pass) {
            ChangePasswordDialog changePasswordDialog = new ChangePasswordDialog(this);
            changePasswordDialog.show();

        }else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    private void getDashboardRemote(){
        loaderDialog.show();
        apiService.getDashboard(Constants.APP_NAME)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CustomerHomeModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(CustomerHomeModel homeResponse) {
                        if (homeResponse.getResultCode().equals(Constants.RESULT_SUCCESS)){

                            serviceAdapter = new ServiceAdapter(getApplicationContext(),homeResponse.getResultObject().getCategories());
                            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),3);
                            recyclerService.setLayoutManager(mLayoutManager);
                            recyclerService.setItemAnimator(new DefaultItemAnimator());
                            recyclerService.setAdapter(serviceAdapter);
                            // load home slider

                            List<CustomerHomeModel.Sliders> sliders = homeResponse.getResultObject().getHomeSliders();
                            HashMap<String,String> url_maps = new HashMap<String, String>();
                            for (CustomerHomeModel.Sliders s : sliders){
                                url_maps.put(s.getName(), s.getFileName());
                            }

                            for(String name : url_maps.keySet()){
                                TextSliderView textSliderView = new TextSliderView(HomeActivity.this);
                                // initialize a SliderLayout
                                textSliderView
                                        .description(name)
                                        .image(url_maps.get(name))
                                        .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                                        .setOnSliderClickListener(HomeActivity.this);

                                //add your extra information
                                textSliderView.bundle(new Bundle());
                                textSliderView.getBundle()
                                        .putString("extra",name);

                                mSlider.addSlider(textSliderView);
                            }
                            mSlider.setPresetTransformer(SliderLayout.Transformer.ZoomOutSlide);
                            mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                            mSlider.setCustomAnimation(new DescriptionAnimation());
                            mSlider.setDuration(4000);
                            mSlider.addOnPageChangeListener(HomeActivity.this);


                        }
                        else {
                            Toast.makeText(getApplicationContext(),homeResponse.getResultMessage(),Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}

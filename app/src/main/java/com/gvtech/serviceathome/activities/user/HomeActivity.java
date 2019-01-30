package com.gvtech.serviceathome.activities.user;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.gvtech.serviceathome.Listners.ServiceDataListener;
import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.adapters.ServiceAdapter;
import com.gvtech.serviceathome.adapters.ServiceChooseAdapter;
import com.gvtech.serviceathome.adapters.ServiceMultiSelectAdapter;
import com.gvtech.serviceathome.adapters.ServiceSearchHomeAdapter;
import com.gvtech.serviceathome.dialogs.ChangePasswordDialog;
import com.gvtech.serviceathome.dialogs.LoaderDialog;
import com.gvtech.serviceathome.models.CustomerHomeModel;
import com.gvtech.serviceathome.models.Service;
import com.gvtech.serviceathome.models.UserModel;
import com.gvtech.serviceathome.service.ApiClient.ApiClient;
import com.gvtech.serviceathome.service.ApiInterface.ApiInterface;
import com.gvtech.serviceathome.utils.Constants;
import com.gvtech.serviceathome.utils.ConverterUtil;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener,ServiceDataListener
{


    private SliderLayout mSlider;
    private RecyclerView recyclerService,recyclerSearchList, recyclerSelectList;
    private ServiceAdapter serviceAdapter;
    private ServiceSearchHomeAdapter serviceSeacrhAdapter;
    private ServiceMultiSelectAdapter adapterSelectList;
    private CompositeDisposable disposable = new CompositeDisposable();
    private ApiInterface apiService;
    private LoaderDialog loaderDialog;
    private UserModel userModel;
    private ImageView imgUserMenu;
    private TextView txtUserMenuName,txtUserMenuPhone;
    private AVLoadingIndicatorView smallLoader;
    private List<Service.ServiceItem> serviceItemSelected = new ArrayList<>();

    EditText edtSelectService,txtDateTime;
    Button btnDateTime,btnSearch;
    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSlider = (SliderLayout)findViewById(R.id.slider);
        btnSearch = findViewById(R.id.btn_search);
        recyclerService = findViewById(R.id.recycler_service);

        btnDateTime = findViewById(R.id.btnDateTime);
        edtSelectService = findViewById(R.id.edt_select_service);
        txtDateTime = findViewById(R.id.txt_datetime);
        recyclerSearchList = findViewById(R.id.recycler_search_list);
        recyclerSelectList = findViewById(R.id.recycler_select_list);
        smallLoader = findViewById(R.id.avi_small);


        apiService = ApiClient.getClient().create(ApiInterface.class);
        loaderDialog = new LoaderDialog(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hview = navigationView.getHeaderView(0);

        imgUserMenu = hview.findViewById(R.id.img_menu_user);
        txtUserMenuName = hview.findViewById(R.id.txt_menu_user_name);
        txtUserMenuPhone = hview.findViewById(R.id.txt_menu_user_phones);



        userModel = ConverterUtil.getUserModel(getApplicationContext());

        edtSelectService.addTextChangedListener(serviceTextChange);

        if(userModel != null){
            txtUserMenuName.setText(userModel.getFirstName()+ " "+ userModel.getLastName());
            txtUserMenuPhone.setText(userModel.getPhone());
            String url = userModel.getProfileImg();
            if (url != null)
                if (!url.isEmpty()){
                    Picasso.with(getApplicationContext()).load(url).placeholder(R.drawable.demo_user).into(imgUserMenu);
                }
        }


        DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };

        btnDateTime.setOnClickListener(v -> {
            new DatePickerDialog(this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        btnSearch.setOnClickListener(v -> {
            if (getSelectedIdArrayString() != null){
                String sDate = txtDateTime.getText().toString();
                Intent intent = new Intent(getApplicationContext(),ServiceItemActivity.class);
                intent.putExtra("serviceIds",getSelectedIdArrayString());
                intent.putExtra("searchDate",sDate);
                intent.putExtra("searchType","service");
                startActivity(intent);
            }
//
        });
        txtDateTime.setText(getCurrentDate());

        loadAdapter();

        getDashboardRemote();


    }

    private String getSelectedIdArrayString(){
        if (serviceItemSelected.size() < 1){
            Toast.makeText(getApplicationContext(),"Please select service",Toast.LENGTH_SHORT).show();
            return null;
        }
        JSONArray jsonArray = new JSONArray();
        for (int i =0; i< serviceItemSelected.size(); i++){
            jsonArray.put(serviceItemSelected.get(i).getId());
        }
        return jsonArray.toString();
    }

    private String getCurrentDate(){
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("YYYY/MM/dd");
        return df.format(c);
    }

    private void updateLabel() {
        String myFormat = "YYYY/MM/dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        txtDateTime.setText(sdf.format(myCalendar.getTime()));
    }

    TextWatcher serviceTextChange = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            Log.d("TEXT--", s.toString());
            if (s.toString().trim().length() > 0)
                loadServiceItemDataRemote(s.toString(),txtDateTime.getText().toString());
            else {
                serviceSeacrhAdapter.clearData();
            }

        }
    };

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


    private void loadAdapter(){
        List<Service.ServiceItem> serviceItemList = new ArrayList<>();
        serviceSeacrhAdapter = new ServiceSearchHomeAdapter(this, serviceItemList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerSearchList.setLayoutManager(mLayoutManager);
        recyclerSearchList.setItemAnimator(new DefaultItemAnimator());
        recyclerSearchList.setAdapter(serviceSeacrhAdapter);


        adapterSelectList = new ServiceMultiSelectAdapter(this, serviceItemList, this);
        RecyclerView.LayoutManager mSLayoutManager = new LinearLayoutManager(this);
        recyclerSelectList.setLayoutManager(mSLayoutManager);
        recyclerSelectList.setItemAnimator(new DefaultItemAnimator());
        recyclerSelectList.setAdapter(adapterSelectList);
    }

    private void updateSearchItem(List<Service.ServiceItem> serviceItemList){
        serviceSeacrhAdapter.updateData(serviceItemList);
    }

    private void loadServiceItemDataRemote(String srvName, String date){
        smallLoader.show();
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
                            if (edtSelectService.getText().toString().trim().length() > 0)
                                updateSearchItem(service.getServiceItems());
                            else
                                serviceSeacrhAdapter.clearData();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),service.getResultMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        smallLoader.hide();
                    }

                    @Override
                    public void onComplete() {
                        smallLoader.hide();
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }

    @Override
    public void onChooseServiceItem(Service.ServiceItem serviceItem) {
        Log.d("service--",serviceItem.getName());
        serviceSeacrhAdapter.clearData();
        serviceItemSelected.add(serviceItem);
        adapterSelectList.updateData(serviceItemSelected);
        edtSelectService.setText("");

    }
}

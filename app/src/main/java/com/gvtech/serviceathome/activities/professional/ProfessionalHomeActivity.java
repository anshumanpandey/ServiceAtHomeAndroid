package com.gvtech.serviceathome.activities.professional;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.activities.user.CalendarActivity;
import com.gvtech.serviceathome.dialogs.LoaderDialog;
import com.gvtech.serviceathome.models.ProfessionalDetailsModel;
import com.gvtech.serviceathome.service.ApiClient.ApiClient;
import com.gvtech.serviceathome.service.ApiInterface.ApiInterface;
import com.gvtech.serviceathome.utils.Constants;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProfessionalHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private CompositeDisposable disposable = new CompositeDisposable();
    private ApiInterface apiService;
    private LoaderDialog loaderDialog;

    private EditText
            edtFname,
            edtLname,
            edtEmail,
            edtPhone,
            edtBusName,
            edtWhatAreU,
            edtAboutService,
            edtSpciality,
            edtAddLine1,
            edtAddLine2,
            edtAddLine3,
            edtCity,
            edtPinCode;
    private Button
            btnPerDetails,
            brnWorkDetails,
            btnAddressDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        apiService = ApiClient.getClient().create(ApiInterface.class);
        loaderDialog = new LoaderDialog(this);
        getUserDetailsRemote(23020);
        initView();
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
        getMenuInflater().inflate(R.menu.professional_home, menu);
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

        if (id == R.id.nav_account_details) {

            Intent intent = new Intent(getApplicationContext(),ProfAccountDetailsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_professional_details) {
            Intent intent = new Intent(getApplicationContext(), AddServiceActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_about_me) {

            Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_calendar_events) {
            Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_calendar_working_day) {

        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(getApplicationContext(), GalleryActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_change_pass) {

        }else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    private void initView(){
        edtFname = findViewById(R.id.edt_first_name);
        edtLname = findViewById(R.id.edt_last_name);
        edtEmail = findViewById(R.id.edt_email);
        edtPhone = findViewById(R.id.edt_phone);
        edtBusName = findViewById(R.id.edt_business_name);
        edtWhatAreU = findViewById(R.id.edt_what_are_you);
        edtAboutService = findViewById(R.id.edt_about_service);
        edtSpciality = findViewById(R.id.edt_speciality);
        edtAddLine1 = findViewById(R.id.edt_address_l_one);
        edtAddLine2 = findViewById(R.id.edt_address_l_two);
        edtAddLine3 = findViewById(R.id.edt_address_l_three);
        edtCity = findViewById(R.id.edt_city);
        edtPinCode = findViewById(R.id.pin_code);

        btnPerDetails = findViewById(R.id.btn_update_personal);
        brnWorkDetails = findViewById(R.id.btn_add_work_details);
        btnAddressDetails = findViewById(R.id.btn_update_address);

    }


    private void getUserDetailsRemote(int userId){
        loaderDialog.show();
        apiService.getProfessionalAccount(Constants.APP_NAME,userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProfessionalDetailsModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(ProfessionalDetailsModel res) {
                        if (res.getResultCode().equals(Constants.RESULT_SUCCESS)){
                            loadPersonalUI(res.getResultObject().getProfessionalDetail());
                            loadPostcodeUI(res.getResultObject().getPostcodes());

                        }
                        else {
                            Toast.makeText(getApplicationContext(),res.getResultMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        loaderDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {
                        loaderDialog.dismiss();
                    }
                });
    }

    private void loadPersonalUI(ProfessionalDetailsModel.ProfessionalDetail perDetails){
        if (perDetails.getFirstName() !=null)
            edtFname.setText(perDetails.getFirstName());
        if (perDetails.getLastName() !=null)
            edtLname.setText(perDetails.getLastName());
        if (perDetails.getBusinessName() !=null)
            edtBusName.setText(perDetails.getBusinessName());
        if (perDetails.getEmail() !=null)
            edtEmail.setText(perDetails.getEmail());
        if (perDetails.getPhone() !=null)
            edtPhone.setText(perDetails.getPhone());
        if (perDetails.getWhatYouAre() !=null)
            edtWhatAreU.setText(perDetails.getWhatYouAre());
        if (perDetails.getServiceInformation() !=null)
            edtAboutService.setText(perDetails.getServiceInformation());
        if (perDetails.getAddress1() !=null)
            edtAddLine1.setText(perDetails.getAddress1());
        if (perDetails.getAddress2() !=null)
            edtAddLine2.setText(perDetails.getAddress2());
        if (perDetails.getAddress3() !=null)
            edtAddLine3.setText(perDetails.getAddress3());
        if (perDetails.getCity() !=null)
            edtCity.setText(perDetails.getCity());

    }

    private void loadPostcodeUI(List<ProfessionalDetailsModel.Postcodes> postcodes){

        for (ProfessionalDetailsModel.Postcodes p : postcodes){
            if (p.getPostcode() != null){
                edtPinCode.setText(p.getPostcode());
            }
        }
    }
}

package com.gvtech.serviceathome.activities.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.adapters.ServiceAdapter;
import com.gvtech.serviceathome.dialogs.LoaderDialog;
import com.gvtech.serviceathome.models.CustomerHomeModel;
import com.gvtech.serviceathome.models.CustomerProfile;
import com.gvtech.serviceathome.service.ApiClient.ApiClient;
import com.gvtech.serviceathome.service.ApiInterface.ApiInterface;
import com.gvtech.serviceathome.utils.AppLocationService;
import com.gvtech.serviceathome.utils.Constants;
import com.gvtech.serviceathome.utils.LocationAddress;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AccountActivity extends AppCompatActivity {

    private CompositeDisposable disposable = new CompositeDisposable();
    private ApiInterface apiService;
    private LoaderDialog loaderDialog;
    private EditText edtFname,edtLname,edtEmail,edtPhone,edtAddress;
    private Button btnSubmitProfile;
    private RoundedImageView rivProfileImg;
    private AppLocationService appLocationService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        apiService = ApiClient.getClient().create(ApiInterface.class);
        loaderDialog = new LoaderDialog(this);

        edtFname = findViewById(R.id.edt_fname);
        edtLname = findViewById(R.id.edt_lname);
        edtEmail = findViewById(R.id.edt_email);
        edtPhone = findViewById(R.id.edt_phone);
//        edtPincode = findViewById(R.id.edt_pincode);
        edtAddress = findViewById(R.id.edt_address);
        btnSubmitProfile = findViewById(R.id.update_profile);
        rivProfileImg = findViewById(R.id.riv_profile_img);

        appLocationService = new AppLocationService(
                this);



        getCustomerProfileRemote(23019);
    }


    private void getCustomerProfileRemote(int userId){
        loaderDialog.show();
        apiService.getCustomerAccount(Constants.APP_NAME, userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CustomerProfile>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(CustomerProfile response) {
                        if (response.getResultCode().equals(Constants.RESULT_SUCCESS)){
                            edtFname.setText(response.getResultObject().getFirstName());
                            edtLname.setText(response.getResultObject().getLastName());
                            edtEmail.setText(response.getResultObject().getEmail());
                            edtPhone.setText(response.getResultObject().getPhone());

                            if (response.getResultObject().getAddress1() != null){
                                edtAddress.setText(response.getResultObject().getAddress1());
                            }else {
                                fetchGPSData();
                            }

                            String url = response.getResultObject().getProfileImage();
                            if (url != null)
                                if (!url.isEmpty()) {
                                    Picasso.with(getApplicationContext()).load(url).placeholder(R.drawable.demo_user).into(rivProfileImg);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }


    private void fetchGPSData(){

        //        Location gpsLocation = appLocationService
//                .getLocation(LocationManager.GPS_PROVIDER);
//        if (gpsLocation != null) {
//            double latitude = gpsLocation.getLatitude();
//            double longitude = gpsLocation.getLongitude();
//            String result = "Latitude: " + gpsLocation.getLatitude() +
//                    " Longitude: " + gpsLocation.getLongitude();
//            edtFname.setText(result);
//        } else {
//            showSettingsAlert();
//        }

        Location location = appLocationService
                .getLocation(LocationManager.GPS_PROVIDER);

        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            LocationAddress locationAddress = new LocationAddress();
            locationAddress.getAddressFromLocation(latitude, longitude,
                    getApplicationContext(), new GeocoderHandler());
        } else {
            showSettingsAlert();
        }
    }


    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                AccountActivity.this);
        alertDialog.setTitle("SETTINGS");
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        AccountActivity.this.startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("addressObj");
                    try {
                        JSONObject object = new JSONObject(locationAddress);
                        edtAddress.setText(object.getString("address"));
                        Log.d("address-object",object.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    locationAddress = null;
            }
        }
    }
}

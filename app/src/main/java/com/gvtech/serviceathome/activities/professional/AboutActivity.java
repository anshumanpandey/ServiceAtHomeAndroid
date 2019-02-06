package com.gvtech.serviceathome.activities.professional;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.dialogs.LoaderDialog;
import com.gvtech.serviceathome.models.ProfessionalDetailsModel;
import com.gvtech.serviceathome.models.postModel.UserLoginResponseModel;
import com.gvtech.serviceathome.service.ApiClient.ApiClient;
import com.gvtech.serviceathome.service.ApiInterface.ApiInterface;
import com.gvtech.serviceathome.utils.Constants;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AboutActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_about);

        apiService = ApiClient.getClient().create(ApiInterface.class);
        loaderDialog = new LoaderDialog(this);
        getUserDetailsRemote(23020);
        initView();
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


    }
}

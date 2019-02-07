package com.gvtech.serviceathome.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.activities.professional.AddServiceActivity;
import com.gvtech.serviceathome.fragments.ProfessionalServiceFragment;
import com.gvtech.serviceathome.models.ProfessionalDetailsModel;
import com.gvtech.serviceathome.models.Service;
import com.gvtech.serviceathome.models.UserModel;
import com.gvtech.serviceathome.service.ApiClient.ApiClient;
import com.gvtech.serviceathome.service.ApiInterface.ApiInterface;
import com.gvtech.serviceathome.utils.Constants;
import com.gvtech.serviceathome.utils.ConverterUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddServiceDialog extends Dialog implements
        View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button btnSubmit, no;
    private TextView txtServiceName;
    EditText txtDuration, txtPrice;
    private Service.ServiceItem serviceItem;
    private CompositeDisposable disposable = new CompositeDisposable();
    private ApiInterface apiService;
    private LoaderDialog loaderDialog;

    public AddServiceDialog(Activity a, Service.ServiceItem serviceItem) {
        super(a, R.style.Theme_Dialog);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.serviceItem = serviceItem;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_service_dialog);

        apiService = ApiClient.getClient().create(ApiInterface.class);
        loaderDialog = new LoaderDialog(this.c);

        btnSubmit = (Button) findViewById(R.id.btn_submit);
        no = (Button) findViewById(R.id.btn_no);
        txtServiceName = findViewById(R.id.txt_service_name);
        txtDuration = findViewById(R.id.edt_duration);
        txtPrice = findViewById(R.id.edt_price);
        btnSubmit.setOnClickListener(this);
        no.setOnClickListener(this);

        txtServiceName.setText(serviceItem.getName());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                UserModel userModel = ConverterUtil.getUserModel(c.getApplicationContext());

                loadProfessionalDataFromRemote(userModel.getUserId(),1,serviceItem.getId(),txtDuration.getText().toString(),txtPrice.getText().toString());
                break;
            case R.id.btn_no:
                dismiss();
                break;
            default:
                break;
        }

    }

    private void loadProfessionalDataFromRemote(int professionalId, int id, int serviceId, String durition, String price){
        loaderDialog.show();
        apiService.addProfessionalService(Constants.APP_NAME, professionalId, id,serviceId,durition, price)
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
                            ((AddServiceActivity)c).replaceFragment(new ProfessionalServiceFragment(),false);
                        }
                        else {
                            Toast.makeText(c.getApplicationContext(),response.getResultMessage(),Toast.LENGTH_SHORT).show();
                            dismiss();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        loaderDialog.dismiss();
                        dismiss();
                    }

                    @Override
                    public void onComplete() {
                        loaderDialog.dismiss();
                        dismiss();
                    }
                });
    }
}
package com.gvtech.serviceathome.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.models.ChangePasswordModel;
import com.gvtech.serviceathome.models.UserModel;
import com.gvtech.serviceathome.service.ApiClient.ApiClient;
import com.gvtech.serviceathome.service.ApiInterface.ApiInterface;
import com.gvtech.serviceathome.utils.Constants;
import com.gvtech.serviceathome.utils.ConverterUtil;
import com.gvtech.serviceathome.utils.SharedStore;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ChangePasswordDialog extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button btnSubmit, no;
    private EditText edtCurrPass,edtNewPass;
    private CompositeDisposable disposable = new CompositeDisposable();
    private ApiInterface apiService;
    private LoaderDialog loaderDialog;

    public ChangePasswordDialog(Activity a) {
        super(a, R.style.Theme_Dialog);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.change_password_dialog);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        edtCurrPass = findViewById(R.id.edit_current_pass);
        edtNewPass = findViewById(R.id.edit_new_pass);
        no = (Button) findViewById(R.id.btn_no);
        btnSubmit.setOnClickListener(this);
        no.setOnClickListener(this);

        apiService = ApiClient.getClient().create(ApiInterface.class);
        loaderDialog = new LoaderDialog(c);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                UserModel userModel = ConverterUtil.getUserModel(c.getApplicationContext());
                changePasswordRemote(userModel.getUserId(),edtCurrPass.getText().toString(),edtNewPass.getText().toString());

                break;
            case R.id.btn_no:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }

    private void changePasswordRemote(int userid, String curPass, String newPass){
        apiService.changePassword(Constants.APP_NAME,userid,curPass,newPass)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ChangePasswordModel>() {
            @Override
            public void onSubscribe(Disposable d) {
             disposable.add(d);
            }

            @Override
            public void onNext(ChangePasswordModel changePasswordModel) {
                if (changePasswordModel.getResultCode().equals(Constants.RESULT_SUCCESS)){
                    UserModel userModel = ConverterUtil.getUserModel(c.getApplicationContext());
                    userModel.setPass(newPass);
                    Gson gson = new Gson();
                    SharedStore.setUserDetails(c.getApplicationContext(),gson.toJson(userModel));
                    Toast.makeText(c.getApplicationContext(),changePasswordModel.getResultMessage(),Toast.LENGTH_SHORT).show();

                    dismiss();
                }
                else {
                    Toast.makeText(c.getApplicationContext(),changePasswordModel.getResultMessage(),Toast.LENGTH_SHORT).show();
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
}
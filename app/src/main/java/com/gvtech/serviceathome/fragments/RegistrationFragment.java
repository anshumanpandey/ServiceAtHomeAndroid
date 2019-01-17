package com.gvtech.serviceathome.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.gvtech.serviceathome.R;
import com.gvtech.serviceathome.activities.LoginActivity;
import com.gvtech.serviceathome.activities.professional.ProfessionalHomeActivity;
import com.gvtech.serviceathome.activities.user.HomeActivity;
import com.gvtech.serviceathome.dialogs.LoaderDialog;
import com.gvtech.serviceathome.models.postModel.UserLoginResponseModel;
import com.gvtech.serviceathome.service.ApiClient.ApiClient;
import com.gvtech.serviceathome.service.ApiInterface.ApiInterface;
import com.gvtech.serviceathome.utils.Constants;
import com.gvtech.serviceathome.utils.SharedStore;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {

    private Button btnReg;
    private EditText edtFirstName,edtLastName,edtEmail,edtPass,edtPhone,edtCPass;
    private CompositeDisposable disposable = new CompositeDisposable();
    private ApiInterface apiService;
    private LoaderDialog loaderDialog;

    public RegistrationFragment() {
        // Required empty public constructor
        apiService = ApiClient.getClient().create(ApiInterface.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imgGoBack = view.findViewById(R.id.img_go_back);


        btnReg = view.findViewById(R.id.btn_reg);
        edtFirstName = view.findViewById(R.id.edt_first_name);
        edtLastName = view.findViewById(R.id.edt_last_name);
        edtEmail = view.findViewById(R.id.edt_email);
        edtPhone = view.findViewById(R.id.edt_phone);
        edtPass = view.findViewById(R.id.edt_pass);
        edtCPass = view.findViewById(R.id.edt_con_pass);

        loaderDialog = new LoaderDialog(getActivity());

        btnReg.setOnClickListener(regClickListener);


        imgGoBack.setOnClickListener(v -> ((LoginActivity)this.getActivity()).beck());
    }

    private View.OnClickListener regClickListener = v -> {

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        String fname = edtFirstName.getText().toString().trim();
        String lname = edtLastName.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String pass = edtPass.getText().toString().trim();
        String cpass = edtCPass.getText().toString().trim();

        if (fname.isEmpty()){
            edtFirstName.setError(getString(R.string.err_fname));
            return;
        }
        if (lname.isEmpty()){
            edtLastName.setError(getString(R.string.err_lname));
            return;
        }
        if (phone.isEmpty() || phone.length() != 10){
            edtPhone.setError(getString(R.string.err_phone));
            return;
        }
        if (email.isEmpty() || !email.matches(emailPattern)){
            edtEmail.setError(getString(R.string.err_email));
            return;
        }
        if (pass.isEmpty()){
            edtPass.setError(getString(R.string.err_password));
            return;
        }
        if (cpass.isEmpty()){
            edtCPass.setError(getString(R.string.err_cpass));
            return;
        }
        if (!pass.equals(cpass)){
            edtCPass.setError(getString(R.string.err_pass_not_matched));
            return;
        }

        // remote call for register customer
        registerRemote(fname,lname,phone,email,pass);
    };

    private void registerRemote(String fname, String lname, String phone, String email, String pass){
        loaderDialog.show();
        apiService.createAccount(
                Constants.APP_NAME,
                Constants.DEVICE_ID,
                Constants.DEVICE_NAME,
                Constants.ROLE_CUSTOMER,
                fname,
                lname,
                "",
                phone,
                email,
                pass)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserLoginResponseModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(UserLoginResponseModel userResponse) {
                        if (userResponse.getResultCode().equals(Constants.RESULT_SUCCESS)){

                            try {
                                UserLoginResponseModel.User u = userResponse.getResultObject().getUser();
                                JSONObject object = new JSONObject();
                                object.put("ID",u.getID());
                                object.put("Email",u.getEmail());
                                object.put("Password",pass);
                                object.put("AccountNumber",u.getAccountNumber());
                                object.put("FirstName",u.getFirstName());
                                object.put("LastName",u.getLastName());
                                object.put("Phone",u.getPhone());
                                object.put("RoleID",u.getRoleID());
                                object.put("RoleName",u.getRoleName());
                                SharedStore.setUserDetails(getActivity().getApplicationContext(),object.toString());

                                Intent intent;
                                if (u.getRoleName().equals(Constants.ROLE_CUSTOMER)){
                                    intent = new Intent(getActivity().getApplicationContext(),HomeActivity.class);
                                }else{
                                    intent = new Intent(getActivity().getApplicationContext(),ProfessionalHomeActivity.class);
                                }
                                Toast.makeText(getActivity(),getString(R.string.success_reg),Toast.LENGTH_SHORT).show();
                                getActivity().startActivity(intent);
                                getActivity().finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }else {
                            Toast.makeText(getActivity(),userResponse.getResultMessage(),Toast.LENGTH_SHORT).show();
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
    public void onDetach() {
        super.onDetach();
        disposable.clear();
    }
}

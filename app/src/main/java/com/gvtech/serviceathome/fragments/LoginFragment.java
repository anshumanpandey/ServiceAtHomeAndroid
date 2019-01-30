package com.gvtech.serviceathome.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class LoginFragment extends Fragment {

    private Button btnLogin, btnReg, btnLinkBusiness;
    private EditText edtUsername, edtPasswd;
    private CompositeDisposable disposable = new CompositeDisposable();
    private ApiInterface apiService;
    private LoaderDialog loaderDialog;
    private LinearLayout layout;


    public LoginFragment() {
        // Required empty public constructor
        apiService = ApiClient.getClient().create(ApiInterface.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layout = view.findViewById(R.id.ll_login);
        btnLogin = view.findViewById(R.id.btn_login);

        edtUsername = view.findViewById(R.id.edt_first_name);
        edtPasswd = view.findViewById(R.id.edt_pass);
        btnReg = view.findViewById(R.id.btn_reg);
        btnLinkBusiness = view.findViewById(R.id.btn_link_business);

        loaderDialog = new LoaderDialog(getActivity());
        // open user registration fragment
        btnReg.setOnClickListener(v -> {
            ((LoginActivity)this.getActivity()).replaceFragment(new RegistrationFragment(), true);
        });

        // open Link business fragment
        btnLinkBusiness.setOnClickListener(v -> {
            ((LoginActivity)this.getActivity()).replaceFragment(new LinkBusinessFragment(),true);
        });

        // login button
        btnLogin.setOnClickListener(v -> {
            String user = edtUsername.getText().toString().trim();
            String pass = edtPasswd.getText().toString().trim();

            if (user.isEmpty()){
                edtUsername.setError(getString(R.string.err_username));
                return;
            }
            if (pass.isEmpty()){
                edtPasswd.setError(getString(R.string.err_password));
                return;
            }
            // do login in remote
            loginRemote(user,pass);

        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        disposable.clear();
    }

    private void loginRemote(String user, String pass){
        loaderDialog.show();
        apiService.userLogin(Constants.APP_NAME,user,pass,Constants.DEVICE_ID,Constants.DEVICE_NAME)
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
                            Intent intent;
                            if (userResponse.getResultObject().getUser().getRoleName().equals(Constants.ROLE_CUSTOMER)){
                                intent = new Intent(getActivity().getApplicationContext(),HomeActivity.class);
                            }else{
                                intent = new Intent(getActivity().getApplicationContext(),ProfessionalHomeActivity.class);
                            }
                            try {
                                UserLoginResponseModel.User u = userResponse.getResultObject().getUser();
                                String img = "";
                                if (u.getProfileImage() != null)
                                    img = u.getProfileImage();

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
                                object.put("profileImg",img);
                                SharedStore.setUserDetails(getActivity().getApplicationContext(),object.toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            getActivity().startActivity(intent);
                            getActivity().finish();
                        }
                        else {
                            YoYo.with(Techniques.Tada)
                                    .duration(500)
                                    .playOn(layout);
                            Toast.makeText(getActivity(),userResponse.getResultMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        YoYo.with(Techniques.Tada)
                                .duration(500)
                                .playOn(layout);
                        Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        loaderDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {
                        loaderDialog.dismiss();
                    }
                });
    }

}

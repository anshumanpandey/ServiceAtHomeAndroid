package com.gvtech.serviceathome.service.ApiInterface;


import com.gvtech.serviceathome.models.postModel.UserLoginResponseModel;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiInterface {


    // user login
    @FormUrlEncoded
    @Headers({"Content-Type:application/x-www-form-urlencoded"})
    @POST("createsession")
    Observable<UserLoginResponseModel> userLogin(@Field("application") String application,
                                                 @Field("email") String email,
                                                 @Field("password") String password,
                                                 @Field("deviceid") String deviceid,
                                                 @Field("devicename") String devicename);
    // forgot password
    @FormUrlEncoded
    @Headers({"Content-Type:application/x-www-form-urlencoded"})
    @POST("forgotpassword")
    Observable<UserLoginResponseModel> forgotPassword(@Field("application") String application,
                                                      @Field("email") String email);

    // registration
    @FormUrlEncoded
    @Headers({"Content-Type:application/x-www-form-urlencoded"})
    @POST("createaccount")
    Observable<UserLoginResponseModel> createAccount(@Field("application") String application,
                                                     @Field("deviceid") String deviceid,
                                                     @Field("devicename") String devicename,
                                                     @Field("role") String role,
                                                     @Field("firstname") String firstname,
                                                     @Field("lastname") String lastname,
                                                     @Field("businessname") String businessname,
                                                     @Field("phone") String phone,
                                                     @Field("email") String email,
                                                     @Field("password") String password);
}

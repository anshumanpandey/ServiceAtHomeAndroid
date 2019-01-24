package com.gvtech.serviceathome.service.ApiInterface;


import com.gvtech.serviceathome.models.ChangePasswordModel;
import com.gvtech.serviceathome.models.CustomerHomeModel;
import com.gvtech.serviceathome.models.CustomerProfile;
import com.gvtech.serviceathome.models.ProfessionalDetailsModel;
import com.gvtech.serviceathome.models.ProfessionalServiceModel;
import com.gvtech.serviceathome.models.Service;
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

    // get all category list for customer
    @FormUrlEncoded
    @Headers({"Content-Type:application/x-www-form-urlencoded"})
    @POST("getdashboard")
    Observable<CustomerHomeModel> getDashboard(@Field("application") String application);

    // find professionals by category id
    @FormUrlEncoded
    @Headers({"Content-Type:application/x-www-form-urlencoded"})
    @POST("searchProfessionalByCategory")
    Observable<ProfessionalServiceModel> searchProfessionalByCategory(@Field("application") String application,
                                                                      @Field("category") int categoryId,
                                                                      @Field("pageindex") int pageindex);

    // find professionals details by professional id
    @FormUrlEncoded
    @Headers({"Content-Type:application/x-www-form-urlencoded"})
    @POST("professionaldetail")
    Observable<ProfessionalDetailsModel> getProfessionalDetail(@Field("application") String application,
                                                               @Field("professionalid") int professionalid,
                                                               @Field("userid") int userid);


    // get customer account details by user id
    @FormUrlEncoded
    @Headers({"Content-Type:application/x-www-form-urlencoded"})
    @POST("getcustomeraccount")
    Observable<CustomerProfile> getCustomerAccount(@Field("application") String application,
                                                   @Field("userid") int userid);

    // update customer account details by user id
    @FormUrlEncoded
    @Headers({"Content-Type:application/x-www-form-urlencoded"})
    @POST("updatecustomeraccount")
    Observable<CustomerProfile> updatecustomeraccount(@Field("application") String application,
                                                   @Field("userid") int userid);

    // update customer account details by user id
    @FormUrlEncoded
    @Headers({"Content-Type:application/x-www-form-urlencoded"})
    @POST("getallservices")
    Observable<Service> getAllServices(@Field("application") String application );

    // search professionals
    @FormUrlEncoded
    @Headers({"Content-Type:application/x-www-form-urlencoded"})
    @POST("searchprofessional")
    Observable<ProfessionalServiceModel> searchProfessional(@Field("application") String application,
                                                                      @Field("searchdate") String searchdate,
                                                                      @Field("service") String service,
                                                                      @Field("postcode") String postcode,
                                                                      @Field("pageindex") int pageindex);

    // change user password
    @FormUrlEncoded
    @Headers({"Content-Type:application/x-www-form-urlencoded"})
    @POST("changepassword")
    Observable<ChangePasswordModel> changePassword(@Field("application") String application,
                                                   @Field("userid") int userid,
                                                   @Field("currentpassword") String currentpassword,
                                                   @Field("newpassword") String newpassword);
}

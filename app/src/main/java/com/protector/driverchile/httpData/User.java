package com.protector.driverchile.httpData;

import com.protector.driverchile.utils.DataModelJson.ChangePass;
import com.protector.driverchile.utils.DataModelJson.ContactModel;
import com.protector.driverchile.utils.DataModelJson.DriverPojo;
import com.protector.driverchile.utils.DataModelJson.HtmlModel;
import com.protector.driverchile.utils.DataModelJson.LoginTrackingModel;
import com.protector.driverchile.utils.DataModelJson.MembershipModel;
import com.protector.driverchile.utils.DataModelJson.MembershipSimpleModel;
import com.protector.driverchile.utils.DataModelJson.MessagePojo;
import com.protector.driverchile.utils.DataModelJson.NotificationModel;
import com.protector.driverchile.utils.DataModelJson.RatingModel;
import com.protector.driverchile.utils.DataModelJson.TourListModel;
import com.protector.driverchile.utils.DataModelJson.VersionApp;

import okhttp3.ResponseBody;

import com.protector.driverchile.login.LoginModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**

 */
public interface User {
    @Headers( "Content-Type: application/json" )
    @GET("api/version/driver/android/{ver}.json")
    Call<VersionApp> versionApp(@Path("ver") String vers);

    @Headers( "Content-Type: application/json" )
    @POST("api/login.json")
    Call<DriverPojo> login(@Body LoginModel user);

    @Headers( "Content-Type: application/json" )
    @POST("api/forgot-password/{email}.json")
    Call<MessagePojo> forgotPass(@Path("email") String email);

    @Headers( "Content-Type: application/json" )
    @POST("api/user/code-resent.json")
    Call<MessagePojo> resentCode(@Header("x-api-key") String apikey,
                                 @Header("x-language-code") String lenguage);

    @Headers( "Content-Type: application/json" )
    @PUT("api/user/verification.json")
    Call<MessagePojo> sentCode(@Header("x-api-key") String apikey,
                               @Header("x-language-code") String lenguage,
                               @Query("verificationCode") String code);

    @DELETE("api/logout.json")
    Call<MessagePojo> logOut(@Header("x-api-key") String apikey,
                             @Header("x-language-code") String lenguage);

    @Headers( "Content-Type: application/json" )
    @POST("api/user/password-update.json")
    Call<MessagePojo> changePass(@Header("x-api-key") String apikey,
                                  @Header("x-language-code") String lenguage,
                                  @Body ChangePass changePass);

    @FormUrlEncoded
    @POST("api/fcm.json")
    Call<ResponseBody> registerToken(@Field("userToken") String userToken,
                                 @Header("x-api-key") String authorization);

    @GET("api/terms-conditions.json")
    Call<HtmlModel> termsCondition();

    @GET("api/about-us.json")
    Call<HtmlModel> aboutUs();

    @GET("api/privacy-policy.json")
    Call<HtmlModel> privacyPolicy();

    @GET("api/contact-us.json")
    Call<ContactModel> contactUS();

    @Headers( "Content-Type: application/json" )
    @POST("api/driver-trip-ratings.json")
    Call<MessagePojo> ratingPassenger(@Header("x-api-key") String apikey,
                                      @Header("x-language-code") String lenguage,
                                      @Body RatingModel ratingModel);

    @Headers( "Content-Type: application/json" )
    @POST("api/user/notification/{status}.json")
    Call<MessagePojo> notificationStatus(@Header("x-api-key") String apikey,
                                 @Header("x-language-code") String lenguage,
                                 @Path("status") boolean status);

    @Headers( "Content-Type: application/json" )
    @POST("api/memberships/actual.json")
    Call<MessagePojo> membership(@Header("x-api-key") String apikey,
                                         @Header("x-language-code") String lenguage);


    @Headers( "Content-Type: application/json" )
    @PUT("api/user/accept-procar/{status}.json")
    Call<MessagePojo> procarStatus(@Header("x-api-key") String apikey,
                                   @Header("x-language-code") String lenguage,
                                   @Path("status") boolean status);

    @Headers( "Content-Type: application/json" )
    @GET("api/user/notification/0/50.json")
    Call<NotificationModel> getNotificationList(@Header("x-api-key") String apikey,
                                                @Header("x-language-code") String lenguage);


    @Headers( "Content-Type: application/json" )
    @POST("api/memberships/current-membership.json")
    Call<MembershipModel> getMembershipInfo(@Header("x-api-key") String apikey,
                                        @Header("x-language-code") String lenguage,
                                            @Header("x-full-map")boolean typeCall);

    @Headers( "Content-Type: application/json" )
    @POST("api/memberships/current-membership.json")
    Call<MembershipSimpleModel> getMembershipSimple(@Header("x-api-key") String apikey,
                                                        @Header("x-language-code") String lenguage,
                                                        @Header("x-full-map")boolean typeCall);
    ///api/memberships/current-membership.json
    @POST("secured/gps/trip/connect.json")
    Call<LoginTrackingModel> logintracking(@Header("x-api-key") String authorization);
}

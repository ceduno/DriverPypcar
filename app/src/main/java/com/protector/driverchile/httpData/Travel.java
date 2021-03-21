package com.protector.driverchile.httpData;

import com.protector.driverchile.utils.DataModelJson.CurrentTravelModel;
import com.protector.driverchile.utils.DataModelJson.MessagePojo;
import com.protector.driverchile.utils.DataModelJson.RatingModel;
import com.protector.driverchile.utils.DataModelJson.TourIdModel;
import com.protector.driverchile.utils.DataModelJson.TourListModel;
import com.protector.driverchile.utils.DataModelJson.TravelInfoModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Travel {

    @Headers( "Content-Type: application/json" )
    @PUT("/api/driver/{status}.json")
    Call<MessagePojo> driverStatus(@Header("x-api-key") String apikey,
                                   @Header("x-language-code") String lenguage,
                                   @Path("status") boolean status);

    @Headers( "Content-Type: application/json" )
    @PUT("api/driver/accept/{tourId}.json")
    Call<MessagePojo> acceptTravel(@Header("x-api-key") String apikey,
                                   @Header("x-language-code") String lenguage,
                                   @Path("tourId") String tourId);

    /*@Headers( "Content-Type: application/json" )
    @PUT("api/driver/reject-tour/{tourId}.json")
    Call<MessagePojo> cancelTravel(@Header("x-api-key") String apikey,
                                   @Header("x-language-code") String lenguage,
                                   @Path("tourId") String tourId);*/
    @Headers( "Content-Type: application/json" )
    @PUT("api/driver/reject-tour/{tourId}.json")
    Call<MessagePojo> cancelTravel(@Header("x-api-key") String apikey,
                                   @Header("x-language-code") String lenguage,
                                   @Path("tourId") String tourId);

    @Headers( "Content-Type: application/json" )
    @PUT("api/driver/arrived-waiting/{tourId}.json")
    Call<MessagePojo> arrivedTravel(@Header("x-api-key") String apikey,
                                   @Header("x-language-code") String lenguage,
                                   @Path("tourId") String tourId);

    @Headers( "Content-Type: application/json" )
    @PUT("api/driver/started/{tourId}.json")
    Call<MessagePojo> startedTravel(@Header("x-api-key") String apikey,
                                    @Header("x-language-code") String lenguage,
                                    @Path("tourId") String tourId);


    @Headers( "Content-Type: application/json" )
    @POST("api/driver/ended_revised_v5/ended.json")
    Call<MessagePojo> endedTravel(@Header("x-api-key") String apikey,
                                  @Header("x-language-code") String lenguage,
                                  @Body TourIdModel tourIdModel);

    @Headers( "Content-Type: application/json" )
    @GET("api/tours/current.json")
    Call<CurrentTravelModel> getCurrent(@Header("x-api-key") String apikey,
                                        @Header("x-language-code") String lenguage);

    @Headers( "Content-Type: application/json" )
    @PUT("api/driver/update-gps.json")
    Call<MessagePojo> upDateGps(@Header("x-api-key") String apikey,
                                  @Header("x-language-code") String lenguage,
                                  @Query("lat") Double lat,
                                  @Query("lng") Double lng);

    @Headers( "Content-Type: application/json" )
    @GET("api/tours/0/{number}.json")
    Call<TourListModel> getTourList(@Header("x-api-key") String apikey,
                                   @Header("x-language-code") String lenguage,
                                   @Path("number") int number);

    @Headers( "Content-Type: application/json" )
    @GET("api/invoice/{tourId}.json")
    Call<TravelInfoModel> getInvoice(@Header("x-api-key") String apikey,
                                     @Header("x-language-code") String lenguage,
                                     @Path("tourId") String tourId);

}

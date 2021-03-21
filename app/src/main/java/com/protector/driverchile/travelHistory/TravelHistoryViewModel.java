package com.protector.driverchile.travelHistory;
import android.app.Activity;
import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;

import com.protector.driverchile.R;
import com.protector.driverchile.homeMaster.HomeMasterView;
import com.protector.driverchile.httpData.HttpConexion;
import com.protector.driverchile.httpData.Travel;
import com.protector.driverchile.utils.DataModelJson.DriverPojo;
import com.protector.driverchile.utils.DataModelJson.TourListModel;
import com.protector.driverchile.utils.LogOut;
import com.protector.driverchile.utils.SharedPreferenceManager;
import com.protector.driverchile.utils.ToastCustom;
import com.protector.driverchile.utils.Validation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TravelHistoryViewModel  extends AndroidViewModel {
    private String TAG= "TRAVELHISTORIVIEWMODEL";
    private Application application;
    private TravelHistoryEventView travelHistoryEventView;
    private Activity activity;

    public TravelHistoryViewModel(Application application, TravelHistoryEventView travelHistoryEventView,
                                  Activity activity) {
        super(application);
        this.application= application;
        this.travelHistoryEventView= travelHistoryEventView;
        this.activity = activity;

        reload();
    }

    public void reload(){
        if (Validation.isNetDisponible(getApplication())){
            getTourList();
        }else{
            ToastCustom.show(3,application.getApplicationContext(),application.getString(R.string.without_internet));
            travelHistoryEventView.showReload(true);
            travelHistoryEventView.showLoad(false);
        }
    }

    private void getTourList() {
        travelHistoryEventView.showLoad(true);
        travelHistoryEventView.showListCondition(false);
        travelHistoryEventView.showReload(false);

        DriverPojo driverPojo= SharedPreferenceManager.getUser(application.getApplicationContext());

        HttpConexion.getUri()
                .create(Travel.class)
                .getTourList(driverPojo.getApiSessionKey(),"es",50)
                .enqueue(new Callback<TourListModel>() {
                    @Override
                    public void onResponse(Call<TourListModel> call, Response<TourListModel> response) {
                        if (response.code()>=400 && response.code()<500){
                            if (response.code()==401){
                                LogOut.Do(activity,true);
                            }else {
                                travelHistoryEventView.showLoad(false);
                                travelHistoryEventView.showReload(true);
                                ToastCustom.show(0,activity,
                                        activity.getApplicationContext().getString(R.string.not_have_any_trip_registry));
                            }
                        }else {
                            try{
                                travelHistoryEventView.showLoad(false);
                                travelHistoryEventView.showListCondition(true);
                                travelHistoryEventView.showList(response.body().getTourList());
                            }catch (Exception e){
                                travelHistoryEventView.showLoad(false);
                                travelHistoryEventView.showReload(true);
                                Log.e(TAG+" getTourList EXCEPTION",e.toString());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<TourListModel> call, Throwable t) {
                        travelHistoryEventView.showLoad(false);
                        travelHistoryEventView.showReload(true);
                        Log.e(TAG+" getTourList ONFAILURE",t.toString());
                    }
                });
    }
}

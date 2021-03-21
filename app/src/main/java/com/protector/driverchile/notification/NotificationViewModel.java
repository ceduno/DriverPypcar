package com.protector.driverchile.notification;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.util.Log;
import androidx.lifecycle.AndroidViewModel;
import com.protector.driverchile.R;
import com.protector.driverchile.httpData.HttpConexion;
import com.protector.driverchile.httpData.User;
import com.protector.driverchile.utils.DataModelJson.DriverPojo;
import com.protector.driverchile.utils.DataModelJson.NotificationMap;
import com.protector.driverchile.utils.DataModelJson.NotificationModel;
import com.protector.driverchile.utils.LogOut;
import com.protector.driverchile.utils.SharedPreferenceManager;
import com.protector.driverchile.utils.ToastCustom;
import com.protector.driverchile.utils.Validation;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**

 */
public class NotificationViewModel extends AndroidViewModel {
    private String TAG= "NOTIFICATIONVIEWMODEL";
    private Application application;
    private NotificationEventView notificationEventView;
    private Activity activity;

    public NotificationViewModel(Application application,NotificationEventView notificationEventView,Activity activity) {
        super(application);
        this.application= application;
        this.notificationEventView= notificationEventView;
        this.activity= activity;
        reload();
    }

    public void reload(){
        if (Validation.isNetDisponible(getApplication())){
            getNotificationList();
        }else{
            ToastCustom.show(3,application.getApplicationContext(),application.getString(R.string.without_internet));
            notificationEventView.showReload(true);
            notificationEventView.showLoad(false);
        }
    }

    @SuppressLint("LongLogTag")
    private void getNotificationList() {
        notificationEventView.showLoad(true);
        notificationEventView.showReload(false);

        DriverPojo driverPojo = SharedPreferenceManager.getUser(application.getApplicationContext());

        HttpConexion.getUri()
                .create(User.class)
                .getNotificationList(driverPojo.getApiSessionKey(), "es")
                .enqueue(new Callback<NotificationModel>() {
                    @Override
                    public void onResponse(Call<NotificationModel> call, Response<NotificationModel> response) {
                        if (response.code() >= 400 && response.code() < 500) {
                            if (response.code() == 401) {
                                LogOut.Do(activity, true);
                            } else {
                                notificationEventView.showLoad(false);
                                notificationEventView.showReload(true);
                                ToastCustom.show(0, activity,
                                        activity.getApplicationContext().getString(R.string.not_have_any_notification));
                            }
                        } else {
                            try {
                                notificationEventView.showLoad(false);
                                List<NotificationMap> notificationMapList= response.body().getNotificationMap();
                                Collections.reverse(notificationMapList);
                                notificationEventView.showList(notificationMapList);
                            } catch (Exception e) {
                                notificationEventView.showLoad(false);
                                notificationEventView.showReload(true);
                                Log.e(TAG + " getNotificationLis EXCEPTION", e.toString());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<NotificationModel> call, Throwable t) {
                        notificationEventView.showLoad(false);
                        notificationEventView.showReload(true);
                        Log.e(TAG + " getNotificationLis ONFAILURE", t.toString());
                    }
                });

    }
}

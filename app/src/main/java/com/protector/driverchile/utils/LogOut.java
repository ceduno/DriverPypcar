package com.protector.driverchile.utils;

import android.app.Activity;
import android.content.Intent;

import com.protector.driverchile.R;
import com.protector.driverchile.loginMaster.LoginMasterView;
import com.protector.driverchile.services.TrakingServices;

public class LogOut {

    public static void Do(Activity activity,boolean ban){

        if (ManagerService.isRunning(TrakingServices.class,activity)){
            Intent intent = new Intent(activity, TrakingServices.class);
            activity.stopService(intent);
        }

        SharedPreferenceManager.setLogin(activity,false);
        SharedPreferenceManager.deletUser(activity);

        if(ban){
            ToastCustom.show(0,activity,activity.getString(R.string.expired_session));
        }else{
            ToastCustom.show(0,activity,activity.getString(R.string.disconnect_user));
            SharedPreferenceManager.setTutorial(activity,true);
        }

        activity.startActivity(new Intent(activity, LoginMasterView.class));
        activity.finish();
    }
}

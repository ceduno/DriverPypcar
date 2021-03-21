package com.protector.driverchile.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

public class ManagerService {

    @SuppressLint("LongLogTag")
    public static boolean isRunning(Class<?> serviceClass, Activity activity) {
        try {

            ActivityManager manager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
            for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
                if (serviceClass.getName().equals(service.service.getClassName())) {
                    return true;
                }
            }

        }catch (Exception e){
            Log.e("MANAGERSERVICE Exception", e.toString());
        }

        return false;
    }
}

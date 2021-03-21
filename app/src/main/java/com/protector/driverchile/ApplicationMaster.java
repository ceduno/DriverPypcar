package com.protector.driverchile;

import android.app.Application;
import android.content.Context;

/**
 *
 */
public class ApplicationMaster extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        ApplicationMaster.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return ApplicationMaster.context;
    }

}

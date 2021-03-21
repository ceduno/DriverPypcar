package com.protector.driverchile.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Marlon Viana on 22/03/2019
 * @email 92marlonViana@gmail.com
 */
public class Validation {

    public static boolean email(String stringEmail){
            Pattern pattern = Pattern
                    .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

            Matcher mather = pattern.matcher(stringEmail);

            if (mather.find() == true) {
                return  true;

            } else {
                return  false;
            }
    }

    public static boolean longPass(String pass){
        if (pass.length()>=6){
            return true;
        }else {
            return false;
        }
    }

    public static boolean comparePassword(String pass1,String pass2){
        if (pass1.equals(pass2)){
            return  true;
        }else {
            return false;
        }
    }

    public static boolean isNetDisponible(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();
        return (actNetInfo != null && actNetInfo.isConnected());
    }

    public static String getAppVersion(Context context){
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }
}

package com.protector.driverchile.utils;

import android.content.Context;
import android.widget.Toast;

import com.ib.custom.toast.CustomToastView;

/**

 */
public class ToastCustom {

    public static void show(int prioity, Context context,String message){
        switch (prioity) {
            case 0:
                CustomToastView.makeText(context, Toast.LENGTH_SHORT, CustomToastView.INFO,message,false).show();
                break;
            case 1:
                CustomToastView.makeText(context, Toast.LENGTH_SHORT, CustomToastView.SUCCESS,message,false).show();
                break;
            case 2:
                CustomToastView.makeText(context, Toast.LENGTH_SHORT, CustomToastView.ERROR,message,false).show();
                break;
            case 3:
                CustomToastView.makeText(context, Toast.LENGTH_SHORT, CustomToastView.WARNING,message,false).show();
                break;
        }
    }
}

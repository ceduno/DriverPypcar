package com.protector.driverchile.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;


public class UserPermits{

    private static final int REQUEST_CODE_PERMISSIONS_CAMERA = 111;
    private static final int REQUEST_CODE_PERMISSIONS_PHONE = 222;
    private static final int REQUEST_CODE_PERMISSIONS_GPS = 333;
    private static final int REQUEST_CODE_PERMISSIONS_STORAGE_READ = 444;
    private static final int REQUEST_CODE_PERMISSIONS_STORAGE_WRITE = 555;

    private Activity activity;
    private boolean allPermits;

    public UserPermits(Activity activity,boolean allPermits) {
        this.activity= activity;
        this.allPermits= allPermits;
    }

    public boolean check(){
        boolean statusPermits= false;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {


        }else {

            //if (permitCamera()){
                if (permitPhone()){
                    if (permitGps()){
                        //if (permitStorageWrite()){
                            //if (permitStorageRead()){
                                statusPermits=true;
                            //}
                       // }
                    }
                }
            //}
        }

        return statusPermits;

    }
    public boolean permitPhone(){
        boolean status= false;
        int permissionCheckPhone =
                ContextCompat.checkSelfPermission(activity,Manifest.permission.CALL_PHONE);

        if (permissionCheckPhone != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    activity, new String[]{Manifest.permission.CALL_PHONE},
                    REQUEST_CODE_PERMISSIONS_PHONE);

        }else if (permissionCheckPhone == PackageManager.PERMISSION_GRANTED){
            status= true;
        }
        return status;
    }

    public boolean permitGps(){
        boolean status= false;
        int permissionCheckGps =
                ContextCompat.checkSelfPermission(activity,Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCheckGps != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_PERMISSIONS_GPS);

        }else if (permissionCheckGps == PackageManager.PERMISSION_GRANTED){
            status=true;
        }
        return status;
    }


   /* public boolean permitCamera(){
        boolean status= false;
        int permissionCheckCamera =
                ContextCompat.checkSelfPermission(activity,Manifest.permission.CAMERA);

        if (permissionCheckCamera != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    activity, new String[]{Manifest.permission.CAMERA},
                    REQUEST_CODE_PERMISSIONS_CAMERA);

        }else if (permissionCheckCamera == PackageManager.PERMISSION_GRANTED){
            status=true;
        }
        return status;
    }

    public boolean permitStorageRead(){
        boolean status= false;
        int permissionCheckStorageRead =
                ContextCompat.checkSelfPermission(activity,Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionCheckStorageRead != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISSIONS_STORAGE_READ);

        }else if (permissionCheckStorageRead == PackageManager.PERMISSION_GRANTED){
            status=true;
        }
        return status;
    }

    public boolean permitStorageWrite(){
        boolean status= false;
        int permissionCheckStorageWrite =
                ContextCompat.checkSelfPermission(activity,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheckStorageWrite != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISSIONS_STORAGE_WRITE);

        }else if (permissionCheckStorageWrite == PackageManager.PERMISSION_GRANTED){
            status=true;
        }
        return status;
    }*/

}

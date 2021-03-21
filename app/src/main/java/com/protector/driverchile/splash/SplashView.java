package com.protector.driverchile.splash;

import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.protector.driverchile.homeMaster.HomeMasterView;
import com.protector.driverchile.R;
import com.protector.driverchile.loginMaster.LoginMasterView;
import com.protector.driverchile.utils.SharedPreferenceManager;
import com.protector.driverchile.utils.UserPermits;
/*import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;*/

public class SplashView extends AppCompatActivity implements SplashEventView{

    private static final int REQUEST_CODE_PERMISSIONS_CAMERA = 111;
    private static final int REQUEST_CODE_PERMISSIONS_PHONE = 222;
    private static final int REQUEST_CODE_PERMISSIONS_GPS = 333;
    private static final int REQUEST_CODE_PERMISSIONS_STORAGE_READ = 444;
    private static final int REQUEST_CODE_PERMISSIONS_STORAGE_WRITE = 555;
    private  UserPermits userPermits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Fabric.with(this, new Crashlytics());

        if (SharedPreferenceManager.getLogin(getApplicationContext())){
            goToHome();
        }else {
            createSplash();
        }
    }

    private void createSplash(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                            | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                            | View.SYSTEM_UI_FLAG_IMMERSIVE);

             /*decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);*/

        } else{
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        userPermits= new UserPermits(this,true);

        ViewDataBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_splash);
        SplashViewModel viewModel= new SplashViewModel(this.getApplication(),this,
                userPermits,this);
        binding.setVariable(BR.viewmodel,viewModel);
        binding.setLifecycleOwner(this);
    }

    @Override
    public void goTo(){
        if (SharedPreferenceManager.getLogin(getApplicationContext())){
            goToHome();
        }else {
            goToLogin();
        }
    }

    @Override
    public void goToLogin() {
        startActivity(new Intent(this, LoginMasterView.class));
        overridePendingTransition(R.anim.fade_in_long,R.anim.fade_out_long);
        finish();
    }

    @Override
    public void goToHome() {
        startActivity(new Intent(this, HomeMasterView.class));
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {
            /*case REQUEST_CODE_PERMISSIONS_CAMERA:{
                *//*if (grantResults.length > 0&& grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (userPermits.check()){
                        goTo();
                    }
                } else {
                   userPermits.permitCamera();
                }*//*
                return;
            }*/

            case REQUEST_CODE_PERMISSIONS_PHONE:{
                if (grantResults.length > 0&& grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (userPermits.check()){
                        goTo();
                    }
                } else {
                    userPermits.permitPhone();
                }
                return;
            }

            case REQUEST_CODE_PERMISSIONS_GPS:{
                if (grantResults.length > 0&& grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (userPermits.check()){
                        goTo();
                    }
                } else {
                    userPermits.permitGps();
                }
                return;
            }

            /*case REQUEST_CODE_PERMISSIONS_STORAGE_READ:{
                if (grantResults.length > 0&& grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (userPermits.check()){
                        goTo();
                    }
                } else {
                    userPermits.permitStorageRead();
                }
                return;
            }*/

            /*case REQUEST_CODE_PERMISSIONS_STORAGE_WRITE:{
                if (grantResults.length > 0&& grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (userPermits.check()){
                        goTo();
                    }
                } else {
                    userPermits.permitStorageWrite();
                }
                return;
            }*/
        }
    }


}

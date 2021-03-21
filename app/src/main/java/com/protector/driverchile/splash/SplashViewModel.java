package com.protector.driverchile.splash;

import android.app.Activity;
import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import android.content.Context;
import androidx.annotation.NonNull;

import android.os.Build;
import android.os.Handler;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;

import com.protector.driverchile.utils.DataModelJson.VersionApp;

import com.protector.driverchile.R;

import com.protector.driverchile.httpData.HttpConexion;
import com.protector.driverchile.httpData.User;
import com.protector.driverchile.utils.UserPermits;
import com.protector.driverchile.utils.Validation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
/
 */
public class SplashViewModel extends AndroidViewModel{
    private String TAG="SPLASHVIEWMODEL";
    private Context context;
    private MutableLiveData<String> message;
    private Application application;
    private SplashEventView splashEventView;
    private Activity activity;
    private UserPermits userPermits;

    public  SplashViewModel(@NonNull Application application, Activity activity,UserPermits userPermits,SplashEventView splashEventView) {
        super(application);
        this.application= application;
        context= application.getApplicationContext();
        this.splashEventView= splashEventView;
        this.activity= activity;
        this.userPermits= userPermits;

        message= new MutableLiveData<>();

        validationMovil();
        validationPermits();
    }

    //region GET and SET
    public MutableLiveData<String> getMessage() {
        return message;
    }

    public void setMessage(MutableLiveData<String> message) {
        this.message = message;
    }
    //endregion

    private void validationMovil(){
        message.setValue(context.getString(R.string.validating_movil));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            validationPermits();
        }else {
            message.setValue(context.getString(R.string.validating_movil_fail));
        }
    }

    private void validationPermits(){
        message.setValue(context.getString(R.string.buying_user_permis));
        //splashEventView.goTo();
        new Handler().postDelayed(()->{

            if (userPermits.check()){
                splashEventView.goTo();
            }

        },3000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Write whatever to want to do after delay specified (1 sec)
                Log.d("Handler", "Running Handler");
            }
        }, 1000);

    }


}

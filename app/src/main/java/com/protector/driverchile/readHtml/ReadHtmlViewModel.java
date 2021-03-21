package com.protector.driverchile.readHtml;

import android.annotation.SuppressLint;
import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.protector.driverchile.R;
import com.protector.driverchile.httpData.HttpConexion;
import com.protector.driverchile.httpData.User;
import com.protector.driverchile.utils.DataModelJson.HtmlModel;
import com.protector.driverchile.utils.ToastCustom;
import com.protector.driverchile.utils.Validation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("LongLogTag")
public class ReadHtmlViewModel extends AndroidViewModel {
    private String TAG= "ReadHtmViewModel";
    private Application application;
    private Context context;
    private ReadHtmlEventView readHtmlEventView;
    private int action;

    public ReadHtmlViewModel(Application application, ReadHtmlEventView readHtmlEventView,int action) {
        super(application);
        this.application= application;
        this.readHtmlEventView= readHtmlEventView;
        this.context= application.getApplicationContext();
        this.action= action;

        loadHtml();
    }


    public void reload(){
        loadHtml();
    }


    private void loadHtml(){

        if (Validation.isNetDisponible(getApplication())){
            if (action==1){
                aboutUS();
            }else if (action==2){
                privacyPolicy();
            }else if (action==3){
                conditions();
            }
        }else{
            ToastCustom.show(3,context,context.getString(R.string.without_internet));
            readHtmlEventView.showReload(true);
            readHtmlEventView.showLoad(false);
        }
    }

    private void aboutUS(){
        readHtmlEventView.showLoad(true);
        readHtmlEventView.showReload(false);

        HttpConexion.getUri()
                .create(User.class)
                .aboutUs()
                .enqueue(new Callback<HtmlModel>() {
                    @Override
                    public void onResponse(Call<HtmlModel> call, Response<HtmlModel> response) {
                        try {
                            readHtmlEventView.showLoad(false);
                            readHtmlEventView.showText(response.body().getAboutUs());
                        }catch (Exception e){
                            readHtmlEventView.showLoad(false);
                            readHtmlEventView.showReload(true);
                            Log.e(TAG+" aboutus EXCEPTION",e.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<HtmlModel> call, Throwable t) {
                        readHtmlEventView.showLoad(false);
                        readHtmlEventView.showReload(true);
                        Log.e(TAG+" aboutus ONFAILURE",t.toString());
                    }
                });
    }

    private void privacyPolicy(){
        readHtmlEventView.showLoad(true);
        readHtmlEventView.showReload(false);

        HttpConexion.getUri()
                .create(User.class)
                .privacyPolicy()
                .enqueue(new Callback<HtmlModel>() {

                    @Override
                    public void onResponse(Call<HtmlModel> call, Response<HtmlModel> response) {
                        try {
                            readHtmlEventView.showLoad(false);
                            readHtmlEventView.showText(response.body().getPrivacyPolicy());
                        }catch (Exception e){
                            readHtmlEventView.showLoad(false);
                            readHtmlEventView.showReload(true);
                            Log.e(TAG+" privacyPolicy EXCEPTION",e.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<HtmlModel> call, Throwable t) {
                        readHtmlEventView.showLoad(false);
                        readHtmlEventView.showReload(true);
                        Log.e(TAG+" privacyPolicy ONFAILURE",t.toString());
                    }
                });
    }

    private void conditions(){
        readHtmlEventView.showLoad(true);
        readHtmlEventView.showReload(false);

        HttpConexion.getUri()
                .create(User.class)
                .termsCondition()
                .enqueue(new Callback<HtmlModel>() {

                    @Override
                    public void onResponse(Call<HtmlModel> call, Response<HtmlModel> response) {
                        try {
                            readHtmlEventView.showLoad(false);
                            readHtmlEventView.showText(response.body().getTermsConditions());
                        }catch (Exception e){
                            readHtmlEventView.showLoad(false);
                            readHtmlEventView.showReload(true);
                            Log.e(TAG+" conditions EXCEPTION",e.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<HtmlModel> call, Throwable t) {
                        readHtmlEventView.showLoad(false);
                        readHtmlEventView.showReload(true);
                        Log.e(TAG+" conditions ONFAILURE",t.toString());
                    }
                });
    }


}

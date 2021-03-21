package com.protector.driverchile.contactUs;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;

import com.protector.driverchile.R;
import com.protector.driverchile.httpData.HttpConexion;
import com.protector.driverchile.httpData.User;
import com.protector.driverchile.utils.DataModelJson.ContactModel;
import com.protector.driverchile.utils.ToastCustom;
import com.protector.driverchile.utils.Validation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUsViewModel extends AndroidViewModel {
    private String TAG="ContactUsViewModel ";
    private Application application;
    private ContactUsEventView contactUsEventView;

    public ContactUsViewModel(Application application,ContactUsEventView contactUsEventView) {
        super(application);
        this.application= application;
        this.contactUsEventView= contactUsEventView;

        reload();
    }

    public void reload(){
        if (Validation.isNetDisponible(getApplication())){
            getContacUs();
        }else{
            ToastCustom.show(3,application.getApplicationContext(),application.getString(R.string.without_internet));
            contactUsEventView.showReload(true);
            contactUsEventView.showLoad(false);
        }
    }

    private void getContacUs(){
        contactUsEventView.showLoad(true);
        contactUsEventView.showReload(false);

        HttpConexion.getUri()
                .create(User.class)
                .contactUS()
                .enqueue(new Callback<ContactModel>() {
                    @Override
                    public void onResponse(Call<ContactModel> call, Response<ContactModel> response) {
                        try {
                            contactUsEventView.showLoad(false);
                            contactUsEventView.showContect(response.body());

                        }catch (Exception e){
                            contactUsEventView.showLoad(false);
                            contactUsEventView.showReload(true);
                            Log.e(TAG+" getContacUs EXCEPTION",e.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<ContactModel> call, Throwable t) {
                        contactUsEventView.showLoad(false);
                        contactUsEventView.showReload(true);
                        Log.e(TAG+" getContacUs ONFAILURE",t.toString());
                    }
                });
    }
}

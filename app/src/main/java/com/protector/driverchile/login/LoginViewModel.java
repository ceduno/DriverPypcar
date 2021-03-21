package com.protector.driverchile.login;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.protector.driverchile.httpData.HttpConexion;
import com.protector.driverchile.httpData.User;
import com.protector.driverchile.utils.SharedPreferenceManager;
import com.protector.driverchile.utils.ToastCustom;
import com.protector.driverchile.utils.Validation;

import com.protector.driverchile.R;
import com.protector.driverchile.utils.DataModelJson.DriverPojo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**

 */
public class LoginViewModel extends AndroidViewModel {
    private String TAG = "LOGINVIEWMODEL";
    private LoginModel loginModel;
    private MutableLiveData<String> user, pass;
    private MutableLiveData<Boolean> requestFocusEmail, requestFocusPass;
    private LoginEventView loginEventView;
    private Context context;

    public LoginViewModel( Application application,LoginEventView loginEventView){
        super(application);
        context= application.getApplicationContext();
        this.loginEventView= loginEventView;

        this.loginModel= new LoginModel();
        user= new MutableLiveData<>();
        pass= new MutableLiveData<>();
    }

    //region GET and SET
    public void setRequestFocusEmail(MutableLiveData<Boolean> requestFocusEmail) {
        this.requestFocusEmail = requestFocusEmail;
    }

    public void setRequestFocusPass(MutableLiveData<Boolean> requestFocusPass) {
        this.requestFocusPass = requestFocusPass;
    }

    public MutableLiveData<String> getUser() {
        loginEventView.colorEmail(true);
        loginModel.setEmail(user.getValue());
        return user;
    }

    public MutableLiveData<String> getPass() {
        loginEventView.colorPass(true);
        loginModel.setPass(pass.getValue());
        return pass;
    }
    //endregion

    //region Button
    public void doLogin(){
        if (loginModel.getEmail()==null || loginModel.getPass()==null){

            ToastCustom.show(0,context,context.getResources().getString(R.string.fill_edit));

            if (loginModel.getEmail()==null){
                loginEventView.requestEmail();
            }else {
                loginEventView.requestPass();
            }

        }else {
           if (!Validation.email(loginModel.getEmail())){
                ToastCustom.show(3,context,context.getString(R.string.invalid_email));
                loginEventView.requestEmail();
                loginEventView.colorEmail(false);
            }else if (!Validation.longPass(loginModel.getPass())){
                ToastCustom.show(3,context,context.getString(R.string.invalid_long_pass));
                loginEventView.requestPass();
                loginEventView.colorPass(false);
            }else {
                loginRequest();
            }

        }

    }

    public void doRegistry(){
        loginEventView.goToRegistry();
    }

    public void goToRegister(){
        loginEventView.goToRegisterDriver();
    }

    public void forgotPass(){
        loginEventView.showDialgoRecoveryPass();
    }

    public void showPass(){
        loginEventView.showPassword();
    }
    //endregion

    //region Request
    private void loginRequest(){

        if (Validation.isNetDisponible(context)){

            loginEventView.enableButtonLogin(false);
            loginEventView.hideKeyBoard();

            HttpConexion.getUri().create(User.class)
                    .login(loginModel)
                    .enqueue(new Callback<DriverPojo>() {
                @Override
                public void onResponse(Call<DriverPojo> call, Response<DriverPojo> response) {
                    loginEventView.enableButtonLogin(true);
                    Log.d("RESPUESTA LOGIN HEADER",response.toString());
                    Log.d("ENVIO USER",loginModel.getEmail()+"  "+loginModel.getPass());

                    if (response.body()==null){
                        ToastCustom.show(
                                2,
                                context,
                                context.getString(R.string.invalid_credentials));
                    }else {
                        try {
                            Log.d("RESPEUSTGA LOGIN BODY",response.body().makeJson());
                            if (response.body().getRoleId().equals("6")){

                                SharedPreferenceManager.setUser(context,response.body());

                                if (response.body().getVerified()){
                                     loginEventView.goToHome();
                                }else {
                                    loginEventView.showDialgoAutheb(response.body().getEmail(),
                                            response.body().getApiSessionKey());
                                }

                            }else {
                                loginEventView.showDialgoInfo(
                                        context.getString(R.string.information),
                                        context.getString(R.string.account_no_driver)
                                );
                            }

                        }catch (Exception e){
                            Log.e(TAG+" loginRequest Exception",e.toString());
                            ToastCustom.show(
                                    3,
                                    context,
                                    context.getString(R.string.something_has_failed));
                        }
                    }

                }

                @Override
                public void onFailure(Call<DriverPojo> call, Throwable t) {
                    loginEventView.enableButtonLogin(true);
                    Log.e(TAG+" loginRequest OnFailure",t.toString());
                    loginEventView.showDialgoInfo(
                            context.getString(R.string.ups),
                            context.getString(R.string.something_has_failed));
                }

            });
        }else {
            ToastCustom.show(3,context,context.getString(R.string.without_internet));
        }
    }
    //endregion

}

package com.protector.driverchile.loginMaster;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import com.protector.driverchile.homeMaster.HomeMasterView;
import com.protector.driverchile.login.LoginView;
import com.protector.driverchile.registerDriver.RegisterView;

import com.protector.driverchile.R;
import com.protector.driverchile.utils.SharedPreferenceManager;

/**

 */
public class LoginMasterView extends FragmentActivity implements LoginMasterEventView {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        goLogin();
    }

    public void goLogin(){
        LoginView fragment = new LoginView(this);
        fragmentTransaction.replace(R.id.fml_login, fragment,"LOGIN_FRAGMENT_TAG");
        fragmentTransaction.commit();
    }

    public void goRegister(){
        RegisterView fragment = new RegisterView();
        fragmentTransaction.replace(R.id.fml_login, fragment,"REGISTER_FRAGMENT_TAG");
        fragmentTransaction.commit();
    }

    @Override
    public void goToHome() {
        SharedPreferenceManager.setLogin(this,true);
        startActivity(new Intent(this, HomeMasterView.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        RegisterView fragment= (RegisterView) fragmentManager.findFragmentByTag("REGISTER_FRAGMENT_TAG");
       if (fragment!=null){
            goLogin();
       }else {
           super.onBackPressed();
       }
    }

}

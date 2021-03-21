package com.protector.driverchile.login;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.protector.driverchile.ApplicationMaster;
import com.protector.driverchile.BuildConfig;
import com.protector.driverchile.dialog.DialogAuthentication;
import com.protector.driverchile.dialog.DialogRecoveryPass;
import com.protector.driverchile.homeMaster.HomeMasterView;

import com.protector.driverchile.R;
import androidx.databinding.library.baseAdapters.BR;

import com.protector.driverchile.loginMaster.LoginMasterEventView;
import com.protector.driverchile.utils.DialogInfo;
import com.protector.driverchile.utils.SharedPreferenceManager;
import com.protector.driverchile.webMaster.WebMasterView;


@SuppressLint("ValidFragment")
public class LoginView extends Fragment implements LoginEventView{
    LoginMasterEventView loginMasterEventView;
    private EditText editTextEmail,editTextPass;
    private Button buttonLogin;
    private View view;
    private ImageView imageViewEyes,imageViewEmail,imageViewPass;
    private ConstraintLayout constraintLayoutLogin;

    public LoginView(LoginMasterEventView loginMasterEventView){
        this.loginMasterEventView= loginMasterEventView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false);
        LoginViewModel loginViewModel= new LoginViewModel(getActivity().getApplication(),this);
        binding.setVariable(BR.viewmodel,loginViewModel);
        binding.setLifecycleOwner(getActivity());
        view= binding.getRoot();
        addView();
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void addView(){
        editTextEmail= view.findViewById(R.id.edit_email);
        editTextPass= view.findViewById(R.id.edit_pass);
        buttonLogin= view.findViewById(R.id.btn_login);
        imageViewEyes= view.findViewById(R.id.img_eye);
        imageViewEmail= view.findViewById(R.id.img_email);
        imageViewPass= view.findViewById(R.id.img_password);

        constraintLayoutLogin= view.findViewById(R.id.constraintLayout_login);
   /*     LayoutAnimationController controller= AnimationUtils.loadLayoutAnimation
                (constraintLayoutLogin.getContext(),R.anim.animlt_up);
        constraintLayoutLogin.setLayoutAnimation(controller);
        constraintLayoutLogin.scheduleLayoutAnimation();*/
    }

    @Override
    public void requestEmail() {
        editTextEmail.requestFocus();
        editTextEmail.setSelection(editTextEmail.getText().toString().length());
    }

    @Override
    public void requestPass() {
        editTextPass.requestFocus();
        editTextPass.setSelection(editTextPass.getText().toString().length());
    }

    @Override
    public void enableButtonLogin(boolean ban) {
        buttonLogin.setEnabled(ban);
        if (ban){
            buttonLogin.setText(R.string.login);
        }else {
            buttonLogin.setText(R.string.loading);
        }
    }

    @Override
    public void goToRegisterDriver() {
        loginMasterEventView.goRegister();
    }

    @Override
    public void goToHome() {
       loginMasterEventView.goToHome();
    }

    @Override
    public void hideKeyBoard() {
        InputMethodManager imm = (InputMethodManager)getActivity().getApplication().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editTextPass.getWindowToken(), 0);
    }

    @Override
    public void showPassword() {
        imageViewEyes.setColorFilter(getContext().getResources().getColor(R.color.colorGrayDark));
        editTextPass.setTransformationMethod(null);

        new Handler().postDelayed(()->{
            editTextPass.setTransformationMethod(new PasswordTransformationMethod());
            imageViewEyes.setColorFilter(getContext().getResources().getColor(R.color.colorGray));
        },1500);

    }

    @Override
    public void colorEmail(boolean ban) {
        if (ban){
            imageViewEmail.setColorFilter(getContext().getResources().getColor(R.color.colorGray));
        }else {
            imageViewEmail.setColorFilter(getContext().getResources().getColor(R.color.colorRed));
        }
    }

    @Override
    public void colorPass(boolean ban) {
        if (ban){
            imageViewPass.setColorFilter(getContext().getResources().getColor(R.color.colorGray));
        }else {
            imageViewPass.setColorFilter(getContext().getResources().getColor(R.color.colorRed));
        }
    }

    @Override
    public void showDialgoInfo(String title,String info) {
        DialogInfo dialogInfo= new DialogInfo(title,info);
        dialogInfo.show(getFragmentManager(),"DIALOG_INFO");
        dialogInfo.setCancelable(true);
    }

    @Override
    public void showDialgoAutheb(String email,String apiKey) {
        DialogAuthentication dialogAuthentication= new DialogAuthentication(email,apiKey,this);
        dialogAuthentication.show(getFragmentManager(),"DIALOG_AUT");
        dialogAuthentication.setCancelable(false);
    }

    @Override
    public void showDialgoRecoveryPass() {
        DialogRecoveryPass dialogRecoveryPass= new DialogRecoveryPass();
        dialogRecoveryPass.show(getFragmentManager(),"DIALOG_RECOVER");
        dialogRecoveryPass.setCancelable(false);
    }

    @Override
    public void goToRegistry() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.URL_REGISTRY));
        startActivity(browserIntent);
    }


}

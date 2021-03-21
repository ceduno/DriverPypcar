package com.protector.driverchile.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;

import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.MutableLiveData;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.protector.driverchile.ApplicationMaster;
import com.protector.driverchile.R;
import com.protector.driverchile.databinding.DialogAuthenticationBinding;
import com.protector.driverchile.httpData.HttpConexion;
import com.protector.driverchile.httpData.User;
import com.protector.driverchile.login.LoginEventView;
import com.protector.driverchile.utils.DataModelJson.MessagePojo;
import com.protector.driverchile.utils.ToastCustom;
import com.protector.driverchile.utils.Validation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("ValidFragment")
public class DialogAuthentication extends DialogFragment {
    private String TAG = "DIALOG AUTHENTICATION";
    private MutableLiveData<String> code;
    private String email;
    private String apiKey;
    private View v;
    private TextView textViewMessage;
    private Button buttonResendCode,buttonSendEmail;
    private LoginEventView loginEventView;

    public DialogAuthentication(String email,String apiKey,LoginEventView loginEventView) {
        this.email= email;
        this.apiKey= apiKey;
        this.loginEventView= loginEventView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        code = new MutableLiveData<>();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(getContext()), R.layout.dialog_authentication, null, false);
        binding.setLifecycleOwner(getActivity());
        binding.setVariable(BR.view,this);
        v= binding.getRoot();
        builder.setView(v);

        addView();
        return builder.create();
    }

    private void addView() {
        textViewMessage= v.findViewById(R.id.txv_message);
        textViewMessage.setText(ApplicationMaster.getAppContext().getString(R.string.mesage_verification_email)+" "+email);

        buttonResendCode= v.findViewById(R.id.btn_resend);
        buttonSendEmail= v.findViewById(R.id.btn_send);
    }

    public MutableLiveData<String> getCode() {
        return code;
    }

    public void setCode(MutableLiveData<String> code) {
        this.code = code;
    }

    //region BUTTONS
    public void closeDialog(){
        dismiss();
    }

    public void sendCode(){
        if (Validation.isNetDisponible(getContext())){
            if (code.getValue()!=null){
                apiSendCode();
            }
        }else {
            ToastCustom.show(3,getContext(),ApplicationMaster.getAppContext().getString(R.string.without_internet));
        }
    }

    public void resendEmail(){
        if (Validation.isNetDisponible(getContext())){
            apiResendEmail();
        }else {
            ToastCustom.show(3,getContext(),ApplicationMaster.getAppContext().getString(R.string.without_internet));
        }
    }

    private void statusButtonSend(boolean ban){
        buttonSendEmail.setEnabled(ban);
        if (ban){
            buttonSendEmail.setText(ApplicationMaster.getAppContext().getString(R.string.send_code));
            buttonResendCode.setVisibility(View.VISIBLE);
        }else {
            buttonSendEmail.setText(ApplicationMaster.getAppContext().getString(R.string.sending));
            buttonResendCode.setVisibility(View.GONE);
        }
    }

    private void statusButtonReSend(boolean ban){
        buttonResendCode.setEnabled(ban);
        if (ban){
            buttonResendCode.setText(ApplicationMaster.getAppContext().getString(R.string.resend_email));
            buttonSendEmail.setVisibility(View.VISIBLE);
        }else {
            buttonResendCode.setText(ApplicationMaster.getAppContext().getString(R.string.resending));
            buttonSendEmail.setVisibility(View.GONE);
        }
    }
    //endregion

    //region CALL API
    @SuppressLint("LongLogTag")
    private void apiSendCode(){
        statusButtonSend(false);
        HttpConexion.getUri().create(User.class)
                .sentCode(apiKey,"es",code.getValue()).enqueue(new Callback<MessagePojo>() {
            @Override
            public void onResponse(Call<MessagePojo> call, Response<MessagePojo> response) {
                statusButtonSend(true);
                Log.d("RESPUESTA SENT CODE HEADER",response.toString());

                try {
                    Log.d("RESPEUSTGA SEND CODE BODY",response.body().makeJson());
                    if (response.body().getType().equals("SUCCESS")){
                        loginEventView.goToHome();
                    }else {
                        ToastCustom.show(2
                                ,getContext()
                                ,ApplicationMaster.getAppContext().getString(R.string.expired_session));
                    }
                }catch (Exception e){
                    Log.e(TAG+" apiSendCode Exception",e.toString());
                    ToastCustom.show(2
                            ,getContext()
                            ,ApplicationMaster.getAppContext().getString(R.string.invalid_code));
                }
            }

            @Override
            public void onFailure(Call<MessagePojo> call, Throwable t) {
                statusButtonSend(true);
                Log.e(TAG+" apiSendCode OnFailure",t.toString());
                ToastCustom.show(0
                        ,getContext()
                        ,ApplicationMaster.getAppContext().getString(R.string.something_has_failed));
            }
        });

    }

    @SuppressLint("LongLogTag")
    private void apiResendEmail(){
        statusButtonReSend(false);
        HttpConexion.getUri().create(User.class)
                .resentCode(apiKey,"es").enqueue(new Callback<MessagePojo>() {
            @Override
            public void onResponse(Call<MessagePojo> call, Response<MessagePojo> response) {
                statusButtonReSend(true);
                Log.d("RESPUESTA RESENT CODE HEADER",response.toString());

                try {
                    Log.d("RESPEUSTGA RESEND CODE BODY",response.body().makeJson());
                    if (response.body().getType().equals("SUCCESS")){
                        ToastCustom.show(1
                                ,getContext()
                                ,ApplicationMaster.getAppContext().getString(R.string.code_send_succes));
                    }else {
                        ToastCustom.show(2
                                ,getContext()
                                ,ApplicationMaster.getAppContext().getString(R.string.expired_session));
                    }
                }catch (Exception e){
                    Log.e(TAG+" apiResendEmail Exception",e.toString());
                    ToastCustom.show(0
                            ,getContext()
                            ,ApplicationMaster.getAppContext().getString(R.string.something_has_failed));
                }
            }

            @Override
            public void onFailure(Call<MessagePojo> call, Throwable t) {
                statusButtonReSend(true);
                Log.e(TAG+" apiResendEmail OnFailure",t.toString());
                ToastCustom.show(0
                        ,getContext()
                        ,ApplicationMaster.getAppContext().getString(R.string.something_has_failed));
            }
        });
    }

    //endregion
}

package com.protector.driverchile.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;

import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.MutableLiveData;
import android.content.Context;
import androidx.databinding.DataBindingUtil;
import com.protector.driverchile.databinding.DialogRecoveryPassBinding;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.protector.driverchile.R;
import com.protector.driverchile.httpData.HttpConexion;
import com.protector.driverchile.httpData.User;
import com.protector.driverchile.login.LoginModel;
import com.protector.driverchile.utils.DataModelJson.MessagePojo;
import com.protector.driverchile.utils.ToastCustom;
import com.protector.driverchile.utils.Validation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogRecoveryPass extends DialogFragment {
    private String TAG = "DIALOG RECOVERUPASS";
    private View v;
    private Button buttonRecovery;
    private EditText editTextEmail;
    private ImageView imageViewEmail;
    private ViewDataBinding binding;
    private MutableLiveData<String> email;
    private LoginModel loginModel;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        loginModel= new LoginModel();
        email= new MutableLiveData<>();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(getContext()), R.layout.dialog_recovery_pass, null, false);
        binding.setLifecycleOwner(getActivity());
        binding.setVariable(BR.view,this);
        v= binding.getRoot();
        builder.setView(v);

        addView();
        return builder.create();
    }

    public MutableLiveData<String> getEmail() {
        loginModel.setEmail(email.getValue());
        colorEmail(true);
        return email;
    }

    public void setEmail(MutableLiveData<String> email) {
        this.email = email;
    }


    private void addView() {
        buttonRecovery= v.findViewById(R.id.btn_recovery);
        imageViewEmail= v.findViewById(R.id.img_email);
        editTextEmail= v.findViewById(R.id.edit_email);
    }

    public void doRecoveryPass(){
        if (loginModel.getEmail()!=null){

            if (!Validation.email(email.getValue())){
                ToastCustom.show(3,getActivity(),getActivity().getString(R.string.invalid_email));
                editTextEmail.requestFocus();
                colorEmail(false);
            }else{
                hideKeyBoard();
                doforgotPass();
            }

        }
    }

    public void closeDialog(){
        dismiss();
    }

    public void enableButton(boolean status){
        buttonRecovery.setEnabled(status);
        if (status){
            buttonRecovery.setText(getString(R.string.send));
        }else {
            buttonRecovery.setText(R.string.sending);
        }
    }

    public void colorEmail(boolean ban) {
        if (ban){
            imageViewEmail.setColorFilter(getContext().getResources().getColor(R.color.colorPrimaryDark));
        }else {
            imageViewEmail.setColorFilter(getContext().getResources().getColor(R.color.colorRed));
        }
    }

    public void hideKeyBoard() {
        InputMethodManager imm = (InputMethodManager)getActivity().getApplication().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editTextEmail.getWindowToken(), 0);
    }

    @SuppressLint("LongLogTag")
    private void doforgotPass(){
        if (Validation.isNetDisponible(getContext())){

           enableButton(false);
            HttpConexion.getUri().create(User.class)
                    .forgotPass(loginModel.getEmail())
                    .enqueue(new Callback<MessagePojo>() {
                        @Override
                        public void onResponse(Call<MessagePojo> call, Response<MessagePojo> response) {
                            enableButton(true);
                            Log.d("RESPUESTA FORGOTPASS HEADER",response.toString());

                                try {
                                    Log.d("RESPEUSTGA FORGOTPASS BODY",response.body().makeJson());
                                    if (response.body().getType().equals("SUCCESS")){
                                        ToastCustom.show(1,getContext(),
                                                getContext().getString(R.string.check_email_password));

                                        new Handler().postDelayed(()->{dismiss();}, 2000);
                                    }else {
                                        ToastCustom.show(2,
                                                getContext(),getContext().getString(R.string.email_not_found));
                                    }
                                }catch (Exception e){
                                    Log.e(TAG+" ForgotPassRequest Exception",e.toString());
                                    ToastCustom.show(2,
                                            getContext(),getContext().getString(R.string.email_not_found));
                                }
                        }

                        @Override
                        public void onFailure(Call<MessagePojo> call, Throwable t) {
                            enableButton(true);
                            Log.e(TAG+" ForgoPassRequest OnFailure",t.toString());
                            ToastCustom.show(0,
                                    getContext(),getContext().getString(R.string.something_has_failed));
                        }

                    });
        }else {
            ToastCustom.show(3,getContext(),getContext().getString(R.string.without_internet));
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.unbind();
    }
}

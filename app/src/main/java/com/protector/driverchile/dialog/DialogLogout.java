package com.protector.driverchile.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.protector.driverchile.ApplicationMaster;
import com.protector.driverchile.R;
import com.protector.driverchile.homeMaster.HomeMasterEventView;
import com.protector.driverchile.httpData.HttpConexion;
import com.protector.driverchile.httpData.User;
import com.protector.driverchile.utils.DataModelJson.DriverPojo;
import com.protector.driverchile.utils.DataModelJson.MessagePojo;
import com.protector.driverchile.utils.LogOut;
import com.protector.driverchile.utils.SharedPreferenceManager;
import com.protector.driverchile.utils.ToastCustom;
import com.protector.driverchile.utils.Validation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("ValidFragment")
public class DialogLogout extends DialogFragment {
    private String TAG = "DIALOGLOGOUT";
    private HomeMasterEventView homeMasterEventView;
    private Button buttonOk,buttonNo;
    private View v;


    public DialogLogout(HomeMasterEventView homeMasterEventView) {
        this.homeMasterEventView= homeMasterEventView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(getContext()), R.layout.dialog_logout, null, false);

        binding.setLifecycleOwner(getActivity());
        binding.setVariable(BR.viewModel,this);
        v= binding.getRoot();
        builder.setView(v);

        addView();
        return builder.create();
    }

    private void addView(){
        buttonNo= v.findViewById(R.id.btn_no);
        buttonOk= v.findViewById(R.id.btn_ok);
    }

    @SuppressLint("LongLogTag")
    public void doLogout() {
        if (Validation.isNetDisponible(getContext())){
            statusButton(false);

            DriverPojo driverPojo= SharedPreferenceManager.getUser(ApplicationMaster.getAppContext());
            HttpConexion.getUri()
                    .create(User.class)
                    .logOut(driverPojo.getApiSessionKey(),"es")
                    .enqueue(new Callback<MessagePojo>() {

                @Override
                public void onResponse(Call<MessagePojo> call, Response<MessagePojo> response) {
                    statusButton(true);
                    Log.d("TAG"+"respuesta Logout HEADER",response.toString());

                    if (response.code()>=400 && response.code()<500){
                        LogOut.Do(getActivity(),true);
                    }else{
                        try {
                            Log.d("TAG"+"respuesta Logout BODY",response.body().makeJson());
                            if (response.body().getType().equals("SUCCESS")){
                                dismiss();
                                LogOut.Do(getActivity(),false);
                            }else {
                                ToastCustom.show(2,
                                        getContext(),getContext().getString(R.string.email_not_found));
                            }
                        }catch (Exception e){
                            Log.e(TAG+" ForgotPassRequest Exception",e.toString());
                            ToastCustom.show(0,
                                    getContext(),getContext().getString(R.string.something_has_failed));
                        }
                    }

                }

                @Override
                public void onFailure(Call<MessagePojo> call, Throwable t) {
                    statusButton(true);
                    Log.e(TAG+" logoutRequest OnFailure",t.toString());
                    ToastCustom.show(0,
                            getContext(),getContext().getString(R.string.something_has_failed));
                }

            });

        }else {
            ToastCustom.show(3,getContext(),getContext().getString(R.string.without_internet));
        }
    }

    public void close(){
        dismiss();
    }

    private void  statusButton(boolean ban){
        buttonOk.setEnabled(ban);
        if (ban){
            buttonOk.setText(R.string.disconnect);
            buttonNo.setVisibility(View.VISIBLE);
        }else {
            buttonOk.setText(R.string.disconnecting);
            buttonNo.setVisibility(View.GONE);
        }
    }
}

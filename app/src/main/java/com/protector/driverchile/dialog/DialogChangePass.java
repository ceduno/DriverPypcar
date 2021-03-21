package com.protector.driverchile.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;

import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.protector.driverchile.R;
import com.protector.driverchile.databinding.DialogChangePassBinding;
import com.protector.driverchile.httpData.HttpConexion;
import com.protector.driverchile.httpData.User;
import com.protector.driverchile.utils.DataModelJson.ChangePass;
import com.protector.driverchile.utils.DataModelJson.DriverPojo;
import com.protector.driverchile.utils.DataModelJson.MessagePojo;
import com.protector.driverchile.utils.LogOut;
import com.protector.driverchile.utils.SharedPreferenceManager;
import com.protector.driverchile.utils.ToastCustom;
import com.protector.driverchile.utils.Validation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@SuppressLint("LongLogTag")
public class DialogChangePass extends DialogFragment {
    private String TAG = "DIALOGLOGOUT";

    private MutableLiveData<String> passOld,passNew,passRepeat;
    private DriverPojo driverPojo;
    private ViewDataBinding binding;
    private EditText editTextOldPass,editTextNewPass,editTextRepeatPass;
    private ImageView imageViewOldPass,imageViewNewPass,imageViewRepeatPass;
    private Button buttonChange;
    private View v;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        passOld= new MutableLiveData<>();
        passNew= new MutableLiveData<>();
        passRepeat= new MutableLiveData<>();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.dialog_change_pass, null, false);

        binding.setLifecycleOwner(getActivity());
        binding.setVariable(BR.view,this);
        v= binding.getRoot();
        builder.setView(v);

        addView();
        return builder.create();
    }

    private void addView() {
        driverPojo= SharedPreferenceManager.getUser(getActivity());
        Log.e("DRIVER",driverPojo.makeJson());

        editTextOldPass= v.findViewById(R.id.edit_pass_old);
        editTextNewPass= v.findViewById(R.id.edit_pass_new);
        editTextRepeatPass= v.findViewById(R.id.edit_repeat_pass);

        imageViewOldPass= v.findViewById(R.id.img_passOld);
        imageViewNewPass= v.findViewById(R.id.img_passNew);
        imageViewRepeatPass= v.findViewById(R.id.img_passRepeat);

        buttonChange= v.findViewById(R.id.btn_change);
    }

    //region GET - SET
    public MutableLiveData<String> getPassOld() {
        return passOld;
    }

    public MutableLiveData<String> getPassNew() {
        return passNew;
    }

    public MutableLiveData<String> getPassRepeat() {
       validateRepeatPass();
        return passRepeat;
    }
    //endregion

    //region BOTONES
    public void doChangePass(){
        if (passOld.getValue()!=null){
            if (validateNewPass()){
                if (validateRepeatPass()){
                    changePass();
                }else {
                    ToastCustom.show(3,getActivity(),
                            getActivity().getString(R.string.pass_no_equal));

                    editTextRepeatPass.requestFocus();
                }
            }
        }else {
            ToastCustom.show(0,getContext(),getResources().getString(R.string.fill_edit));
            editTextOldPass.requestFocus();
        }
    }

    public void closeDialog(){
        dismiss();
    }
    //endregion

    private boolean validateNewPass(){
        boolean ban;
        if (passNew.getValue()!=null){
            if (Validation.longPass(passNew.getValue().trim())){
                ban= true;
            }else {
                ToastCustom.show(3,getActivity(),
                        getActivity().getString(R.string.invalid_long_pass));

                ban= false;
            }
        }else {
            ToastCustom.show(0,getContext(),getResources().getString(R.string.fill_edit));
            editTextNewPass.requestFocus();

            ban= false;
        }
        return ban;
    }

    private boolean validateRepeatPass(){
        boolean ban= false;
        if (passRepeat.getValue()!=null){
            if (passNew.getValue()!=null){
                if (passRepeat.getValue().trim().equals(passNew.getValue().trim())){
                    imageViewRepeatPass.setColorFilter(getActivity()
                            .getResources().getColor(R.color.colorGreen));

                    ban= true;
                }else {
                    imageViewRepeatPass.setColorFilter(getActivity()
                            .getResources().getColor(R.color.colorRed));
                }
            }
        }
        return ban;
    }

    private void changePass(){
        if (Validation.isNetDisponible(getActivity())){
            statusButton(false);

            ChangePass changePass= new ChangePass();
            changePass.setOldPassword(passOld.getValue());
            changePass.setNewPassword(passRepeat.getValue());

            DriverPojo driverPojo= SharedPreferenceManager.getUser(getContext());
            HttpConexion.getUri()
                    .create(User.class)
                    .changePass(driverPojo.getApiSessionKey(),"es",changePass)
                    .enqueue(new Callback<MessagePojo>() {
                        @Override
                        public void onResponse(Call<MessagePojo> call, Response<MessagePojo> response) {
                            if (response.code()>=400 && response.code()<500){
                                LogOut.Do(getActivity(),true);
                            }else{
                                statusButton(true);
                                try {
                                    Log.d("TAG"+"respuesta changePass BODY",response.body().makeJson());
                                    if (response.body().getType().equals("SUCCESS")){
                                        ToastCustom.show(1,
                                                getContext(),
                                                getContext().
                                                        getString(R.string.password_changed_successfully));

                                        new Handler().postDelayed(()->{dismiss();}, 2000);

                                    }else {
                                        ToastCustom.show(2,
                                                getContext(),
                                                getContext().
                                                        getString(R.string.old_password_invalidates));
                                    }
                                }catch (Exception e){
                                    Log.e(TAG+" changePass Exception",e.toString());
                                    ToastCustom.show(0,
                                            getContext(),
                                            getContext().getString(R.string.something_has_failed));
                                }
                            }

                        }


                        @Override
                        public void onFailure(Call<MessagePojo> call, Throwable t) {
                            statusButton(true);
                            Log.e(TAG+" changePass OnFailure",t.toString());
                            ToastCustom.show(0,
                                    getContext(),
                                    getContext().getString(R.string.something_has_failed));
                        }
                    });

        }else {
            ToastCustom.show(3,getActivity(),
                    getActivity().getString(R.string.without_internet));
        }
    }

    private void statusButton(boolean ban){
        buttonChange.setEnabled(ban);
        if (ban){
            buttonChange.setText(getText(R.string.change));
        }else {
            buttonChange.setText(getText(R.string.changin));
        }
    }
}

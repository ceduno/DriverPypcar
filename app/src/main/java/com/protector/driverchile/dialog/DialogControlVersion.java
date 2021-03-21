package com.protector.driverchile.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.MutableLiveData;

import com.protector.driverchile.R;
import com.protector.driverchile.homeMaster.HomeMasterEventView;
import com.protector.driverchile.utils.DialogCustom;
import com.protector.driverchile.utils.LogOut;

/**

 */
@SuppressLint("ValidFragment")
public class DialogControlVersion extends DialogFragment {
    private View v;
    private String message;
    private int status;
    private HomeMasterEventView homeMasterEventView;
    private TextView textViewMessage;
    private Button buttonOk,buttonNo;
    private ImageView buttonClose;


    public DialogControlVersion(String message, int status, HomeMasterEventView homeMasterEventView) {
        this.message= message;
        this.status= status;
        this.homeMasterEventView= homeMasterEventView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
           R.layout.dialog_play_store, null, false);

        binding.setLifecycleOwner(getActivity());
        binding.setVariable(BR.viewModel,this);
        v= binding.getRoot();
        builder.setView(v);

        addView();
        managerStatus();

        return builder.create();
    }

    private void addView() {
        buttonNo= v.findViewById(R.id.btn_no);
        buttonOk= v.findViewById(R.id.btn_ok);
        buttonClose= v.findViewById(R.id.btn_close);

        textViewMessage= v.findViewById(R.id.txv_message);
        textViewMessage.setText(message);

    }

    private void managerStatus(){

        if (status==1){  /**MANDATORY_RELEASE**/
            buttonClose.setVisibility(View.GONE);
        }else if (status==2){ /**OPTIONAL_RELEASE_AVAILABLE**/
            buttonNo.setVisibility(View.GONE);
        }else if(status==3){ /** INVALID_RELEASE**/
            buttonClose.setVisibility(View.GONE);
            buttonOk.setVisibility(View.GONE);

        }else if (status==4){/** UNSUPPORTED_RELEASE **/
            buttonClose.setVisibility(View.GONE);

        }else if (status == 5){ /** NO INTERNET OR FAILE**/
            buttonNo.setVisibility(View.GONE);
            buttonClose.setVisibility(View.GONE);
            buttonOk.setText(getString(R.string.retry));
        }

    }

    public void goTo(){
        if (status>=1 && status<5){

            try {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" +getActivity().getPackageName())));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(
                        new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://play.google.com/store/apps/details?id="
                                        +getActivity().getPackageName())));
            }

        }else {
            homeMasterEventView.validationVersionApp();
            dismiss();
        }

    }

    public void doCancel(){
        LogOut.Do(getActivity(),false);
    }

    public void closeDialog(){
        dismiss();
    }
}

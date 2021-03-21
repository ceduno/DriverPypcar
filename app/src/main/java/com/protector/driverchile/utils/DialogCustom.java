package com.protector.driverchile.utils;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.protector.driverchile.R;

/**

 */
@SuppressLint("ValidFragment")
public class DialogCustom extends DialogFragment implements View.OnClickListener {
    private View v;
    public String title,message,buttonTextOk,buttonTextNo;
    private TextView textViewTitle,textViewMessage;
    public Button buttonOk,buttonNo;
    private boolean towButton;

    public DialogCustom(String title,String message,String buttonTextOk,String buttonTextNo,boolean twoButton){
        this.title= title;
        this.message= message;
        this.buttonTextOk= buttonTextOk;
        this.buttonTextNo= buttonTextNo;
        this.towButton= twoButton;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createSimpleDialog();
    }

    public AlertDialog createSimpleDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        v = inflater.inflate(R.layout.dialog_custom, null);
        builder.setView(v);

        addView();
        return builder.create();
    }

    private void addView() {
        textViewTitle= v.findViewById(R.id.txv_title);
        textViewTitle.setText(title);
        textViewMessage= v.findViewById(R.id.txv_message);
        textViewMessage.setText(message);
        buttonOk= v.findViewById(R.id.btn_ok);
        buttonOk.setText(buttonTextOk);
        buttonNo= v.findViewById(R.id.btn_no);
        buttonNo.setText(buttonTextNo);

        if (!towButton){
            buttonNo.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {

    }
}

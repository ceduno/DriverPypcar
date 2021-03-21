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

@SuppressLint("ValidFragment")
public class DialogInfo  extends DialogFragment {

    private View v;
    private String title,message;
    private TextView textViewTitle,textViewMessage;
    private Button buttonOk;

    public DialogInfo(String title,String message){
        this.title= title;
        this.message= message;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createSimpleDialog();
    }

    public AlertDialog createSimpleDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        v = inflater.inflate(R.layout.dialog_info, null);
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
        buttonOk.setOnClickListener((View v) -> {
            dismiss();
        });
    }
}

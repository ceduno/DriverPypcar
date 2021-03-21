package com.protector.driverchile.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.protector.driverchile.R;
import com.protector.driverchile.utils.SharedPreferenceManager;


public class DialogTutorial extends DialogFragment {
    private View v;
    private Button buttonOk;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createSimpleDialog();
    }

    public AlertDialog createSimpleDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        v = inflater.inflate(R.layout.dialog_first_time, null);
        builder.setView(v);

        addView();
        return builder.create();
    }

    private void addView() {
        buttonOk= v.findViewById(R.id.btn_ok);
        buttonOk.setOnClickListener((View v) -> {
            SharedPreferenceManager.setTutorial(getContext(),false);
            dismiss();
        });
    }
}

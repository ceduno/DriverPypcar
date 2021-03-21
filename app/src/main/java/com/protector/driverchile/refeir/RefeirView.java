package com.protector.driverchile.refeir;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

import com.protector.driverchile.R;
import com.protector.driverchile.utils.DataModelJson.DriverPojo;
import com.protector.driverchile.utils.SharedPreferenceManager;
import com.protector.driverchile.utils.ToastCustom;

/***/
public class RefeirView extends Fragment {
    private View v;
    private DriverPojo driverPojo;
    private MutableLiveData<String>  refeirCode;
    private Button buttonRefeir;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        refeirCode= new MutableLiveData<>();

        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.fragment_refeir_friends, null, false);
        binding.setLifecycleOwner(getActivity());
        binding.setVariable(BR.viewModel,this);

        v= binding.getRoot();
        addView();
        setValues();
        return v;
    }

    private void addView(){
        buttonRefeir= v.findViewById(R.id.btn_refeir);
    }

    private void setValues() {
        driverPojo= SharedPreferenceManager.getUser(getContext());
        if (driverPojo.getCodeToRefer()==null){
            ToastCustom.show(2,getContext(),getActivity().getString(R.string.fail_refeir_code));
            buttonRefeir.setVisibility(View.GONE);
        }else {
            refeirCode.setValue(driverPojo.getCodeToRefer());
       }
    }

    public MutableLiveData<String> getRefeirCode() {
        return refeirCode;
    }

    public void setRefeirCode(MutableLiveData<String> refeirCode) {
        this.refeirCode = refeirCode;
    }

    public void refeir(){
        int emojiGif=0x1F381;
        int emojiDriver=0x1F695;
        int emojiRider=0x1F6B6;

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                getEmojiByUnicode(emojiGif)+getString(R.string.refeir_message)
                        +driverPojo.getCodeToRefer()+"\n\n"
                        +getEmojiByUnicode(emojiDriver)
                        +getString(R.string.refeir_message_url_driver)
                        +"https://play.google.com/store/apps/details?id="+getActivity().getPackageName()+"\n\n"
                        +getEmojiByUnicode(emojiRider)
                        +getString(R.string.refeir_message_url_rider)
                        +"https://play.google.com/store/apps/details?id=com.procarchile");

        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    private String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
}

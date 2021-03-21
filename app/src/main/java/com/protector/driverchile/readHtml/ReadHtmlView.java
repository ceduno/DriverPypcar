package com.protector.driverchile.readHtml;

import android.annotation.SuppressLint;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.protector.driverchile.R;


@SuppressLint("ValidFragment")
public class ReadHtmlView extends Fragment implements ReadHtmlEventView{
    private String TAG="ReadHtmlView";
    private FloatingActionButton buttonReload;
    private ConstraintLayout linearLayoutLoad;
    private TextView textView;
    private int action;
    private View v;
    private  ReadHtmlViewModel viewModel;

    public ReadHtmlView(int action) {
        this.action = action;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_text_html,container,false);

        v= binding.getRoot();
        addView();

        viewModel= new ReadHtmlViewModel(getActivity().getApplication(),
                this,action);
        binding.setVariable(BR.viewmodel,viewModel);
        binding.setLifecycleOwner(getActivity());

        return v;
    }

    @SuppressLint("RestrictedApi")
    private void addView() {
        buttonReload= v.findViewById(R.id.btn_reload);
        buttonReload.setVisibility(View.GONE);

        linearLayoutLoad= v.findViewById(R.id.vw_load);
        textView= v.findViewById(R.id.txv_html);
    }

    @Override
    public void showLoad(boolean ban) {
        try {
            if (ban){
                linearLayoutLoad.setVisibility(View.VISIBLE);
            }else {
                linearLayoutLoad.setVisibility(View.GONE);
            }
        }catch (Exception e){
            Log.e(TAG,"Vista CERRADA");
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void showReload(boolean ban) {
        try {
            Animation fadeIn = AnimationUtils.loadAnimation(getContext(),
                    R.anim.fade_in);

            Animation fadeOut = AnimationUtils.loadAnimation(getContext(),
                    R.anim.fade_out);

            if (ban){
                if (buttonReload.getVisibility()==View.GONE){
                    buttonReload.setVisibility(View.VISIBLE);
                    buttonReload.setAnimation(fadeIn);
                }
            }else {
                buttonReload.setAnimation(fadeOut);
                new Handler().postDelayed(
                        ()->{buttonReload.setVisibility(View.GONE);},800
                );
            }
        }catch (Exception e){
            Log.e(TAG,"Vista CERRADA");
        }
    }

    @Override
    public void showText(String text) {
        try {
            Animation animationUp = AnimationUtils.loadAnimation(getContext(),
                    R.anim.anim_uplafrombotton);

            textView.startAnimation(animationUp);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                textView.setText(Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT));
            } else {
                textView.setText(Html.fromHtml(text));
            }
        }catch (Exception e){
            Log.e(TAG,"Vista CERRADA");
        }
    }


}

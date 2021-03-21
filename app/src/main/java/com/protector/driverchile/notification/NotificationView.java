package com.protector.driverchile.notification;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.protector.driverchile.R;
import com.protector.driverchile.utils.DataModelJson.NotificationMap;
import com.protector.driverchile.utils.ToastCustom;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NotificationView extends Fragment implements NotificationEventView{
    private String TAG="NOTIFICATIONVIEW";
    private View v;
    private FloatingActionButton buttonReload;
    private ConstraintLayout linearLayoutLoad;
    private RecyclerView recyclerView;
    private NotificationViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_notification,container,false);

        v= binding.getRoot();
        addView();

        viewModel= new NotificationViewModel(getActivity().getApplication(),this,getActivity());
        binding.setVariable(BR.viewmodel,viewModel);
        binding.setLifecycleOwner(getActivity());

        return v;
    }

    @SuppressLint({"RestrictedApi", "WrongConstant"})
    private void addView() {
        buttonReload= v.findViewById(R.id.btn_reload);
        buttonReload.setVisibility(View.GONE);

        linearLayoutLoad= v.findViewById(R.id.vw_load);

        recyclerView= v.findViewById(R.id.recy_notification);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,false));
    }


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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void showList(List list) {
        try {
            if (list.isEmpty() || list.size()==0){
                ToastCustom.show(0,
                        getActivity(),
                        getActivity().getApplicationContext()
                                .getString(R.string.not_have_any_notification));
            }else {
                List<NotificationMap> items=list;
                items.sort((o1,o2)-> o1.getCreatedAt().compareTo(o2.getCreatedAt()));
                Collections.reverse(items);
                        AdapterNotifiaction adapter= new AdapterNotifiaction(items);
                recyclerView.setAdapter(adapter);

                LayoutAnimationController controller=AnimationUtils.loadLayoutAnimation
                        (recyclerView.getContext(),R.anim.animlt_up);
                recyclerView.setLayoutAnimation(controller);
                recyclerView.getAdapter().notifyDataSetChanged();
                recyclerView.scheduleLayoutAnimation();
            }
        }catch (Exception e){
            Log.e(TAG,"Vista CERRADA");
        }
    }

    @Override
    public void reloadList() {
        viewModel.reload();
    }
}

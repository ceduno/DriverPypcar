package com.protector.driverchile.travelHistory;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.protector.driverchile.R;
import com.protector.driverchile.dialog.DialogCancelTripHistory;
import com.protector.driverchile.homeMaster.HomeMasterEventView;
import com.protector.driverchile.utils.DataModelJson.TourModel;
import java.util.List;

public class TravelHistoryView extends Fragment implements TravelHistoryEventView{
    private String TAG="TRAVELHISTORY";
    private View v;
    private FloatingActionButton buttonReload;
    private ConstraintLayout linearLayoutLoad;
    private TravelHistoryViewModel viewModel;
    private RecyclerView recyclerView;
    private HomeMasterEventView homeMasterEventView;

    public TravelHistoryView(HomeMasterEventView homeMasterEventView) {
        this.homeMasterEventView= homeMasterEventView;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_travel_history,container,false);

        v= binding.getRoot();
        addView();

        viewModel= new TravelHistoryViewModel(getActivity().getApplication(),this,getActivity());
        binding.setVariable(BR.viewmodel,viewModel);
        binding.setLifecycleOwner(getActivity());
        return v;
    }

    @SuppressLint({"RestrictedApi", "WrongConstant"})
    private void addView() {
        buttonReload= v.findViewById(R.id.btn_reload);
        buttonReload.setVisibility(View.GONE);

        linearLayoutLoad= v.findViewById(R.id.vw_load);

        recyclerView= v.findViewById(R.id.recy_history);
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

    @SuppressLint("WrongConstant")
    @Override
    public void showList(List list) {
        try {
            List<TourModel> tourModelList= list;

            AdapterHistory adapter= new AdapterHistory(tourModelList,this);
            recyclerView.setAdapter(adapter);

            LayoutAnimationController controller=AnimationUtils.loadLayoutAnimation
                    (recyclerView.getContext(),R.anim.animlt_up);
            recyclerView.setLayoutAnimation(controller);
            recyclerView.getAdapter().notifyDataSetChanged();
            recyclerView.scheduleLayoutAnimation();

        }catch (Exception e){
            Log.e(TAG,"Vista CERRADA");
        }
    }

    @Override
    public void showDetailTravel(String tourId) {
       homeMasterEventView.showInvoiceTravel(tourId);
    }

    @Override
    public void showCancelTravel(String tourId) {
        DialogCancelTripHistory dialogLogout= new DialogCancelTripHistory(tourId,this);
        dialogLogout.show(getActivity().getSupportFragmentManager(),"DIALOG_CANCEL_TRIP");
    }

    @Override
    public void reloadList() {
        viewModel.reload();
    }

    @Override
    public void showListCondition(boolean ban) {
        if(ban){
            recyclerView.setVisibility(View.VISIBLE);
        }else{
            recyclerView.setVisibility(View.GONE);
        }
    }

}

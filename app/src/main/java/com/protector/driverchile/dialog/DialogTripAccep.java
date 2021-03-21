package com.protector.driverchile.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.DialogFragment;

import com.protector.driverchile.R;
import com.protector.driverchile.homeMaster.HomeMasterEventView;
import com.protector.driverchile.httpData.HttpConexion;
import com.protector.driverchile.httpData.Travel;
import com.protector.driverchile.utils.DataModelJson.CurrentTravelModel;
import com.protector.driverchile.utils.DataModelJson.DriverPojo;
import com.protector.driverchile.utils.DataModelJson.MessagePojo;
import com.protector.driverchile.utils.LogOut;
import com.protector.driverchile.utils.SharedPreferenceManager;
import com.protector.driverchile.utils.ToastCustom;
import com.protector.driverchile.utils.Validation;
import com.terry.view.swipeanimationbutton.SwipeAnimationButton;
import com.terry.view.swipeanimationbutton.SwipeAnimationListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 */
public class DialogTripAccep extends DialogFragment {
    private String TAG= "DIALOGACCEPTTRIP";
    private CurrentTravelModel currentTravelModel;
    private View v;
    private Button buttonAccept,buttonCancel;
    private SwipeAnimationButton swipebtnaccept;
    private TextView textViewNamePassanger,textViewDistance,textViewTime;//,textViewOrigin,textViewDestination;
    private static MediaPlayer mp;
    private HomeMasterEventView homeMasterEventView;
    private RatingBar ratingBar;
    private ProgressBar progressBarStatus;
    public DialogTripAccep(CurrentTravelModel currentTravelModel, HomeMasterEventView homeMasterEventView) {
        this.currentTravelModel= currentTravelModel;
        this.homeMasterEventView= homeMasterEventView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(getContext()), R.layout.dialog_accept_trip, null, false);

        binding.setLifecycleOwner(getActivity());
        binding.setVariable(BR.viewModel,this);
        v= binding.getRoot();
        builder.setView(v);

        addView();
        return builder.create();
    }

    private void addView() {
        buttonAccept= v.findViewById(R.id.btn_accept);
        buttonCancel= v.findViewById(R.id.btn_no);

        //textViewNamePassanger= v.findViewById(R.id.txv_name_passaenger);
       /* textViewOrigin= v.findViewById(R.id.txv_origin);
        textViewDestination= v.findViewById(R.id.txv_destination);*/
        textViewDistance= v.findViewById(R.id.txv_origin_accept);
        textViewTime= v.findViewById(R.id.txv_destination);
        swipebtnaccept= v.findViewById(R.id.swipe_btn_accept);
        progressBarStatus= v.findViewById(R.id.progressBarStatus);
        swipebtnaccept.setOnSwipeAnimationListener(new SwipeAnimationListener() {
            @Override
            public void onSwiped(boolean isRight) {
                /*ToastCustom.show(0,
                        getContext(),"EVENTO "+isRight);*/
                if (isRight) {
                    swipebtnaccept.setVisibility(View.GONE);
                    progressBarStatus.setVisibility(View.VISIBLE);
                    acceptTrip();

                } else {
                    swipebtnaccept.setVisibility(View.GONE);
                    progressBarStatus.setVisibility(View.VISIBLE);
                    cancelTrip();
                }
            }
        });
        ratingBar= v.findViewById(R.id.ratingbar);
        ratingBar.setIsIndicator(true);

        //textViewNamePassanger.setText(currentTravelModel.getFirstName());
        /*textViewOrigin.setText(currentTravelModel.getSourceAddress());
        textViewDestination.setText(currentTravelModel.getDestinationAddress());*/
        textViewDistance.setText(currentTravelModel.getDistanceToRiderPickup()+" Km");
        textViewTime.setText(Math.round(currentTravelModel.getDurationToRiderPickup())+" Min");
        if (currentTravelModel.getRating()>0){
            ratingBar.setRating(currentTravelModel.getRating());
        }

        //VIBRACION
        Vibrator v = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            v.vibrate(500);
        }
        //SONIDO
        stopRingtone();
        mp = MediaPlayer.create(getContext(), R.raw.beep);
        mp.start();
        mp.setLooping(true);



    }

    public void stopRingtone(){
        if(mp!=null && mp.isPlaying()){
            mp.stop();
            mp.reset();
            mp.release();
            mp = MediaPlayer.create(getContext(), R.raw.beep);
        }
    }
    public void acceptTrip(){
        stopRingtone();
        if (Validation.isNetDisponible(getContext())){
            statusAccept(false);

            DriverPojo driverPojo= SharedPreferenceManager.getUser(getContext());

            HttpConexion.getUri()
                    .create(Travel.class)
                    .acceptTravel(driverPojo.getApiSessionKey(),"es",currentTravelModel.getTourId())
                    .enqueue(new Callback<MessagePojo>() {
                        @Override
                        public void onResponse(Call<MessagePojo> call, Response<MessagePojo> response) {
                            statusAccept(true);
                            Log.d("RESPONSE TRAVEL","-----------------------------------------AQUI:   "+response.toString());
                            if (response.code()>=400 && response.code()<500){

                                if (response.code()==401){
                                    LogOut.Do(getActivity(),true);
                                }else {

                                    dismiss();
                                    ToastCustom.show(0,
                                            getContext(),getContext().getString(R.string.trip_assigned_another_driver));
                                    swipebtnaccept.setVisibility(View.VISIBLE);
                                    progressBarStatus.setVisibility(View.GONE);
                                }

                            }else {

                                try {

                                    currentTravelModel.setStatus("accepted");
                                    homeMasterEventView.managerStatusTravel(currentTravelModel);
                                    homeMasterEventView.focusRider();
                                    swipebtnaccept.setVisibility(View.VISIBLE);
                                    progressBarStatus.setVisibility(View.GONE);
                                    dismiss();

                                }catch (Exception e){
                                    Log.e(TAG+" cancelTravel Exception",e.toString());
                                    currentTravelModel.setStatus("accepted");
                                    homeMasterEventView.managerStatusTravel(currentTravelModel);
                                    homeMasterEventView.focusRider();
                                    swipebtnaccept.setVisibility(View.VISIBLE);
                                    progressBarStatus.setVisibility(View.GONE);
                                    dismiss();

                                   /* ToastCustom.show(0,
                                            getContext(),getContext().getString(R.string.something_has_failed));*/
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<MessagePojo> call, Throwable t) {
                            statusAccept(true);
                            Log.e(TAG+" cancelTravel OnFailure",t.toString());
                            swipebtnaccept.setVisibility(View.VISIBLE);
                            progressBarStatus.setVisibility(View.GONE);
                            ToastCustom.show(0,
                                    getContext(),getContext().getString(R.string.something_has_failed));
                        }
                    });

        }else {
            swipebtnaccept.setVisibility(View.VISIBLE);
            progressBarStatus.setVisibility(View.GONE);
            ToastCustom.show(3,getContext(),getContext().getString(R.string.without_internet));
        }
    }

    public void cancelTrip(){
        stopRingtone();
        if (Validation.isNetDisponible(getContext())){
            statusCancel(false);

            DriverPojo driverPojo= SharedPreferenceManager.getUser(getContext());

            HttpConexion.getUri()
                    .create(Travel.class)
                    .cancelTravel(driverPojo.getApiSessionKey(),"es",currentTravelModel.getTourId())
                    .enqueue(new Callback<MessagePojo>() {
                        @Override
                        public void onResponse(Call<MessagePojo> call, Response<MessagePojo> response) {
                            statusCancel(true);
                            if (response.code()>=400 && response.code()<500){
                                if (response.code()==401){
                                    LogOut.Do(getActivity(),true);
                                }else {
                                    dismiss();
                                    ToastCustom.show(0,
                                            getContext(),getContext().getString(R.string.expired_trip));
                                }
                            }else {
                                try {
                                    ToastCustom.show(1,
                                            getContext(),response.body().getMessages().get(0));

                                    dismiss();
                                }catch (Exception e){
                                    Log.e(TAG+" cancelTravel Exception",e.toString());
                                    ToastCustom.show(0,
                                            getContext(),getContext().getString(R.string.something_has_failed));
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<MessagePojo> call, Throwable t) {
                            statusCancel(true);
                            Log.e(TAG+" cancelTravel OnFailure",t.toString());
                            ToastCustom.show(0,
                                    getContext(),getContext().getString(R.string.something_has_failed));
                        }
                    });

        }else {
            ToastCustom.show(3,getContext(),getContext().getString(R.string.without_internet));
        }
    }

    private void statusAccept(boolean status){
        buttonAccept.setEnabled(status);
        if (status){
            buttonAccept.setText(R.string.to_accept);
            buttonCancel.setVisibility(View.VISIBLE);
        }else {
            buttonAccept.setText(R.string.accepting);
            buttonCancel.setVisibility(View.GONE);
        }
    }

    private void statusCancel(boolean status){
        buttonCancel.setEnabled(status);
        if (status){
            buttonCancel.setText(R.string.to_refuse);
            buttonAccept.setVisibility(View.VISIBLE);
        }else {
            buttonCancel.setText(R.string.rechanging);
            buttonAccept.setVisibility(View.GONE);
        }
    }
}

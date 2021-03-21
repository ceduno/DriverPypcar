package com.protector.driverchile.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
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

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*Carlos Duno**/

@SuppressLint("LongLogTag")
public class DialogTripWaiting extends DialogFragment {
    private String TAG= "DIALOGTRIPWAITING";
    private CurrentTravelModel currentTravelModel;
    private HomeMasterEventView homeMasterEventView;
    private Location locationDriver;
    private Button buttonAccept,buttonCancel;
    private TextView textViewNamePassanger,textViewSecuriteCode,textViewOrigin,textViewDestination;
    private RatingBar ratingBar;
    private View v;

    public DialogTripWaiting(CurrentTravelModel currentTravelModel, HomeMasterEventView homeMasterEventView, Location locationDriver) {
        this.currentTravelModel= currentTravelModel;
        this.homeMasterEventView= homeMasterEventView;
        this.locationDriver=locationDriver;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(getContext()), R.layout.dialog_arrived_trip, null, false);

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

        textViewNamePassanger= v.findViewById(R.id.txv_name_passaenger);
        //textViewSecuriteCode= v.findViewById(R.id.txv_segurite_code);
        textViewOrigin= v.findViewById(R.id.txv_origin_arrived);
        //textViewDestination= v.findViewById(R.id.txv_destination);
       /* ratingBar= v.findViewById(R.id.ratingbar);
        ratingBar.setIsIndicator(true);*/

        textViewNamePassanger.setText(currentTravelModel.getFirstName());
        textViewOrigin.setText(currentTravelModel.getSourceAddress());
        //textViewDestination.setText(currentTravelModel.getDestinationAddress());
        //textViewSecuriteCode.setText(currentTravelModel.getSecurityCode());

       /* if (currentTravelModel.getRating()>0){
            ratingBar.setRating(currentTravelModel.getRating());
        }*/

    }

    public void focusRider(){
        homeMasterEventView.focusRider();
        dismiss();
    }

    public void showRouteDestinationMaps(){
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr="+locationDriver.getLatitude()+","+locationDriver.getLongitude()+"&daddr="+currentTravelModel.getSourceLatitude()+","+currentTravelModel.getSourceLongitude()));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_LAUNCHER );
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        startActivity(intent);
    }
    public void focusRiderGoogleMaps(){

        if (currentTravelModel.getSourceLatitude() != null
                && currentTravelModel.getSourceLongitude() != null
                && currentTravelModel.getSourceAddress()!=null) {

            String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)",
                    Double.valueOf(currentTravelModel.getSourceLatitude()),
                    Double.valueOf(currentTravelModel.getSourceLongitude()),
                    currentTravelModel.getSourceAddress());
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            intent.setClassName("com.google.android.apps.maps",
                    "com.google.android.maps.MapsActivity");
            startActivity(intent);

        }
    }

    public void callPassanger(){
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",
                currentTravelModel.getPhoneCode()+currentTravelModel.getPhone(), null)));
        dismiss();
    }

    public void waitingPassanger(){
        if (Validation.isNetDisponible(getContext())){
            statusAccept(false);

            DriverPojo driverPojo= SharedPreferenceManager.getUser(getContext());

            HttpConexion.getUri()
                    .create(Travel.class)
                    .arrivedTravel(driverPojo.getApiSessionKey(),"es",currentTravelModel.getTourId())
                    .enqueue(new Callback<MessagePojo>() {
                        @Override
                        public void onResponse(Call<MessagePojo> call, Response<MessagePojo> response) {
                            statusAccept(true);
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
                                    currentTravelModel.setStatus("arrived & waiting");
                                    homeMasterEventView.managerStatusTravel(currentTravelModel);
                                    homeMasterEventView.showSecureCode();
                                    homeMasterEventView.focusRider();
                                    dismiss();
                                }catch (Exception e){
                                    Log.e(TAG+" waitingPassanger Exception",e.toString());
                                    ToastCustom.show(0,
                                            getContext(),getContext().getString(R.string.something_has_failed));
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<MessagePojo> call, Throwable t) {
                            statusAccept(true);
                            Log.e(TAG+" waitingPassanger OnFailure",t.toString());
                            ToastCustom.show(0,
                                    getContext(),getContext().getString(R.string.something_has_failed));
                        }
                    });

        }else {
            ToastCustom.show(3,getContext(),getContext().getString(R.string.without_internet));
        }
    }

    public void cancelTrip(){
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

                                    homeMasterEventView.clearInfoTravel();

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

    public void closeDialog(){
        dismiss();
    }

    private void statusAccept(boolean status){
        buttonAccept.setEnabled(status);
        if (status){
            buttonAccept.setText(R.string.arrived_wait);
            buttonCancel.setVisibility(View.VISIBLE);
        }else {
            buttonAccept.setText(R.string.accepting);
            buttonCancel.setVisibility(View.GONE);
        }
    }

    private void statusCancel(boolean status){
        buttonCancel.setEnabled(status);
        if (status){
            buttonCancel.setText(R.string.cancel_trip);
            buttonAccept.setVisibility(View.VISIBLE);
        }else {
            buttonCancel.setText(R.string.canceling);
            buttonAccept.setVisibility(View.GONE);
        }
    }
}

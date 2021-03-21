package com.protector.driverchile.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
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
import com.protector.driverchile.utils.DataModelJson.TourIdModel;
import com.protector.driverchile.utils.LogOut;
import com.protector.driverchile.utils.SharedPreferenceManager;
import com.protector.driverchile.utils.ToastCustom;
import com.protector.driverchile.utils.Validation;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*Carlos Duno**/

public class DialogTripEnd extends DialogFragment {
    private String TAG= "DIALOGTRIPWAITING";
    private CurrentTravelModel currentTravelModel;
    private HomeMasterEventView homeMasterEventView;
    private Button buttonAccept;
    private TextView textViewNamePassanger,textViewSecuriteCode,textViewOrigin,textViewDestination;
    private RatingBar ratingBar;
    private View v;

    public DialogTripEnd(CurrentTravelModel currentTravelModel, HomeMasterEventView homeMasterEventView) {
        this.currentTravelModel= currentTravelModel;
        this.homeMasterEventView= homeMasterEventView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(getContext()), R.layout.dialog_end_trip, null, false);

        binding.setLifecycleOwner(getActivity());
        binding.setVariable(BR.viewModel,this);
        v= binding.getRoot();
        builder.setView(v);

        addView();
        return builder.create();
    }

    private void addView() {
        buttonAccept= v.findViewById(R.id.btn_accept);

        textViewNamePassanger= v.findViewById(R.id.txv_name_passaenger);
        //textViewSecuriteCode= v.findViewById(R.id.txv_segurite_code);
       // textViewOrigin= v.findViewById(R.id.txv_origin_end);
        textViewDestination= v.findViewById(R.id.txv_destination);
       // ratingBar= v.findViewById(R.id.ratingbar);
       // ratingBar.setIsIndicator(true);

        textViewNamePassanger.setText(currentTravelModel.getFirstName());
        //textViewOrigin.setText(currentTravelModel.getSourceAddress());
        textViewDestination.setText(currentTravelModel.getDestinationAddress());
        //textViewSecuriteCode.setText(currentTravelModel.getSecurityCode());

        /*if (currentTravelModel.getRating()>0){
            ratingBar.setRating(currentTravelModel.getRating());
        }*/

    }

    public void focusDestination(){
        homeMasterEventView.focusDestination();
        dismiss();
    }

    public void showRouteDestinationMaps(){
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr="+currentTravelModel.getSourceLatitude()+","+currentTravelModel.getSourceLongitude()+"&daddr="+currentTravelModel.getDestinationLatitude()+","+currentTravelModel.getDestinationLongitude()));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_LAUNCHER );
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        startActivity(intent);
    }

    public void focusDestinationGoogleMaps(){

        if (currentTravelModel.getDestinationLatitude() != null
                && currentTravelModel.getDestinationLongitude() != null
                && currentTravelModel.getDestinationAddress()!=null) {

            String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)",
                    Double.valueOf(currentTravelModel.getDestinationLatitude()),
                    Double.valueOf(currentTravelModel.getDestinationLongitude()),
                    currentTravelModel.getDestinationAddress());
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            intent.setClassName("com.google.android.apps.maps",
                    "com.google.android.maps.MapsActivity");
            startActivity(intent);

        }

    }

    public void endTrip(){
        if (Validation.isNetDisponible(getContext())){
            statusAccept(false);

            DriverPojo driverPojo= SharedPreferenceManager.getUser(getContext());
            TourIdModel tourIdModel= new TourIdModel();
            tourIdModel.setTourId(currentTravelModel.getTourId());

            HttpConexion.getUri()
                    .create(Travel.class)
                    .endedTravel(driverPojo.getApiSessionKey(),"es",tourIdModel)
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
                                    ToastCustom.show(1,
                                            getContext(),getString(R.string.trip_completed));

                                    homeMasterEventView.clearInfoTravel();
                                    homeMasterEventView.showInvoiceTravel(currentTravelModel.getTourId());

                                    dismiss();
                                }catch (Exception e){
                                    Log.e(TAG+" endTrip Exception",e.toString());
                                    ToastCustom.show(0,
                                            getContext(),getContext().getString(R.string.something_has_failed));
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<MessagePojo> call, Throwable t) {
                            statusAccept(true);
                            Log.e(TAG+" endTrip OnFailure",t.toString());
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
            buttonAccept.setText(R.string.end_trip);
        }else {
            buttonAccept.setText(R.string.ending_trip);
        }
    }


}

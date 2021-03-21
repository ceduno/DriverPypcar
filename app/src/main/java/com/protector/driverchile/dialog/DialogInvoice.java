package com.protector.driverchile.dialog;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.protector.driverchile.ApplicationMaster;
import com.protector.driverchile.R;
import com.protector.driverchile.directionhelpers.FetchURL;
import com.protector.driverchile.directionhelpers.TaskLoadedCallback;
import com.protector.driverchile.homeMaster.HomeMasterEventView;
import com.protector.driverchile.utils.Convert;
import com.protector.driverchile.utils.DataModelJson.DriverPojo;
import com.protector.driverchile.utils.DataModelJson.TravelInfoModel;
import com.protector.driverchile.utils.DateUtils;
import com.protector.driverchile.utils.Pinter;
import com.protector.driverchile.utils.SharedPreferenceManager;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;


/**
 *
 */
public class DialogInvoice extends DialogFragment implements OnMapReadyCallback, TaskLoadedCallback {
    private TravelInfoModel travelInfoModel;
    private HomeMasterEventView homeMasterEventView;
    private View view;
    private ImageView buttonCancel;
    private ImageView imageProfile;
    private TextView textViewNamePassanger,textViewTravelDate,textViewOrigin,textViewDestination,
    textViewIdTravel,textViewDistance,textViewWaitTime,textViewDuration,textViewRate,textViewPercentage,
    textViewParticipation,textViewTags,textViewTotal, textViewDurationEstimated;
    private MapView mapView;
    private GoogleMap mMap;
    private DriverPojo driverPojo;
    private Button buttonRating;

    public DialogInvoice(TravelInfoModel travelInfoModel,HomeMasterEventView homeMasterEventView) {
        this.travelInfoModel= travelInfoModel;
        this.homeMasterEventView= homeMasterEventView;
        //Log.e("TRAVEL INFO",travelInfoModel.makeJson());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_travel_history_detail, null);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        driverPojo= SharedPreferenceManager.getUser(ApplicationMaster.getAppContext());
        addView(savedInstanceState);
        setValue();
        return view;
    }

    private void addView(Bundle bundle) {
        buttonCancel= view.findViewById(R.id.btn_cancel);
        buttonCancel.setOnClickListener((v)->{dismiss();});
        buttonRating= view.findViewById(R.id.btn_rating);
        if (travelInfoModel.getIsPassengerRated()){
            buttonRating.setVisibility(View.GONE);
        }

        buttonRating.setOnClickListener((view)->{
            homeMasterEventView.showDialogRating(travelInfoModel);
            //dismiss();
        });

        textViewNamePassanger= view.findViewById(R.id.txv_name_passaenger);
        textViewTravelDate= view.findViewById(R.id.txv_travel_date);
        textViewOrigin= view.findViewById(R.id.txv_origin);
        textViewDestination= view.findViewById(R.id.txv_destination);
        textViewIdTravel= view.findViewById(R.id.txv_id_travel);
        textViewDistance= view.findViewById(R.id.txv_distance);
        textViewWaitTime= view.findViewById(R.id.txv_wait_time);
        textViewDuration= view.findViewById(R.id.txv_duration);
        textViewRate= view.findViewById(R.id.txv_rate);
        textViewPercentage= view.findViewById(R.id.txv_percentage);
        textViewParticipation= view.findViewById(R.id.txv_participation);
        textViewTags= view.findViewById(R.id.txv_tags);
        textViewTotal= view.findViewById(R.id.txv_total_income);
        textViewDurationEstimated=view.findViewById(R.id.txt_duration_estim_value);

        mapView = view.findViewById(R.id.map);
        mapView.onCreate(bundle);
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap= googleMap;
        boolean success;
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            /*ToastCustom.show(0,
                    getContext(),
                    " THEME "+SharedPreferenceManager.getTheme(getActivity()));*/
            if(SharedPreferenceManager.getTheme(getActivity()) && DateUtils.validTimeDarkTheme()){
                success = mMap.setMapStyle(
                        MapStyleOptions.loadRawResourceStyle(
                                getActivity(), R.raw.stylenight_json));
            }else{
                success = mMap.setMapStyle(
                        MapStyleOptions.loadRawResourceStyle(
                                getActivity(), R.raw.styleday_json));
            }

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }
        addMarkes();
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        mapView.onLowMemory();
        super.onLowMemory();
    }

    private void setValue() {
        imageProfile= view.findViewById(R.id.img_profil_photo);
        if (travelInfoModel.getpPhotoUrl()!=null){
            Glide.with(getActivity())
                    .load(travelInfoModel.getpPhotoUrl())
                    .circleCrop()
                    .placeholder(R.drawable.ic_person)
                    .into(imageProfile);
        }

        if (travelInfoModel.getpFirstName()!=null){
            textViewNamePassanger.setText(travelInfoModel.getpFirstName());
        }

        if (travelInfoModel.getDateTime()!=null){
            textViewTravelDate.setText(Convert.epochToDate(travelInfoModel.getDateTime()));
        }

        if (travelInfoModel.getSourceAddress()!=null){
            textViewOrigin.setText(travelInfoModel.getSourceAddress());
        }

        if (travelInfoModel.getDestinationAddress()!=null){
            textViewDestination.setText(travelInfoModel.getDestinationAddress());
        }

        if (travelInfoModel.getUserTourId()!=null){
            textViewIdTravel.setText(travelInfoModel.getUserTourId());
        }

        if (travelInfoModel.getDistance()!=null){
            textViewDistance.setText(Convert.metersTokilometers(travelInfoModel.getDistance())+" Km");
        }

        if (travelInfoModel.getWaitingTime()!=null){
            textViewWaitTime.setText(Convert.millisecondToTime(travelInfoModel.getWaitingTime()));
        }

        if (travelInfoModel.getDuration()!=null){
            textViewDuration.setText(Convert.millisecondToTime(travelInfoModel.getDuration()));
        }//txt_duration_estim_value
        if (travelInfoModel.getEstimatedDuration()!=null){
            textViewDurationEstimated.setText(Convert.millisecondToTime(travelInfoModel.getEstimatedDuration()));
        }//txt_duration_estim_value

        if (travelInfoModel.getTotal()!=null){
            double totalfare= travelInfoModel.getTotal()*travelInfoModel.getExchangeRate();
            textViewRate.setText("$"+Math.round(totalfare));
        }

        if (travelInfoModel.getPercentage()!=null){
            textViewPercentage.setText(travelInfoModel.getPercentage()+" %");
        }

        if (travelInfoModel.getDriverAmount()!=null && travelInfoModel.getTollAmount()!=null){
            double participation = travelInfoModel.getDriverAmount() - travelInfoModel.getTollAmount();
            double participationperDolar= participation*travelInfoModel.getExchangeRate();
            textViewParticipation.setText("$"+Math.round(participationperDolar));
        }

        if (travelInfoModel.getTollAmount()!=null){
            double tag= travelInfoModel.getTollAmount()*travelInfoModel.getExchangeRate();
            textViewTags.setText("$"+Math.round(tag));
        }

        if (travelInfoModel.getDriverAmount()!=null){
            double total= travelInfoModel.getDriverAmount()*travelInfoModel.getExchangeRate();
            textViewTotal.setText("$"+Math.round(total));
        }
    }

    private void addMarkes(){

        if (travelInfoModel.getSourceLatitude()!=null && travelInfoModel.getSourceLongitude()!=null){

            Double lat= Double.valueOf(travelInfoModel.getSourceLatitude());
            Double longit= Double.valueOf(travelInfoModel.getSourceLongitude());
            LatLng locOrigin = new LatLng(lat,longit);

            MarkerOptions markerOrigin = new MarkerOptions();
            markerOrigin.position(locOrigin);

            if (driverPojo.getGender().toLowerCase().equals("female")){
                markerOrigin.icon(Pinter.bitmapDescriptorFromVector(ApplicationMaster.getAppContext(),
                        R.drawable.ic_car_pink));
            }else {
                markerOrigin.icon(Pinter.bitmapDescriptorFromVector(ApplicationMaster.getAppContext(),
                        R.drawable.ic_car_pro));
            }

            mMap.addMarker(markerOrigin);
        }

        if (travelInfoModel.getDestinationLatitude()!=null && travelInfoModel.getDestinationLongitude()!=null){

            Double lat= Double.valueOf(travelInfoModel.getDestinationLatitude());
            Double longit= Double.valueOf(travelInfoModel.getDestinationLongitude());
            LatLng locDestination = new LatLng(lat,longit);

            MarkerOptions markerDestination = new MarkerOptions();
            markerDestination.position(locDestination);

            if (travelInfoModel.getRiderGender().toLowerCase().equals("female")){
                markerDestination.icon(Pinter.bitmapDescriptorFromVector(ApplicationMaster.getAppContext(),
                        R.drawable.ic_marker_destino_icono_rosa));
            }else {
                markerDestination.icon(Pinter.bitmapDescriptorFromVector(ApplicationMaster.getAppContext(),
                        R.drawable.ic_marker_destino_icono_azul));
            }

            mMap.addMarker(markerDestination);

            mMap.moveCamera(CameraUpdateFactory.newLatLng(locDestination));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(13));
        }
        drawingroute();
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public void drawingroute(){
        Double lat= Double.valueOf(travelInfoModel.getSourceLatitude());
        Double longit= Double.valueOf(travelInfoModel.getSourceLongitude());
        Double latD= Double.valueOf(travelInfoModel.getDestinationLatitude());
        Double longitD= Double.valueOf(travelInfoModel.getDestinationLongitude());
        MarkerOptions place1 = new MarkerOptions().position(new LatLng(lat, longit)).title("pickup");
        MarkerOptions place2 = new MarkerOptions().position(new LatLng(latD, longitD)).title("Destination");
        callDirectionsUrl(place1,place2);

    }

    public void callDirectionsUrl(MarkerOptions place1, MarkerOptions place2) {
        //Toast.makeText(this, "CALL DIRECTIONS", Toast.LENGTH_LONG).show();
        new FetchURL(getContext()).execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");

    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service

        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" +"AIzaSyB074uHXpmpnXqfG-P5hN3VtzGZW_X5j-M";
        //Toast.makeText(this, url, Toast.LENGTH_LONG).show();
        return url;
    }
    @Override
    public void onTaskDone(Object... values) {

        //Toast.makeText(this, "onTask DONE", Toast.LENGTH_LONG).show();
        drawPolylinesMap(values);

    }
    private Polyline currentPolyline;
    public void drawPolylinesMap(Object... values){
        //Toast.makeText(getActivity(), "DRAW IN BOOKCAR", Toast.LENGTH_LONG).show();

        if (currentPolyline != null)
            currentPolyline.remove();

        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
        // new move

    }

}

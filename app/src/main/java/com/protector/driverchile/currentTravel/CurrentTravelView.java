package com.protector.driverchile.currentTravel;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.protector.driverchile.ApplicationMaster;
import com.protector.driverchile.R;
import com.protector.driverchile.homeMaster.HomeMasterEventView;
import com.protector.driverchile.httpData.HttpConexion;
import com.protector.driverchile.httpData.Travel;
import com.protector.driverchile.utils.DataModelJson.CurrentTravelModel;
import com.protector.driverchile.utils.DataModelJson.DriverPojo;
import com.protector.driverchile.utils.DataModelJson.MembershipSimpleModel;
import com.protector.driverchile.utils.DataModelJson.MessagePojo;
import com.protector.driverchile.utils.DataModelJson.TourIdModel;
import com.protector.driverchile.utils.DateUtils;
import com.protector.driverchile.utils.DialogInfo;
import com.protector.driverchile.utils.LogOut;
import com.protector.driverchile.utils.Pinter;
import com.protector.driverchile.utils.SharedPreferenceManager;
import com.protector.driverchile.utils.ToastCustom;
import com.protector.driverchile.utils.UserPermits;
import com.protector.driverchile.utils.Validation;
import com.stfalcon.swipeablebutton.SwipeableButton;
import com.terry.view.swipeanimationbutton.SwipeAnimationButton;
import com.terry.view.swipeanimationbutton.SwipeAnimationListener;

import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;


public class CurrentTravelView extends Fragment implements OnMapReadyCallback, LocationListener
        , View.OnClickListener {
    private String TAG = "CurrentTravelView";
    private View v;
    private GoogleMap mMap;
    private LocationManager locationManager;
    private ConstraintLayout viewNoGps, viewSecureCode,layoutRemainingTrip,layoutRemainingDays,layoutStatus,layoutActivar;
    private CardView cardViewStatusMembership;
    private RelativeLayout cardViewStatusTravel;
    private Button buttonGps;
    private ImageButton btnMembership,btnCall,btnMessage,btnGps;
    private Marker markerDriver, markerRider, markerDestination;
    private Location locationDriver;
    private MapView mapView;
    private HomeMasterEventView homeMasterEventView;
    private DriverPojo driverPojo;
    private CurrentTravelModel currentTravelModel;
    /** View info travel **/
    private ImageView imageViewPhoto,ivTypetrip;
    private TextView textViewStatusTravel, textViewNamePassanger, textViewOrigin,txvDireccionTour;
    private Button buttonMoreDetail;
    /** View Secure Code **/
    private Button buttonCloseSecureCode,btnCancel,btnValidar;
    private TextView textViewSecureCode;
    private TextView txvTitleMembership,txvRemainingTrips,txvRemainingDays,txvStatusCircleColorA,
            txvStatusCircleColorS,txvStatusText,txvRight,txvLeft,txvStatusMembership,txvClose;
    private LatLngBounds bounds;
    private LatLngBounds.Builder builder;
    private List<Marker> markersList = new ArrayList<Marker>();
    private ConstraintLayout vwStatusAction,layout_option_swipe,layout_option_code;
    private SwipeAnimationButton swipe_btn_start;
    private LinearLayout viewStartingTrip;
    private SwipeAnimationButton swipe_btn_ended;
    private LinearLayout viewEndedTrip;
    private SwipeAnimationButton swipeBtnArrived,swipeBtnStart,swipeBtnEnd,swipeTest;
    private LinearLayout viewArrivedTrip;
    private MembershipSimpleModel membershipModel;
    private EditText edtxSecurityCode;
    private ConstraintLayout linearLayoutLoad;
    private ProgressBar progressBarStatus;
    private SwipeableButton swipeableButton;
    private Polyline currentPolyline;
    private SwipeButton btnSwipeEnded;

    public CurrentTravelView(HomeMasterEventView homeMasterEventView, DriverPojo driverPojo) {
        this.homeMasterEventView = homeMasterEventView;
        this.driverPojo = driverPojo;
    }

    @SuppressLint("InvalidWakeLockTag")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.fragment_current_travel, null, false);

        binding.setLifecycleOwner(getActivity());
        binding.setVariable(BR.viewModel, this);
        v = binding.getRoot();

        addView(savedInstanceState);

        //homeMasterEventView.membershipRequest(); activar obligado PILAAAAAA
        return v;
    }

    private void addView(Bundle bundle) {
        mapView = v.findViewById(R.id.map);
        mapView.onCreate(bundle);
        mapView.getMapAsync(this);

        viewNoGps = v.findViewById(R.id.view_no_gps);
        viewNoGps.setVisibility(View.GONE);

        viewSecureCode = v.findViewById(R.id.view_scure_code);
        viewSecureCode.setVisibility(View.GONE);

        cardViewStatusTravel = v.findViewById(R.id.view_status_travel);

        //cardViewStatusTravel.setVisibility(View.GONE);

        txvStatusMembership = v.findViewById(R.id.txv_status_membership);
        cardViewStatusMembership = v.findViewById(R.id.view_status_membership);
        cardViewStatusMembership.setVisibility(View.GONE);
        txvStatusMembership.setOnClickListener(this);

        buttonGps = v.findViewById(R.id.btn_gps);
        buttonGps.setOnClickListener(this);

        /** View info travel **/
        imageViewPhoto = v.findViewById(R.id.img_profil_photo);
        textViewStatusTravel = v.findViewById(R.id.txv_status_travel);
        textViewNamePassanger = v.findViewById(R.id.txv_name_passaenger);
        txvDireccionTour = v.findViewById(R.id.txv_direccion_tour);
        ivTypetrip = v.findViewById(R.id.iv_typetrip);

        buttonMoreDetail = v.findViewById(R.id.btn_more_detail);

        /** View Secure Code **/
        buttonCloseSecureCode = v.findViewById(R.id.btn_closed_code);
        buttonCloseSecureCode.setOnClickListener(this);
        textViewSecureCode = v.findViewById(R.id.txv_segurite_code);

        /** View Membership**/
        txvTitleMembership = v.findViewById(R.id.txv_title_membership);
        txvRemainingTrips = v.findViewById(R.id.txv_remaining_trips);
        txvRemainingDays = v.findViewById(R.id.txv_remaining_days);
        txvStatusCircleColorS = v.findViewById(R.id.txv_status_circle_colorS);
        txvStatusCircleColorA = v.findViewById(R.id.txv_status_circle_colorA);
        txvStatusText = v.findViewById(R.id.txv_status_text);
        layoutRemainingTrip = v.findViewById(R.id.layoutRemainingTrip);
        layoutRemainingDays = v.findViewById(R.id.layoutRemainingDays);
        layoutStatus= v.findViewById(R.id.layoutStatus);
        layoutActivar= v.findViewById(R.id.layoutActivar);
        txvClose= v.findViewById(R.id.txv_close);
        txvClose.setOnClickListener(this);

        btnMembership= v.findViewById(R.id.btn_membership);

        layoutStatus.setVisibility(View.GONE);
        builder= new LatLngBounds.Builder();
        //Components for status travel
        vwStatusAction = v.findViewById(R.id.vw_status_action);
        vwStatusAction.setOnClickListener(this);
        layout_option_swipe = v.findViewById(R.id.layout_option_swipe);
        //Components for check code security
        layout_option_code = v.findViewById(R.id.layout_option_code);
        edtxSecurityCode = v.findViewById(R.id.edtx_security_code);
        edtxSecurityCode.setOnClickListener(this);
        btnCancel = v.findViewById(R.id.btn_cancel);
        btnValidar = v.findViewById(R.id.btn_validar);
        btnCancel.setOnClickListener(this);
        btnValidar.setOnClickListener(this);

        btnCall = v.findViewById(R.id.btn_call);
        btnCall.setOnClickListener(this);
        btnMessage = v.findViewById(R.id.btn_message);
        btnMessage.setOnClickListener(this);
        btnGps = v.findViewById(R.id.btn_maps);
        btnGps.setOnClickListener(this);
        //edtxSecurityCode.setOnClickListener(this);

        viewArrivedTrip= v.findViewById(R.id.viewArrivedTrip);

        swipeBtnArrived= v.findViewById(R.id.swipe_btn_control);
        swipeBtnStart= v.findViewById(R.id.swipe_btn_control_start);
        //swipeTest= v.findViewById(R.id.swipe_test);
       // swipeBtnEnd= v.findViewById(R.id.swipe_btn_control_end);
        //modificado
        btnSwipeEnded= v.findViewById(R.id.btnSwipeEnded);
        //swipeableButton= v.findViewById(R.id.swipeEnded);
        // swipeBtnStart.moveToLeft();

        txvRight= v.findViewById(R.id.txv_right);
        txvLeft= v.findViewById(R.id.txv_left);

        linearLayoutLoad= v.findViewById(R.id.vw_load);
        progressBarStatus= v.findViewById(R.id.progressBarStatus);

        setListenerSwipe();
        //swipeBtnStart= new SwipeAnimationButton(getActivity());
    }

    public void hideKeyBoard() {
        InputMethodManager imm = (InputMethodManager)getActivity().getApplication().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edtxSecurityCode.getWindowToken(), 0);
    }

    public void setListenerSwipe(){

        btnSwipeEnded.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                if(active){
                    setOptionTextSwipe();
                    //swipeBtnEnd.setVisibility(View.GONE);
                    btnSwipeEnded.setVisibility(View.GONE);
                    progressBarStatus.setVisibility(View.VISIBLE);
                    endTrip();
                }


            }
        });

        swipeBtnArrived.setOnSwipeAnimationListener(new SwipeAnimationListener() {
            @Override
            public void onSwiped(boolean isRight) {
                if (isRight) {
                    if (currentTravelModel.getStatus()!=null){
                        if (currentTravelModel.getStatus().equals("accepted")){
                            //arrive
                            setOptionTextSwipe();
                            swipeBtnArrived.setVisibility(View.GONE);
                            progressBarStatus.setVisibility(View.VISIBLE);
                            waitingPassanger();
                        }
                    }
                } else {
                    if (currentTravelModel.getStatus().equals("accepted")){
                        //arrive
                        swipeBtnArrived.setVisibility(View.GONE);
                        progressBarStatus.setVisibility(View.VISIBLE);
                        cancelWaitingTrip();
                    }
                }
            }
        });

        swipeBtnStart.setOnSwipeAnimationListener(new SwipeAnimationListener() {
            @Override
            public void onSwiped(boolean isRight) {
                if (isRight) {
                    if (currentTravelModel.getStatus()!=null){
                        if (currentTravelModel.getStatus().equals("arrived & waiting")){
                            //accepted
                            setOptionTextSwipe();
                            swipeBtnStart.setVisibility(View.GONE);
                            progressBarStatus.setVisibility(View.VISIBLE);
                            showSegureCode(true);
                            homeMasterEventView.setStatusBar();
                            //startTrip();
                        }
                    }
                } else {
                    if (currentTravelModel.getStatus().equals("arrived & waiting")){
                        //accepted
                        swipeBtnStart.setVisibility(View.GONE);
                        progressBarStatus.setVisibility(View.VISIBLE);
                        cancelTrip();
                    }
                }
            }
        });

       /* swipeBtnEnd.setOnSwipeAnimationListener(new SwipeAnimationListener() {
            @Override
            public void onSwiped(boolean isRight) {
                if (isRight) {
                    setOptionTextSwipe();
                    swipeBtnEnd.setVisibility(View.GONE);
                    progressBarStatus.setVisibility(View.VISIBLE);

                            endTrip();

                }
            }
        });*/
    }

    public void setOptionTextSwipe(){

        if (currentTravelModel.getStatus()!=null){

            if (currentTravelModel.getStatus().equals("accepted")) {
                //arrive
                swipeBtnArrived.setVisibility(View.VISIBLE);
                swipeBtnStart.setVisibility(View.GONE);
                //swipeBtnEnd.setVisibility(View.GONE);
                btnSwipeEnded.setVisibility(View.GONE);
                ivTypetrip.setImageResource(R.drawable.ic_dir_origin);
                txvDireccionTour.setText(currentTravelModel.getSourceAddress());
                txvLeft.setText("Cancel");
                txvRight.setText("Llegue");
            }else if (currentTravelModel.getStatus().equals("arrived & waiting")){
                //accepted
                swipeBtnArrived.setVisibility(View.GONE);
                swipeBtnStart.setVisibility(View.VISIBLE);
                //swipeBtnEnd.setVisibility(View.GONE);
                btnSwipeEnded.setVisibility(View.GONE);
                ivTypetrip.setImageResource(R.drawable.ic_dir_dest);
                txvDireccionTour.setText(currentTravelModel.getDestinationAddress());
                txvLeft.setText("Cancelar");
                txvRight.setText("Iniciar Viaje");
            }else if (currentTravelModel.getStatus().equals("started")){
                //final
                swipeBtnArrived.setVisibility(View.GONE);
                swipeBtnStart.setVisibility(View.GONE);
                //swipeBtnEnd.setVisibility(View.VISIBLE);
                btnSwipeEnded.setVisibility(View.VISIBLE);
                ivTypetrip.setImageResource(R.drawable.ic_dir_dest);
                txvDireccionTour.setText(currentTravelModel.getDestinationAddress());
                txvLeft.setVisibility(View.GONE);
                txvLeft.setText("");
                txvRight.setText("Finalizar");
            }
        }
    }
    public void showLoad(boolean ban) {
        try {
            if (ban){
                linearLayoutLoad.setVisibility(View.GONE);
            }else {
                linearLayoutLoad.setVisibility(View.GONE);
            }
        }catch (Exception e){
            Log.e(TAG,"Vista CERRADA");
        }
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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void changeThemeMap(){
      if(mMap!=null){
          boolean success;
          if(SharedPreferenceManager.getTheme(getActivity()) && DateUtils.validTimeDarkTheme()){
              success = mMap.setMapStyle(
                      MapStyleOptions.loadRawResourceStyle(
                              getActivity(), R.raw.stylenight_json));
          }else{
              success = mMap.setMapStyle(
                      MapStyleOptions.loadRawResourceStyle(
                              getActivity(), R.raw.styleday_json));
          }
      }
}


    //region MAPA
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

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


            locationStar();


       /* LatLng loc = new LatLng(locationDriver.getLatitude(), locationDriver.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));*/

        View locationButton=((View) mapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
        RelativeLayout.LayoutParams rlp= (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP,0);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
        rlp.setMargins(0,180,180,0);
    }

    private void addMarketDriver(){

        if (markerDriver != null) {
            markerDriver.remove();
            markersList.remove(0);
        }

        LatLng loc = new LatLng(locationDriver.getLatitude(), locationDriver.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.anchor(0.5f,0.5f);
        //markerOptions.zIndex(2.0f);
        //markerOptions.flat(true);
        markerOptions.position(loc);
        int height = 60;
        int width = 60;
        Bitmap bpro = BitmapFactory.decodeResource(getResources(),R.drawable.ic_car_pro);
        Bitmap smallMarkerpro = Bitmap.createScaledBitmap(bpro, width, height, false);
        Bitmap bpink = BitmapFactory.decodeResource(getResources(),R.drawable.ic_car_pink);
        Bitmap smallMarkerpink = Bitmap.createScaledBitmap(bpink, width, height, false);

        BitmapDescriptor smallMarkerIconPro = BitmapDescriptorFactory.fromBitmap(smallMarkerpro);
        BitmapDescriptor smallMarkerIconPink = BitmapDescriptorFactory.fromBitmap(smallMarkerpink);

        if(driverPojo.getCarModel().getCarTpeId().equals("2")){
            markerOptions.icon(smallMarkerIconPro);
           /* markerOptions.icon(Pinter.bitmapDescriptorFromVector(ApplicationMaster.getAppContext(),
                    R.drawable.ic_car_pro));*/

        }else if (driverPojo.getCarModel().getCarTpeId().equals("3")){

            markerOptions.icon(Pinter.bitmapDescriptorFromVector(ApplicationMaster.getAppContext(),
                    R.drawable.ic_pin_pro_extra));

        }else if (driverPojo.getCarModel().getCarTpeId().equals("4")){

            markerOptions.icon(Pinter.bitmapDescriptorFromVector(ApplicationMaster.getAppContext(),
                    R.drawable.ic_pin_pro_delux));

        }else if (driverPojo.getCarModel().getCarTpeId().equals("7")){

            markerOptions.icon(Pinter.bitmapDescriptorFromVector(ApplicationMaster.getAppContext(),
                    R.drawable.ic_pin_vehicle_7));

        }else if (driverPojo.getCarModel().getCarTpeId().equals("8")){
            markerOptions.icon(smallMarkerIconPink);
           /* markerOptions.icon(Pinter.bitmapDescriptorFromVector(ApplicationMaster.getAppContext(),
                    R.drawable.ic_car_pink));*/

        }else if (driverPojo.getCarModel().getCarTpeId().equals("9")){

            markerOptions.icon(Pinter.bitmapDescriptorFromVector(ApplicationMaster.getAppContext(),
                    R.drawable.ic_pin_pink_extra));

        }


       // builder.include(markerOptions.getPosition());
        markerDriver = mMap.addMarker(markerOptions);
       // markersList.
        markersList.add(0,markerDriver);
        for (Marker m : markersList) {
            builder.include(m.getPosition());
        }
        /*LatLngBounds bounds = builder.build();
        CameraUpdate cu;
        cu = CameraUpdateFactory.newLatLngBounds(bounds, 50);
        mMap.animateCamera(cu);*/
        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));
    }

    private void addMarketRider(){
        removeMarketRider();

        double latitude= Double.valueOf(currentTravelModel.getSourceLatitude());
        double longitude= Double.valueOf(currentTravelModel.getSourceLongitude());

        LatLng loc = new LatLng(latitude,longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(loc);
        markerOptions.title(currentTravelModel.getSourceAddress());

        if (currentTravelModel.getRiderGender().toLowerCase().equals("female")){
            markerOptions.icon(Pinter.bitmapDescriptorFromVector(ApplicationMaster.getAppContext(),
                    R.drawable.ic_women));
        }else {
            markerOptions.icon(Pinter.bitmapDescriptorFromVector(ApplicationMaster.getAppContext(),
                    R.drawable.ic_men));
        }
        //builder.include(markerOptions.getPosition());
        markerRider= mMap.addMarker(markerOptions);
        markersList.add(1,markerRider);
        for (Marker m : markersList) {
            builder.include(m.getPosition());
        }
        LatLngBounds bounds = builder.build();
        CameraUpdate cu;
        cu = CameraUpdateFactory.newLatLngBounds(bounds, 50);
        mMap.animateCamera(cu);
    }

    private void removeMarketRider(){
        if (markerRider != null) {
            markerRider.remove();
        }
    }


    private void removePolyline(){
        if (currentPolyline != null) {
            currentPolyline.remove();
        }
    }

    public void focusMarketDriver(){
        LatLng loc = new LatLng(locationDriver.getLatitude(), locationDriver.getLongitude());
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
       //markersList.add(markerDriver);
        for (Marker m : markersList) {
            builder.include(m.getPosition());
        }
        LatLngBounds bounds = builder.build();
        CameraUpdate cu;
        cu = CameraUpdateFactory.newLatLngBounds(bounds, 50);
        mMap.animateCamera(cu);
    }

    public Location getLocationDriver(){
        return locationDriver;
    }

    public void focusMarketRider(){
        double latitude= Double.valueOf(currentTravelModel.getSourceLatitude());
        double longitude= Double.valueOf(currentTravelModel.getSourceLongitude());

        LatLng loc = new LatLng(latitude,longitude);
        for (Marker m : markersList) {
            builder.include(m.getPosition());
        }
        LatLngBounds bounds = builder.build();
        CameraUpdate cu;
        cu = CameraUpdateFactory.newLatLngBounds(bounds, 50);
        mMap.animateCamera(cu);
    }

    private void addMarketDestination(){
        removeMarketDestination();

        double latitude= Double.valueOf(currentTravelModel.getDestinationLatitude());
        double longitude= Double.valueOf(currentTravelModel.getDestinationLongitude());

        LatLng loc = new LatLng(latitude,longitude);


        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(loc);
        markerOptions.title(currentTravelModel.getDestinationAddress());

        if (currentTravelModel.getRiderGender().toLowerCase().equals("female")){
            markerOptions.icon(Pinter.bitmapDescriptorFromVector(ApplicationMaster.getAppContext(),
                    R.drawable.ic_marker_destino_icono_rosa));
        }else {
            markerOptions.icon(Pinter.bitmapDescriptorFromVector(ApplicationMaster.getAppContext(),
                    R.drawable.ic_marker_destino_icono_azul));
        }
        builder.include(markerOptions.getPosition());
        markerDestination= mMap.addMarker(markerOptions);
        markersList.add(2,markerDestination);
        for (Marker m : markersList) {
            builder.include(m.getPosition());
        }
        LatLngBounds bounds = builder.build();
        CameraUpdate cu;
        cu = CameraUpdateFactory.newLatLngBounds(bounds, 50);
        mMap.animateCamera(cu);
    }

    private void removeMarketDestination(){
        if (markerDestination != null) {
            markerDestination.remove();
        }
    }

    public void focusMarketDestination(){
        double latitude= Double.valueOf(currentTravelModel.getDestinationLatitude());
        double longitude= Double.valueOf(currentTravelModel.getDestinationLongitude());

        LatLng loc = new LatLng(latitude,longitude);
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 0);
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        mMap.animateCamera(cu);
    }

    //endregion

    //region GPS
    @SuppressLint("MissingPermission")
    private void locationStar() {
        UserPermits userPermits = new UserPermits(getActivity(), false);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(this.getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            userPermits.permitGps();

            return;
        } else {

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    2000, 10, this);

            if (locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER) != null){
                locationDriver = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                addMarketDriver();
                homeMasterEventView.starService();
            }else if (locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)!= null){
                locationDriver = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                addMarketDriver();
                homeMasterEventView.starService();
            }

            mMap.setMyLocationEnabled(true);
            mMap.setOnMyLocationChangeListener(myLocationChangeListener);
        }
    }

    private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            //Log.e("MAPA","--------------------onMyLocationChange");
            try {
                if (location != null) {
                    locationDriver= location;
                    addMarketDriver();
                }
            }catch (Exception e){
                Log.e(TAG+"Error localizacion mapa",e.toString());
            }
        }
    };

    @Override
    public void onLocationChanged(Location location) {
        Log.e("MAPA","--------------------onLocationChanged");
        locationDriver = location;
        homeMasterEventView.sendLocationTracking();

        //addMarketDriver();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.e("onLocationChanged", provider);
    }

    @Override
    public void onProviderEnabled(String provider) {
        showViewNoGps(false);
        homeMasterEventView.starService();
    }

    @Override
    public void onProviderDisabled(String provider) {
        showViewNoGps(true);
    }

    private void showViewNoGps(boolean ban){

        if (ban){
            if (viewNoGps.getVisibility()!=View.VISIBLE){
                viewNoGps.setVisibility(View.VISIBLE);
                cardViewStatusMembership.setVisibility(View.GONE);
                if (currentTravelModel!=null){
                    if (!currentTravelModel.getStatus().equals("assigned")){
                        showInfoTravel(currentTravelModel,false);
                    }
                }
            }
        }else {
            if (viewNoGps.getVisibility()!=View.GONE){
                viewNoGps.setVisibility(View.GONE);

                if (currentTravelModel!=null){
                    if (!currentTravelModel.getStatus().equals("assigned")){
                        if (currentTravelModel.getPhotoUrl()!=null){
                            Glide.with(getContext())
                                    .load(currentTravelModel.getPhotoUrl())
                                    .circleCrop()
                                    .placeholder(R.drawable.ic_person)
                                    .into(imageViewPhoto);
                        }
                        showInfoTravel(currentTravelModel,true);
                    }
                }
            }
        }

    }

    public void turnGPSOn(){
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
    }
    //endregion
    //REGION MEMBERSHIP INFO
    public void showMembershipInfo(MembershipSimpleModel membershipModel, boolean show){
        new Handler().postDelayed(new Runnable(){
            public void run(){
                // Cuando pasen los 10 segundos Ocultamos el view membresia
                cardViewStatusMembership.setVisibility(View.GONE);
            };
        }, 10000);
         this.membershipModel=membershipModel;
        if (show){
            /*if (cardViewStatusMembership.getVisibility()==View.GONE){
                cardViewStatusMembership.setVisibility(View.VISIBLE);
            }*/


                /*Animation animationUp = AnimationUtils.loadAnimation(ApplicationMaster.getAppContext(),
                        R.anim.anim_uplafrombotton);

                cardViewStatusMembership.startAnimation(animationUp);*/
                if(membershipModel!=null){
                    //showActivateMembership(false);
                    txvTitleMembership.setText("Membresía "+membershipModel.getMembershipTypeName());
                    txvRemainingTrips.setText(membershipModel.getRemainingTrips());
                    txvRemainingDays.setText(membershipModel.getRemainingDays());
                    txvStatusMembership.setVisibility(View.VISIBLE);
                    if(membershipModel.getOperationStatus().equals("activated")) {
                        txvStatusMembership.setBackgroundResource(R.drawable.dw_circle_green);
                        txvStatusMembership.setText("A");
                       /* txvStatusCircleColorA.setVisibility(View.VISIBLE);
                        txvStatusCircleColorS.setVisibility(View.GONE);*/
                        //txvStatusText.setText(membershipModel.getOperationStatus());
                    }else{
                        txvStatusMembership.setBackgroundResource(R.drawable.dw_circle_red);
                        txvStatusMembership.setText("S");
                       /* txvStatusCircleColorA.setVisibility(View.GONE);
                        txvStatusCircleColorS.setVisibility(View.VISIBLE);*/
                        //txvStatusText.setText(membershipModel.getOperationStatus());
                    }


                    //Integer.parseInt(membershipModel.getRemainingDays())
                    // txvStatusCircleColor.setBackgroundColor(R.drawable.dw_circle_red);
                }

        }else {
            txvStatusMembership.setVisibility(View.GONE);
            txvTitleMembership.setText("SIN MEMBRESIA");
            //showActivateMembership(true);
        }


    }

    public void showActivateMembership(boolean band){
        if(band){

            layoutActivar.setVisibility(View.VISIBLE);
            layoutStatus.setVisibility(View.GONE);
            layoutRemainingDays.setVisibility(View.GONE);
            layoutRemainingTrip.setVisibility(View.GONE);
        }
        else{
            layoutActivar.setVisibility(View.GONE);
            layoutStatus.setVisibility(View.VISIBLE);
            layoutRemainingDays.setVisibility(View.VISIBLE);
            layoutRemainingTrip.setVisibility(View.VISIBLE);
        }

        cardViewStatusMembership.setVisibility(View.VISIBLE);
    }

    //END REGION MEMBERSHIP INFO
    //region TRAVEL
    private MarkerOptions place1, place2;
    public void showInfoTravel(CurrentTravelModel currentTravel,boolean show){


        currentTravelModel= currentTravel;

        if (show){
            if (cardViewStatusTravel.getVisibility()==View.GONE){
                cardViewStatusTravel.setVisibility(View.VISIBLE);
                cardViewStatusMembership.setVisibility(View.GONE);
               /* Animation animationUp = AnimationUtils.loadAnimation(ApplicationMaster.getAppContext(),
                        R.anim.anim_uplafrombotton);

                cardViewStatusTravel.startAnimation(animationUp);*/
            }
        }else {
            cardViewStatusTravel.setVisibility(View.GONE);
        }

        if (currentTravelModel!=null){

            if (currentTravelModel.getFirstName()!=null &&  currentTravelModel.getLastName()!=null){
                textViewNamePassanger.setText(currentTravelModel.getFirstName());
                txvDireccionTour.setText(currentTravelModel.getSourceAddress());
            }

            if (currentTravelModel.getStatus()!=null){
                setOptionTextSwipe();
                if (currentTravelModel.getStatus().equals("accepted")){
                    textViewStatusTravel.setText(ApplicationMaster.getAppContext().getString(R.string.looking_passenger));

                    //textViewOrigin.setVisibility(View.VISIBLE);
                   // textViewOrigin.setText(currentTravelModel.getSourceAddress())
                    addMarketRider();
                    LatLng latlng=markerDriver.getPosition();
                    LatLng latlngRider=markerRider.getPosition();
                    place1 = new MarkerOptions().position(new LatLng(latlng.latitude, latlng.longitude)).title("pickup");
                    place2 = new MarkerOptions().position(new LatLng(latlngRider.latitude, latlngRider.longitude)).title("Destination");
                    homeMasterEventView.callDirectionsUrl(place1,place2);
                    LatLngBounds bounds = builder.build();
                    mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds,70));

                }else if (currentTravelModel.getStatus().equals("arrived & waiting")){

                    textViewStatusTravel.setText(getString(R.string.wait_pasanger));

                }else if (currentTravelModel.getStatus().equals("started")){
                    textViewStatusTravel.setText(getString(R.string.taking_pasanger));
                    //removeMarketRider();
                    addMarketDestination();
                    LatLng latlng=markerRider.getPosition();
                    removePolyline();
                    LatLng latlngRider=markerDestination.getPosition();
                    place1 = new MarkerOptions().position(new LatLng(latlng.latitude, latlng.longitude)).title("pickup");
                    place2 = new MarkerOptions().position(new LatLng(latlngRider.latitude, latlngRider.longitude)).title("Destination");
                    homeMasterEventView.callDirectionsUrl(place1,place2);
                    LatLngBounds bounds = builder.build();
                    mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds,70));
                }
            }
            Log.d("URL PHOTO RIDER","---------------- "+currentTravelModel.getPhotoUrl());
            if (currentTravelModel.getPhotoUrl()!=null){
                Glide.with(getContext())
                        .load(currentTravelModel.getPhotoUrl())
                        .circleCrop()
                        .placeholder(R.drawable.ic_person)
                        .into(imageViewPhoto);
            }
            vwStatusAction.setOnClickListener(this);
            buttonMoreDetail.setOnClickListener(this);
        }

    }

    private boolean isShowCodeSecurity=false;

    public void showSegureCode(boolean ban){

        if (ban){
            textViewSecureCode.setText(currentTravelModel.getSecurityCode());
            layout_option_swipe.setVisibility(View.GONE);
            layout_option_code.setVisibility(View.VISIBLE);
            isShowCodeSecurity=true;
            /*if (viewSecureCode.getVisibility()!=View.VISIBLE){
                //viewSecureCode.setVisibility(View.VISIBLE);

                if (currentTravelModel!=null){
                    if (!currentTravelModel.getStatus().equals("assigned")){
                        showInfoTravel(currentTravelModel,false);
                    }
                }
            }*/
        }else {

            if (viewSecureCode.getVisibility()!=View.GONE){
                //viewSecureCode.setVisibility(View.GONE);
                layout_option_code.setVisibility(View.GONE);
                isShowCodeSecurity=false;
                if (currentTravelModel!=null){
                    if (!currentTravelModel.getStatus().equals("assigned")){
                        showInfoTravel(currentTravelModel,true);
                    }
                }
            }
        }
    }

    public void showDialgoInfo(String title,String info) {
        DialogInfo dialogInfo= new DialogInfo(title,info);
        dialogInfo.show(getFragmentManager(),"DIALOG_INFO");
        dialogInfo.setCancelable(true);
    }
    public void clearInfoTravel(){
        currentTravelModel= null;
        builder= new LatLngBounds.Builder();
        //currentPolyline.remove();
        mMap.clear();
        locationStar();
        cardViewStatusTravel.setVisibility(View.GONE);

    }
    //endregion
boolean isShowSwipe=false;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_gps:
                turnGPSOn();
                break;
            case R.id.btn_more_detail:
                homeMasterEventView.managerStatusTravelDialog(currentTravelModel);
                break;
            case R.id.vw_status_action:
                if(!isShowCodeSecurity){
                   minimizedSwipeOption(isShowSwipe);
                }

                //homeMasterEventView.managerStatusTravelDialog(currentTravelModel);
                break;
            case R.id.btn_closed_code:
                showSegureCode(false);
                break;
            case R.id.txv_status_membership:
                //showSegureCode(false);
                showMembershipInfoNew();
                break;
            case R.id.txv_close:
                cardViewStatusMembership.setVisibility(View.GONE);
                //showSegureCode(false);
                break;
            case R.id.btn_membership:
                homeMasterEventView.membershipRequest();
                break;
            case R.id.btn_validar:
                hideKeyBoard();
                homeMasterEventView.setStatusBarMaps();
                validSecurityCode();
                //homeMasterEventView.membershipRequest();
                break;
            case R.id.btn_cancel:
                hideKeyBoard();
                /*showSegureCode(false);
                isShowCodeSecurity=false;
                layout_option_code.setVisibility(View.GONE);
                layout_option_swipe.setVisibility(View.VISIBLE);
                setOptionTextSwipe();*/
                //cancelTrip();
                //homeMasterEventView.membershipRequest();
                break;
            case R.id.btn_call:
                callPassanger();
                break;
            case R.id.btn_maps:
                showRouteDestinationMaps();
                break;
            case R.id.btn_message:
                messageToPassenger();
                break;
            case R.id.edtx_security_code:

                break;

        }
    }

    public void showMembershipInfoNew(){
       /* Animation animationUp = AnimationUtils.loadAnimation(ApplicationMaster.getAppContext(),
                R.anim.anim_uplafrombotton);
*/
        //cardViewStatusMembership.startAnimation(animationUp);
        cardViewStatusMembership.setVisibility(View.VISIBLE);
    }


    public void minimizedSwipeOption(boolean show){
        if(show){
            /*Animation animationUp = AnimationUtils.loadAnimation(ApplicationMaster.getAppContext(),
                    R.anim.anim_uplafrombotton);

            layout_option_swipe.startAnimation(animationUp);*/
            layout_option_swipe.setVisibility(View.VISIBLE);
            isShowSwipe=false;
        }else{

            layout_option_swipe.setVisibility(View.GONE);
            isShowSwipe=true;
        }

    }



//*******************************************************************************************************
    // PROCEDURE FOR AWAITING TRIP VIEW


    public void waitingPassanger(){
        showLoad(true);
        if (Validation.isNetDisponible(getContext())){
            //statusAccept(false);

            DriverPojo driverPojo= SharedPreferenceManager.getUser(getContext());

            HttpConexion.getUri()
                    .create(Travel.class)
                    .arrivedTravel(driverPojo.getApiSessionKey(),"es",currentTravelModel.getTourId())
                    .enqueue(new Callback<MessagePojo>() {
                        @Override
                        public void onResponse(Call<MessagePojo> call, Response<MessagePojo> response) {
                            //statusAccept(true);
                            showLoad(false);
                            if (response.code()>=400 && response.code()<500){
                                if (response.code()==401){
                                    LogOut.Do(getActivity(),true);
                                }else {
                                    //dismiss();
                                    ToastCustom.show(0,
                                            getContext(),getContext().getString(R.string.expired_trip));
                                }
                            }else {
                                try {
                                    currentTravelModel.setStatus("arrived & waiting");
                                    homeMasterEventView.managerStatusTravel(currentTravelModel);
                                    //homeMasterEventView.showSecureCode();
                                    homeMasterEventView.focusRider();
                                    //dismiss();
                                    swipeBtnArrived.setVisibility(View.GONE);
                                    progressBarStatus.setVisibility(View.GONE);
                                    //swipeBtnArrived= new SwipeAnimationButton(getContext());
                                }catch (Exception e){
                                    //Log.e(TAG+" waitingPassanger Exception",e.toString());
                                    ToastCustom.show(0,
                                            getContext(),getContext().getString(R.string.something_has_failed));
                                    //swipeBtnArrived= new SwipeAnimationButton(getContext());
                                    setListenerSwipe();
                                    swipeBtnArrived.setVisibility(View.VISIBLE);
                                    progressBarStatus.setVisibility(View.GONE);
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<MessagePojo> call, Throwable t) {
                            //statusAccept(true);
                            //Log.e(TAG+" waitingPassanger OnFailure",t.toString());
                            showLoad(false);
                            ToastCustom.show(0,
                                    getContext(),getContext().getString(R.string.something_has_failed));
                            //swipeBtnArrived= new SwipeAnimationButton(getContext());
                            setListenerSwipe();
                            swipeBtnArrived.setVisibility(View.VISIBLE);
                            progressBarStatus.setVisibility(View.GONE);
                        }
                    });

        }else {
            showLoad(false);
            ToastCustom.show(3,getContext(),getContext().getString(R.string.without_internet));
        }
    }

    public void cancelWaitingTrip(){
        showLoad(true);
        if (Validation.isNetDisponible(getContext())){
            //statusCancel(false);

            DriverPojo driverPojo= SharedPreferenceManager.getUser(getContext());

            HttpConexion.getUri()
                    .create(Travel.class)
                    .cancelTravel(driverPojo.getApiSessionKey(),"es",currentTravelModel.getTourId())
                    .enqueue(new Callback<MessagePojo>() {
                        @Override
                        public void onResponse(Call<MessagePojo> call, Response<MessagePojo> response) {
                            //statusCancel(true);
                            showLoad(false);
                            if (response.code()>=400 && response.code()<500){
                                if (response.code()==401){
                                    LogOut.Do(getActivity(),true);
                                }else {
                                    //dismiss();
                                    ToastCustom.show(0,
                                            getContext(),response.body().toString());
                                    //swipeBtnArrived= new SwipeAnimationButton(getContext());
                                    setListenerSwipe();
                                    swipeBtnArrived.setVisibility(View.VISIBLE);
                                    progressBarStatus.setVisibility(View.GONE);
                                }
                            }else {
                                try {
                                    /*ToastCustom.show(1,
                                            getContext(),response.body().toString());*/

                                    homeMasterEventView.clearInfoTravel();
                                    //swipeBtnArrived.setVisibility(View.VISIBLE);
                                    progressBarStatus.setVisibility(View.GONE);
                                    //dismiss();
                                }catch (Exception e){
                                    Log.e(TAG+" cancelTravel Exception",e.toString());
                                    ToastCustom.show(0,
                                            getContext(),"cancelTravel Exception "+e.toString());
                                    //swipeBtnArrived= new SwipeAnimationButton(getContext());
                                    setListenerSwipe();
                                    swipeBtnArrived.setVisibility(View.VISIBLE);
                                    progressBarStatus.setVisibility(View.GONE);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<MessagePojo> call, Throwable t) {
                            //statusCancel(true);
                            showLoad(false);
                            Log.e(TAG+" cancelTravel OnFailure",t.toString());
                            ToastCustom.show(0,
                                    getContext()," cancelTravel OnFailure  "+t.toString());
                            //swipeBtnArrived= new SwipeAnimationButton(getContext());
                            setListenerSwipe();
                            swipeBtnArrived.setVisibility(View.VISIBLE);
                            progressBarStatus.setVisibility(View.GONE);
                        }
                    });

        }else {
            showLoad(false);
            //swipeBtnArrived= new SwipeAnimationButton(getContext());
            setListenerSwipe();
            ToastCustom.show(3,getContext(),getContext().getString(R.string.without_internet));
        }
    }

    // END PROCEDURE AWAITING TRIP VIEW

    //************************************************************************************************
    //************************************************************************************************

    //PROCEDURE FOR STARTING TRIP


    public void startTrip(){
        showLoad(true);
        if (Validation.isNetDisponible(getContext())){
            //statusAccept(false);

            DriverPojo driverPojo= SharedPreferenceManager.getUser(getContext());

            HttpConexion.getUri()
                    .create(Travel.class)
                    .startedTravel(driverPojo.getApiSessionKey(),"es",currentTravelModel.getTourId())
                    .enqueue(new Callback<MessagePojo>() {

                        @Override
                        public void onResponse(Call<MessagePojo> call, Response<MessagePojo> response) {
                            //statusAccept(true);
                            showLoad(false);
                            if (response.code()>=400 && response.code()<500){
                                if (response.code()==401){
                                    LogOut.Do(getActivity(),true);
                                }else {
                                    //dismiss();
                                    ToastCustom.show(0,
                                            getContext(),getContext().getString(R.string.expired_trip));
                                }
                            }else {
                                try {
                                    currentTravelModel.setStatus("started");
                                    homeMasterEventView.managerStatusTravel(currentTravelModel);
                                    homeMasterEventView.focusDestination();
                                    //swipeBtnStart.setVisibility(View.VISIBLE);
                                    progressBarStatus.setVisibility(View.GONE);
                                    //swipeBtnStart= new SwipeAnimationButton(getContext());
                                    //dismiss();
                                }catch (Exception e){
                                    Log.e(TAG+" startTrip Exception",e.toString());
                                    //swipeBtnStart.setVisibility(View.VISIBLE);
                                    //swipeBtnStart= new SwipeAnimationButton(getContext());
                                    setListenerSwipe();
                                    progressBarStatus.setVisibility(View.GONE);
                                    /*ToastCustom.show(0,
                                            getContext(),getContext().getString(R.string.something_has_failed));*/
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<MessagePojo> call, Throwable t) {
                            //statusAccept(true);
                            showLoad(false);
                            Log.e(TAG+" startTrip OnFailure",t.toString());
                            setListenerSwipe();
                            swipeBtnStart.setVisibility(View.VISIBLE);
                            progressBarStatus.setVisibility(View.GONE);
                            ToastCustom.show(0,
                                    getContext(),getContext().getString(R.string.something_has_failed));
                        }
                    });

        }else {
            showLoad(false);
            swipeBtnStart= new SwipeAnimationButton(getContext());
            setListenerSwipe();
            swipeBtnStart.setVisibility(View.VISIBLE);
            progressBarStatus.setVisibility(View.GONE);
            ToastCustom.show(3,getContext(),getContext().getString(R.string.without_internet));
        }
    }

    public void cancelTrip(){
        showLoad(true);
        if (Validation.isNetDisponible(getContext())){
            //statusCancel(false);

            DriverPojo driverPojo= SharedPreferenceManager.getUser(getContext());

            HttpConexion.getUri()
                    .create(Travel.class)
                    .cancelTravel(driverPojo.getApiSessionKey(),"es",currentTravelModel.getTourId())
                    .enqueue(new Callback<MessagePojo>() {
                        @Override
                        public void onResponse(Call<MessagePojo> call, Response<MessagePojo> response) {
                            //statusCancel(true);
                            showLoad(false);
                            if (response.code()>=400 && response.code()<500){
                                if (response.code()==401){
                                    LogOut.Do(getActivity(),true);
                                }else {
                                    //dismiss();
                                    ToastCustom.show(0,
                                            getContext(),getContext().getString(R.string.expired_trip));
                                }
                            }else {
                                if (response.code()==200) {
                                    try {
                                        /*ToastCustom.show(1,
                                                getContext(),response.body().toString());*/

                                        homeMasterEventView.clearInfoTravel();
                                        //swipeBtnStart.setVisibility(View.VISIBLE);
                                        progressBarStatus.setVisibility(View.GONE);
                                        //dismiss();
                                    }catch (Exception e){
                                        Log.e(TAG+" cancelTravel Exception",e.toString());
                                        //swipeBtnStart= new SwipeAnimationButton(getContext());
                                        setListenerSwipe();
                                        swipeBtnStart.setVisibility(View.VISIBLE);
                                        progressBarStatus.setVisibility(View.GONE);
                                        ToastCustom.show(0,
                                                getContext(),getContext().getString(R.string.something_has_failed)+" "+e.toString());
                                    }
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<MessagePojo> call, Throwable t) {
                            //statusCancel(true);
                            showLoad(false);
                            Log.e(TAG+" cancelTravel OnFailure",t.toString());

                            //swipeBtnStart= new SwipeAnimationButton(getContext());
                            setListenerSwipe();
                            swipeBtnStart.setVisibility(View.VISIBLE);
                            progressBarStatus.setVisibility(View.GONE);
                            /*ToastCustom.show(0,
                                    getContext(),getContext().getString(R.string.something_has_failed));*/
                        }
                    });

        }else {
            showLoad(false);
            //swipeBtnStart= new SwipeAnimationButton(getContext());
            setListenerSwipe();
            swipeBtnStart.setVisibility(View.VISIBLE);
            progressBarStatus.setVisibility(View.GONE);
            ToastCustom.show(3,getContext(),getContext().getString(R.string.without_internet));
        }
    }
    //END PROCEDURE FOR STARTING TRIP

//*****************************************************************************************
    //*******************************************************************************
    //PROCEDURE ENDED TRIP




    public void endTrip(){
        showLoad(true);
    if (Validation.isNetDisponible(getContext())){
        //statusAccept(false);

        DriverPojo driverPojo= SharedPreferenceManager.getUser(getContext());
        TourIdModel tourIdModel= new TourIdModel();
        tourIdModel.setTourId(currentTravelModel.getTourId());

        HttpConexion.getUri()
                .create(Travel.class)
                .endedTravel(driverPojo.getApiSessionKey(),"es",tourIdModel)
                .enqueue(new Callback<MessagePojo>() {
                    @Override
                    public void onResponse(Call<MessagePojo> call, Response<MessagePojo> response) {
                        //statusAccept(true);
                        showLoad(false);
                        if (response.code()>=400 && response.code()<500){
                            if (response.code()==401){
                                LogOut.Do(getActivity(),true);
                            }else {
                                //dismiss();
                                ToastCustom.show(0,
                                        getContext(),getContext().getString(R.string.expired_trip));
                            }
                        }else {
                            try {
                                ToastCustom.show(1,
                                        getContext(),getString(R.string.trip_completed));

                                currentTravelModel.setStatus("ended");
                                homeMasterEventView.showOnduty();
                                homeMasterEventView.managerStatusTravel(currentTravelModel);
                                homeMasterEventView.showInvoiceTravel(currentTravelModel.getTourId());
                                homeMasterEventView.clearInfoTravel();
                                //swipeBtnStart.setVisibility(View.VISIBLE);
                                progressBarStatus.setVisibility(View.GONE);

                                removePolyline();
                                clearInfoTravel();
                                onResume();
                                //dismiss();
                            }catch (Exception e){
                                Log.e(TAG+" endTrip Exception",e.toString());
                                setListenerSwipe();
                                //swipeBtnEnd.setVisibility(View.VISIBLE);
                                btnSwipeEnded.setVisibility(View.VISIBLE);
                                progressBarStatus.setVisibility(View.GONE);
                                /*ToastCustom.show(0,
                                        getContext(),getContext().getString(R.string.something_has_failed));*/
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MessagePojo> call, Throwable t) {
                        //statusAccept(true);
                        showLoad(false);
                        Log.e(TAG+" endTrip OnFailure",t.toString());
                        //swipeBtnEnd.setVisibility(View.VISIBLE);
                        setListenerSwipe();
                        btnSwipeEnded.setVisibility(View.VISIBLE);
                        progressBarStatus.setVisibility(View.GONE);
                        /*ToastCustom.show(0,
                                getContext(),getContext().getString(R.string.something_has_failed));*/
                    }
                });

    }else {
        showLoad(false);
        ToastCustom.show(3,getContext(),getContext().getString(R.string.without_internet));
        //swipeBtnEnd.setVisibility(View.VISIBLE);
        btnSwipeEnded.setVisibility(View.VISIBLE);
        progressBarStatus.setVisibility(View.GONE);
    }
}

    //ENDED TRIP

    public void validSecurityCode(){

        if(edtxSecurityCode.getText()!=null && !edtxSecurityCode.getText().equals("")){

            if(edtxSecurityCode.getText().toString().toLowerCase().equals(currentTravelModel.getSecurityCode().toLowerCase())){
                layout_option_code.setVisibility(View.GONE);
                layout_option_swipe.setVisibility(View.VISIBLE);
                isShowCodeSecurity=false;
                startTrip();
            }else {
                ToastCustom.show(3,getContext(),"Código inválido");
            }
        }else{
            //edtxSecurityCode.setTextColor();
            ToastCustom.show(3,getContext(),"Ingrese un código");
        }
    }



    public void drawPolylinesMap(Object... values){
        //Toast.makeText(getActivity(), "DRAW IN BOOKCAR", Toast.LENGTH_LONG).show();

        if (currentPolyline != null)
            currentPolyline.remove();

        if(currentTravelModel!=null && !currentTravelModel.getStatus().equals("ended")){
            currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);

            for(int i=0; i<currentPolyline.getPoints().size();i++){
                builder.include(currentPolyline.getPoints().get(i));
            }
        }


        // new move

    }

    public void callPassanger(){
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",
                currentTravelModel.getPhoneCode()+currentTravelModel.getPhone(), null)));
    }

    public void showRouteDestinationMaps(){
        //modificar para mostrar segun status
        String lat="",lng="";
        if(currentTravelModel.getStatus().equals("accepted")){
            lat=currentTravelModel.getSourceLatitude();
            lng=currentTravelModel.getSourceLongitude();
        }else if(currentTravelModel.getStatus().equals("arrived & waiting")){
            lat=currentTravelModel.getSourceLatitude();
            lng=currentTravelModel.getSourceLongitude();
        }else if(currentTravelModel.getStatus().equals("started")){
            lat=currentTravelModel.getDestinationLatitude();
            lng=currentTravelModel.getDestinationLongitude();
        }
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr="+locationDriver.getLatitude()+","+locationDriver.getLongitude()+"&daddr="+lat+","+lng));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_LAUNCHER );
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        startActivity(intent);
    }

    public void messageToPassenger(){
        if(!currentTravelModel.getPhone().equals("") && currentTravelModel.getPhone()!=null){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", ""+currentTravelModel.getPhoneCode()+""+currentTravelModel.getPhone(), null)));

        }

    }
}


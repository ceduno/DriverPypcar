package com.protector.driverchile.contactUs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
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
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.protector.driverchile.R;
import com.protector.driverchile.utils.DataModelJson.ContactModel;
import com.protector.driverchile.utils.DateUtils;
import com.protector.driverchile.utils.SharedPreferenceManager;
import com.protector.driverchile.utils.ToastCustom;

public class ContactUsView extends Fragment implements ContactUsEventView, OnMapReadyCallback {
    private String TAG="ContactUsView ";
    private FloatingActionButton buttonReload;
    private ConstraintLayout linearLayoutLoad;
    private ConstraintLayout constraintLayoutContact;
    private View v;
    private  ContactUsViewModel viewModel;
    private MapView mapView;
    private GoogleMap mMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_contact_us,container,false);

        v= binding.getRoot();
        addView(savedInstanceState);

        viewModel= new ContactUsViewModel(getActivity().getApplication(), this);
        binding.setVariable(BR.viewmodel,viewModel);
        binding.setLifecycleOwner(getActivity());

        return v;
    }

    @SuppressLint("RestrictedApi")
    private void addView(Bundle bundle) {
        buttonReload= v.findViewById(R.id.btn_reload);
        buttonReload.setVisibility(View.GONE);

        linearLayoutLoad= v.findViewById(R.id.vw_load);

        constraintLayoutContact= v.findViewById(R.id.content_contact);
        constraintLayoutContact.setVisibility(View.GONE);

        mapView = v.findViewById(R.id.map);
        mapView.onCreate(bundle);
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        boolean success;
        try {
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
                if (buttonReload.getVisibility()== View.GONE){
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
    public void showContect(ContactModel contactModel) {
        try {

            constraintLayoutContact.setVisibility(View.VISIBLE);
            LayoutAnimationController controller= AnimationUtils.loadLayoutAnimation
                    (constraintLayoutContact.getContext(),R.anim.animlt_up);
            constraintLayoutContact.setLayoutAnimation(controller);
            constraintLayoutContact.scheduleLayoutAnimation();

            Double lat= Double.valueOf(contactModel.getLatitude());
            Double longit= Double.valueOf(contactModel.getLongitude());
            addMarker(lat,longit);

            CardView cardViewEmail= v.findViewById(R.id.cardView_email);
            TextView textViewEmail= v.findViewById(R.id.txv_email);
            CardView cardViewPhone1= v.findViewById(R.id.cardView_phone1);
            TextView textViewPhone1= v.findViewById(R.id.txv_phone1);
            CardView cardViewPhone2= v.findViewById(R.id.cardView_phone2);
            TextView textViewPhone2= v.findViewById(R.id.txv_phone2);
            CardView cardViewFax= v.findViewById(R.id.cardView_fax);
            TextView textViewFax= v.findViewById(R.id.txv_fax);

            if (contactModel.getEmail()!=null){
                if (!contactModel.getEmail().isEmpty()){
                    textViewEmail.setText(contactModel.getEmail());
                    cardViewEmail.setOnClickListener((view)->{sendEmail(contactModel.getEmail());});
                }else {
                    cardViewEmail.setVisibility(View.GONE);
                }
            }else{
                cardViewEmail.setVisibility(View.GONE);
            }

            if (contactModel.getPhoneNumberOne()!=null){
                if (!contactModel.getFax().isEmpty()){
                    textViewPhone1.setText(contactModel.getPhoneNumberOne());
                    cardViewPhone1.setOnClickListener((view)->{call(contactModel.getPhoneNumberOne());});
                }else {
                    cardViewPhone1.setVisibility(View.GONE);
                }
            }else{
                cardViewPhone1.setVisibility(View.GONE);
            }


            if (contactModel.getPhoneNumberTwo()!=null){
                if (!contactModel.getFax().isEmpty()){
                    textViewPhone2.setText(contactModel.getPhoneNumberTwo());
                    cardViewPhone2.setOnClickListener((view)->{call(contactModel.getPhoneNumberTwo());});
                }else {
                    cardViewPhone2.setVisibility(View.GONE);
                }
            }else{
                cardViewPhone2.setVisibility(View.GONE);
            }

            if (contactModel.getFax()!=null){
                if (!contactModel.getFax().isEmpty()){
                    textViewFax.setText(contactModel.getFax());
                }else {
                    cardViewFax.setVisibility(View.GONE);
                }
            }else{
                cardViewFax.setVisibility(View.GONE);
            }

        }catch (Exception e){
            Log.e(TAG,"vista CERRADA");
        }

    }


    private void addMarker(Double lat,Double longit){
        LatLng loc = new LatLng(lat,longit);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14));

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(loc);

        mMap.addMarker(markerOptions);
    }

    private void sendEmail(String email){

        String[] TO = {email};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.support_to)
                +" "+ SharedPreferenceManager.getUser(getContext()).getFullName());
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            ToastCustom.show(2,getContext(),getString(R.string.not_have_email_service));
        }

    }

    private void call(String phone){
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",
                phone, null)));
    }

}

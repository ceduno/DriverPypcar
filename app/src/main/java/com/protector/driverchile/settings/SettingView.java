package com.protector.driverchile.settings;

import android.annotation.SuppressLint;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.protector.driverchile.ApplicationMaster;
import com.protector.driverchile.R;
import com.protector.driverchile.homeMaster.HomeMasterEventView;
import com.protector.driverchile.httpData.HttpConexion;
import com.protector.driverchile.httpData.User;
import com.protector.driverchile.utils.DataModelJson.ChangePass;
import com.protector.driverchile.utils.DataModelJson.DriverPojo;
import com.protector.driverchile.utils.DataModelJson.MessagePojo;
import com.protector.driverchile.utils.LogOut;
import com.protector.driverchile.utils.ProgresDialogCustom;
import com.protector.driverchile.utils.SharedPreferenceManager;
import com.protector.driverchile.utils.ToastCustom;
import com.protector.driverchile.utils.Validation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("ValidFragment")
public class SettingView extends Fragment {
    private String TAG= "SettingView";
    private HomeMasterEventView homeMasterEventView;
    private LinearLayout viewProcar,viewDivi;
    private CheckBox aSwitchNotification,aSwitchProcar,aSwitchScreen,aSwitchTheme;
    private DriverPojo driverPojo;
    private View v;

    public SettingView(HomeMasterEventView homeMasterEventView) {
        this.homeMasterEventView= homeMasterEventView;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DriverPojo driverPojo = SharedPreferenceManager.getUser(getContext());
        if (driverPojo.getGender().toLowerCase().equals("female")){
            getContext().setTheme(R.style.AppThemePink);
        }else {
            getContext().setTheme(R.style.AppThemePro);
        }
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_settings, null, false);
        binding.setLifecycleOwner(getActivity());
        binding.setVariable(BR.viewSetting,this);
        v= binding.getRoot();
        addView();
        return v;
    }

    private void addView() {
        driverPojo= SharedPreferenceManager.getUser(ApplicationMaster.getAppContext());

        if (driverPojo.getGender()!=null){
            if (driverPojo.getGender().toLowerCase().equals("male")){
                viewProcar= v.findViewById(R.id.linearLayout_travel_procar);
                viewProcar.setVisibility(View.GONE);
                viewDivi= v.findViewById(R.id.viw_01);
                viewDivi.setVisibility(View.GONE);
            }
        }else {
            ToastCustom.show(3,getActivity(),
                    getActivity().getString(R.string.fail_view)
                            +getActivity().getString(R.string.settings));
        }

        aSwitchNotification= v.findViewById(R.id.swt_notification);
        statusSwitchNotification(driverPojo.getNotificationStatus());

        aSwitchProcar= v.findViewById(R.id.swt_travel_procar);
        if (driverPojo.getAcceptProcar()!=null){
            statusSwitchProcar(driverPojo.getAcceptProcar());
        }

        aSwitchTheme= v.findViewById(R.id.swt_theme);
        aSwitchTheme.setOnCheckedChangeListener(new ThemeModeChkListener());
        statusSwitchTheme(SharedPreferenceManager.getTheme(getContext()));
        /*if (driverPojo.getDarktheme()!=null){
            statusSwitchTheme(driverPojo.getDarktheme());
        }*/

        aSwitchScreen= v.findViewById(R.id.swt_screen_on);
        aSwitchScreen.setChecked(SharedPreferenceManager.getScreen(getContext()));

    }

    private class ThemeModeChkListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            if (isChecked) {
                //notifStatus = true;
                SharedPreferenceManager.setTheme(getActivity(),true);

            } else {
                // notifStatus = false;
                SharedPreferenceManager.setTheme(getActivity(),false);
            }
        }

    }
    public void goToAccount(){ homeMasterEventView.goToProfile(); }

    public void goToChangePass(){
        homeMasterEventView.showDialogChangePass();
    }

    public void goToDisconnet(){
        homeMasterEventView.showDialogLogout();
    }

    public void goToTechnicalSupport(){ homeMasterEventView.goToContactUs(); }

    public void goToAboutUs(){
        homeMasterEventView.goToRead(1);
    }

    public void goToPrivacyPolicy(){
        homeMasterEventView.goToRead(2);
    }

    public void goToTermsConditions(){
        homeMasterEventView.goToRead(3);
    }

    @SuppressLint("LongLogTag")
    public void changeStatusNotification(){
        boolean status;
        if (aSwitchNotification.isChecked()){
            status= false;
        }else {
            status= true;
        }

        if (Validation.isNetDisponible(getActivity())){

            ProgresDialogCustom progresDialogCustom= new ProgresDialogCustom(getString(R.string.performing_operation));
            progresDialogCustom.show(getActivity().getSupportFragmentManager(),"DIALOG_PROGRESS");

            DriverPojo driverPojo= SharedPreferenceManager.getUser(getContext());
            HttpConexion.getUri()
                    .create(User.class)
                    .notificationStatus(driverPojo.getApiSessionKey(),"es",status)
                    .enqueue(new Callback<MessagePojo>() {
                        @Override
                        public void onResponse(Call<MessagePojo> call, Response<MessagePojo> response) {
                            progresDialogCustom.dismiss();

                            if (response.code() >= 400 && response.code() < 500) {
                                if (response.code() == 401) {
                                    LogOut.Do(getActivity(), true);
                                } else {
                                    ToastCustom.show(0,
                                            getContext(),
                                            getContext().getString(R.string.something_has_failed));
                                }
                            } else {
                                try {

                                    if (response.body().getType().equals("SUCCESS")) {
                                        driverPojo.setNotificationStatus(status);
                                        SharedPreferenceManager.setUser(getActivity(), driverPojo);
                                        statusSwitchNotification(status);
                                    } else {
                                        ToastCustom.show(0,
                                                getContext(),
                                                getContext().getString(R.string.something_has_failed));
                                    }

                                } catch (Exception e) {
                                    Log.e(TAG + " changeStatusNotification Exception", e.toString());
                                    ToastCustom.show(0,
                                            getContext(),
                                            getContext().getString(R.string.something_has_failed));
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<MessagePojo> call, Throwable t) {
                            progresDialogCustom.dismiss();

                            Log.e(TAG+" changeStatusNotification OnFailure",t.toString());
                            ToastCustom.show(0,
                                    getContext(),
                                    getContext().getString(R.string.something_has_failed));
                        }
                    });

        }else {
            ToastCustom.show(3,getActivity(),
                    getActivity().getString(R.string.without_internet));
        }

    }


    public void changeStatusScreen(){
        if (aSwitchScreen.isChecked()){
            SharedPreferenceManager.setScreen(getContext(),false);
            aSwitchScreen.setChecked(false);
        }else {
            SharedPreferenceManager.setScreen(getContext(),true);
            aSwitchScreen.setChecked(true);
        }

        homeMasterEventView.screenStatus();
    }

    public void changeStatusTheme(){
        if (aSwitchTheme.isChecked()){
            SharedPreferenceManager.setTheme(getContext(),false);
            aSwitchTheme.setChecked(false);

        }else {
            SharedPreferenceManager.setTheme(getContext(),true);
            aSwitchTheme.setChecked(true);
        }

        homeMasterEventView.themeStatus();
    }

    @SuppressLint("LongLogTag")
    public void changeStatusProCar(){
        boolean status;
        if (aSwitchProcar.isChecked()){
            status= false;
        }else {
            status= true;
        }

        if (Validation.isNetDisponible(getActivity())){

            ProgresDialogCustom progresDialogCustom= new ProgresDialogCustom(getString(R.string.performing_operation));
            progresDialogCustom.show(getActivity().getSupportFragmentManager(),"DIALOG_PROGRESS");

            DriverPojo driverPojo= SharedPreferenceManager.getUser(getContext());
            HttpConexion.getUri()
                    .create(User.class)
                    .procarStatus(driverPojo.getApiSessionKey(),"es",status)
                    .enqueue(new Callback<MessagePojo>() {
                        @Override
                        public void onResponse(Call<MessagePojo> call, Response<MessagePojo> response) {
                            progresDialogCustom.dismiss();

                            if (response.code() >= 400 && response.code() < 500) {
                                if (response.code() == 401) {
                                    LogOut.Do(getActivity(), true);
                                } else {
                                    ToastCustom.show(0,
                                            getContext(),
                                            getContext().getString(R.string.something_has_failed));
                                }
                            } else {
                                try {

                                    if (response.body().getType().equals("SUCCESS")) {
                                        driverPojo.setAcceptProcar(status);
                                        SharedPreferenceManager.setUser(getActivity(), driverPojo);
                                        statusSwitchProcar(status);
                                    } else {
                                        ToastCustom.show(0,
                                                getContext(),
                                                getContext().getString(R.string.something_has_failed));
                                    }

                                } catch (Exception e) {
                                    Log.e(TAG + " changeStatusProCar Exception", e.toString());
                                    ToastCustom.show(0,
                                            getContext(),
                                            getContext().getString(R.string.something_has_failed));
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<MessagePojo> call, Throwable t) {
                            progresDialogCustom.dismiss();

                            Log.e(TAG+" changeStatusProCar OnFailure",t.toString());
                            ToastCustom.show(0,
                                    getContext(),
                                    getContext().getString(R.string.something_has_failed));
                        }
                    });

        }else {
            ToastCustom.show(3,getActivity(),
                    getActivity().getString(R.string.without_internet));
        }

    }

    @SuppressLint("ResourceType")
    private void statusSwitchNotification(boolean status){
        aSwitchNotification.setChecked(status);
    }

    private void statusSwitchProcar(boolean status){
        aSwitchProcar.setChecked(status);
    }
    private void statusSwitchTheme(boolean status){
        aSwitchTheme.setChecked(status);
    }

}

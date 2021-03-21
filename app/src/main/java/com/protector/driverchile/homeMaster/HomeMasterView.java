package com.protector.driverchile.homeMaster;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.os.PowerManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.protector.driverchile.ApplicationMaster;
import com.protector.driverchile.contactUs.ContactUsView;
import com.protector.driverchile.currentTravel.CurrentTravelView;
import com.protector.driverchile.dialog.DialogControlVersion;
import com.protector.driverchile.dialog.DialogRating;
import com.protector.driverchile.dialog.DialogTripAccep;
import com.protector.driverchile.dialog.DialogChangePass;
import com.protector.driverchile.dialog.DialogInvoice;
import com.protector.driverchile.dialog.DialogLogout;
import com.protector.driverchile.R;
import com.protector.driverchile.dialog.DialogTripEnd;
import com.protector.driverchile.dialog.DialogTripStart;
import com.protector.driverchile.dialog.DialogTripWaiting;
import com.protector.driverchile.dialog.DialogTutorial;
import com.protector.driverchile.directionhelpers.FetchURL;
import com.protector.driverchile.directionhelpers.TaskLoadedCallback;
import com.protector.driverchile.httpData.HttpConexion;
import com.protector.driverchile.httpData.Travel;
import com.protector.driverchile.httpData.User;
import com.protector.driverchile.httpData.WebSocketConexion;
import com.protector.driverchile.membership.MembershipView;
import com.protector.driverchile.notification.NotificationView;
import com.protector.driverchile.profile.ProfileView;
import com.protector.driverchile.readHtml.ReadHtmlView;
import com.protector.driverchile.refeir.RefeirView;
import com.protector.driverchile.services.TrakingServices;
import com.protector.driverchile.settings.SettingView;
import com.protector.driverchile.travelHistory.TravelHistoryView;
import com.protector.driverchile.utils.DataModelJson.CurrentTravelModel;
import com.protector.driverchile.utils.DataModelJson.DriverPojo;
import com.protector.driverchile.utils.DataModelJson.LoginTrackingModel;
import com.protector.driverchile.utils.DataModelJson.MembershipModel;
import com.protector.driverchile.utils.DataModelJson.MembershipSimpleModel;
import com.protector.driverchile.utils.DateUtils;
import com.protector.driverchile.utils.ManagerService;
import com.protector.driverchile.utils.DataModelJson.MessagePojo;
import com.protector.driverchile.utils.DataModelJson.TravelInfoModel;
import com.protector.driverchile.utils.DataModelJson.VersionApp;
import com.protector.driverchile.utils.LogOut;
import com.protector.driverchile.utils.ProgresDialogCustom;
import com.protector.driverchile.utils.SharedPreferenceManager;
import com.protector.driverchile.utils.ToastCustom;
import com.protector.driverchile.utils.Validation;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.protector.driverchile.utils.OkHttpUtils.getUnsafeOkHttpClient;


@SuppressLint("LongLogTag")
public class HomeMasterView extends FragmentActivity implements HomeMasterEventView,
        View.OnClickListener, NavigationView.OnNavigationItemSelectedListener, TaskLoadedCallback {

    private String TAG = "HomeMasterView";
    private ImageView buttonOnDriver, buttonMenu, imageViewPhotoDriver;
    private CheckBox chkOnDuty;
    private TextView textViewToolbar, textViewNameDriver, textViewEmailDriver;
    private FrameLayout frameLayout;
    private DriverPojo driverPojo;
    private DrawerLayout drawerLayout;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private SettingView settingFragment;
    private ProfileView profileFragment;
    private TravelHistoryView travelHistoryFragment;
    private ReadHtmlView readHtmlFragment;
    private CurrentTravelView currentTravelFragment;
    private NotificationView notificationFragment;
    private RefeirView refeirFragment;
    private MembershipView membershipFragment;
    private ContactUsView contactUsFragment;
    protected PowerManager.WakeLock wakelock;
    private int selectfragment = 0;
    private int statusButtonMenu = 0;
    private String statusTrip;
    private MembershipModel membershipModel;
    private Boolean operationStatus;
    private String operationStatusName;
    private ConstraintLayout layoutToolbarMenu;
    private NavigationView navigationView;
    private LinearLayout layoutComplement;
    //private ConectTrackingStomp conectTrackingStomp;
    private BroadcastReceiver mRandomNumberReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // int receivedNumber = intent.getIntExtra("RandomNumber",-1);
            getCurrentTravel();

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        DriverPojo driverPojo = SharedPreferenceManager.getUser(getApplicationContext());

       /* if (driverPojo.getGender().toLowerCase().equals("female")){
            setTheme(R.style.AppThemePink);
        }else {
            setTheme(R.style.AppThemePro);
        }*/
        if (SharedPreferenceManager.getTheme(getApplicationContext()) && DateUtils.validTimeDarkTheme()) {
            setTheme(R.style.AppTheme);
        } else {
            setTheme(R.style.AppThemeDark);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        addView();
        screenStatus();
        sendTokenFirebase();
        changeImageStatusDriver(driverPojo.getOnDuty());
        initFragment();
        goToCurrentTravel();
        activateCurrentMenuColor();
        setStatusBarMaps();
        registerBroadcast();
        validationVersionApp();

        if (SharedPreferenceManager.getTutorial(this)) {
            showDialogTutorial();
        }
    }


    private void registerBroadcast() {
        LocalBroadcastManager.getInstance(this).registerReceiver(
                mRandomNumberReceiver,
                new IntentFilter("BROADCAST_FIREBASE")
        );
    }

    private void initFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        settingFragment = new SettingView(this);
        profileFragment = new ProfileView();
        travelHistoryFragment = new TravelHistoryView(this);
        notificationFragment = new NotificationView();
        contactUsFragment = new ContactUsView();
        refeirFragment = new RefeirView();
        membershipFragment = new MembershipView();
    }

    private void addView() {
        driverPojo = SharedPreferenceManager.getUser(getApplicationContext());
        Log.d("DRIVER INFO", driverPojo.makeJson());
        operationStatus = false;
        operationStatusName = "";
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);


        buttonMenu = findViewById(R.id.btn_menu);
        //Toolbar.LayoutParams params=buttonMenu.getLayoutParams();
        buttonMenu.setOnClickListener(this);
        //buttonOnDriver= findViewById(R.id.btn_on_driver);
        //buttonOnDriver.setOnClickListener(this);
        chkOnDuty = findViewById(R.id.chk_onDuty);
        chkOnDuty.setOnClickListener(this);
        statusTrip = "none";
        textViewToolbar = findViewById(R.id.txv_title_toolbar);
        textViewToolbar.setText(R.string.current_trip_home);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View viewDrawer = navigationView.getHeaderView(0);
        textViewNameDriver = viewDrawer.findViewById(R.id.txv_name_driver);
        textViewNameDriver.setText(driverPojo.getFullName());
        textViewEmailDriver = viewDrawer.findViewById(R.id.txv_email_driver);
        textViewEmailDriver.setText(driverPojo.getEmail());
        imageViewPhotoDriver = viewDrawer.findViewById(R.id.img_profil_photo);

        layoutToolbarMenu = findViewById(R.id.layoutBar);

        if (driverPojo.getPhotoUrl() != null) {
            Glide.with(getApplicationContext())
                    .load(driverPojo.getPhotoUrl())
                    .circleCrop()
                    .placeholder(R.drawable.ic_person)
                    .into(imageViewPhotoDriver);
        }

        frameLayout = findViewById(R.id.frame_home);
        layoutComplement = findViewById(R.id.layoutComplement);

    }


    private void setAdjustMarginToolbar(boolean maps) {

        if (maps) {
            layoutComplement.setVisibility(View.VISIBLE);
        } else {
            layoutComplement.setVisibility(View.GONE);
        }

    }


    public void toolbarMenuMode(boolean activar) {
        if (activar) {//normal
            //layoutToolbarMenu.setVisibility(View.VISIBLE);
            //layoutToolbarMap.setVisibility(View.GONE);
            //buttonMenu.setColorFilter(R.color.colorWhite);
            //buttonOnDriver.setColorFilter(R.color.colorWhite);
            //buttonMenu.setBackgroundResource(R.drawable.dw_background);
            //buttonOnDriver.setBackgroundResource(R.color.colorPrimaryDark);
            // buttonOnDriver.setVisibility(View.GONE);
            //setStatusBar();
            layoutToolbarMenu.setBackgroundResource(R.drawable.dw_background);
            textViewToolbar.setVisibility(View.VISIBLE);
            chkOnDuty.setVisibility(View.GONE);


        } else {//mapa
            //layoutToolbarMenu.setVisibility(View.GONE);
            //layoutToolbarMap.setVisibility(View.VISIBLE);
            //buttonMenu.setColorFilter(R.color.colorBlackDark);
            //buttonOnDriver.setColorFilter(R.color.colorBlackDark);
            //mptrip_completedbuttonMenu.setBackgroundResource(R.drawable.dw_circle_white);
            //buttonOnDriver.setBackgroundResource(R.drawable.dw_circle_white);
            //buttonMenu.setBackgroundResource(R.drawable.dw_background);
            //setStatusBarMaps();
            layoutToolbarMenu.setBackgroundResource(R.color.colorEmpy);
            //buttonOnDriver.setVisibility(View.GONE);
            textViewToolbar.setVisibility(View.GONE);
            chkOnDuty.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void screenStatus() {
        if (SharedPreferenceManager.getScreen(getApplicationContext())) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    @Override
    public void themeStatus() {
        if (currentTravelFragment != null) {
            currentTravelFragment.changeThemeMap();
        }

        if (SharedPreferenceManager.getTheme(getApplicationContext()) && DateUtils.validTimeDarkTheme()) {
            setTheme(R.style.AppTheme);
        } else {
            setTheme(R.style.AppThemeDark);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_menu:
                clickMenu();
                break;

            case R.id.btn_on_driver:
                //membershipRequestStatus();
                // onDuty();
                break;
            case R.id.chk_onDuty:
                //membershipRequestStatus();
                onDuty();
                break;

        }
    }

    private void removemMenuColor() {
        //navigationView.getMenu().getItemId()//(0).setChecked(true);
        for (int i = 0; i < navigationView.getMenu().size(); i++) {
            MenuItem item = navigationView.getMenu().getItem(i);
            item.setChecked(false);
        }
    }

    private void activateCurrentMenuColor() {
        //navigationView.getMenu().getItemId()//(0).setChecked(true);
        for (int i = 0; i < navigationView.getMenu().size(); i++) {
            MenuItem item = navigationView.getMenu().getItem(i);
            if (item.getItemId() == R.id.nav_current_trip)
                item.setChecked(true);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        // setItemMenuColor(menuItem);
        removemMenuColor();
        menuItem.setChecked(true);

        switch (menuItem.getItemId()) {
            case R.id.nav_current_trip:

                if (selectfragment != 0) {
                    comeBackFragment();
                    getCurrentTravel();
                    toolbarMenuMode(false);
                    setStatusBarMaps();
                }
                break;

            case R.id.nav_travel_histoy:
                if (selectfragment != 1) {
                    goToTravelHistory();
                    setStatusBar();
                }
                break;
            case R.id.nav_membership:
                if (selectfragment != 8) {
                    goToMembership();
                    setStatusBar();
                }
                break;
            case R.id.nav_notifications:
                if (selectfragment != 2) {
                    goToNotification();
                    setStatusBar();
                }
                break;

            case R.id.nav_settings:
                if (selectfragment != 3) {
                    goToSettings();
                    setStatusBar();
                }
                break;

            case R.id.nav_refeir:
                if (selectfragment != 4) {
                    goToRefeir();
                    setStatusBar();
                }
                break;

            case R.id.nav_log_out:
                showDialogLogout();
                break;
        }

        new Handler().postDelayed(
                () -> {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }, 100
        );

        return false;
    }

    private void clickMenu() {
        if (statusButtonMenu == 0) {
            openCloseDrawer();
        } else {
            comeBackFragment();
        }
    }

    public void openCloseDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (selectfragment > 0) {
            comeBackFragment();
        } else {
            super.onBackPressed();
        }
    }

    private void comeBackFragment() {
        if (selectfragment == 1) {
            getSupportFragmentManager().beginTransaction().remove(travelHistoryFragment).commit();
            selectfragment = 0;
            statusButtonMenu = 0;
            buttonMenu.setImageResource(R.drawable.ic_menu_driver);
            toolbarMenuMode(false);
            setStatusBarMaps();

            setToolbarText();//textViewToolbar.setText(R.string.current_trip);
        } else if (selectfragment == 2) {
            getSupportFragmentManager().beginTransaction().remove(notificationFragment).commit();
            selectfragment = 0;
            statusButtonMenu = 0;
            buttonMenu.setImageResource(R.drawable.ic_menu_driver);
            toolbarMenuMode(false);
            setStatusBarMaps();

            setToolbarText();//textViewToolbar.setText(R.string.current_trip);
        } else if (selectfragment == 3) {
            getSupportFragmentManager().beginTransaction().remove(settingFragment).commit();
            selectfragment = 0;
            statusButtonMenu = 0;
            buttonMenu.setImageResource(R.drawable.ic_menu_driver);
            toolbarMenuMode(false);
            setStatusBarMaps();

            setToolbarText();//textViewToolbar.setText(R.string.current_trip);
        } else if (selectfragment == 4) {
            getSupportFragmentManager().beginTransaction().remove(refeirFragment).commit();
            selectfragment = 0;
            statusButtonMenu = 0;
            buttonMenu.setImageResource(R.drawable.ic_menu_driver);
            toolbarMenuMode(false);
            setStatusBarMaps();
            setToolbarText();//textViewToolbar.setText(R.string.current_trip);
        } else if (selectfragment == 8) {
            getSupportFragmentManager().beginTransaction().remove(membershipFragment).commit();
            selectfragment = 0;
            statusButtonMenu = 0;
            buttonMenu.setImageResource(R.drawable.ic_menu_driver);
            toolbarMenuMode(false);
            setStatusBarMaps();
            setToolbarText();//textViewToolbar.setText(R.string.current_trip);
        } else if (selectfragment >= 5 && selectfragment != 8) {

            if (selectfragment == 5) {
                getSupportFragmentManager().beginTransaction().remove(profileFragment).commit();
            } else if (selectfragment == 6) {
                getSupportFragmentManager().beginTransaction().remove(readHtmlFragment).commit();
            } else if (selectfragment == 7) {
                getSupportFragmentManager().beginTransaction().remove(contactUsFragment).commit();
            }

            selectfragment = 3;

            statusButtonMenu = 0;
            buttonMenu.setImageResource(R.drawable.ic_menu_driver);

            textViewToolbar.setText(R.string.settings);
        }
    }

    public void showDialogLogout() {
        DialogLogout dialogLogout = new DialogLogout(this);
        dialogLogout.show(getSupportFragmentManager(), "DIALOG_LOGOUT");
        dialogLogout.setCancelable(false);
    }

    @Override
    public void showDialogChangePass() {
        DialogChangePass dialogChangePass = new DialogChangePass();
        dialogChangePass.show(getSupportFragmentManager(), "DIALOG_CHANGE_PASS");
        dialogChangePass.setCancelable(false);
    }

    @Override
    public void showDialogRating(TravelInfoModel travelInfoModel) {
        DialogRating dialogRating = new DialogRating(travelInfoModel);
        dialogRating.show(getSupportFragmentManager(), "DIALOG_RATING");
        dialogRating.setCancelable(false);
    }

    public void goToCurrentTravel() {
        comeBackFragment();
        selectfragment = 0;
        setToolbarText();
        toolbarMenuMode(false);
        //textViewToolbar.setText(R.string.current_trip);

        currentTravelFragment = new CurrentTravelView(this, driverPojo);

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_home, currentTravelFragment, "FM_MAP");
        fragmentTransaction.commit();
    }

    public void goToTravelHistory() {
        comeBackFragment();
        toolbarMenuMode(true);
        selectfragment = 1;
        textViewToolbar.setText(R.string.travel_history);

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_home, travelHistoryFragment, "FM_HISTORY");
        fragmentTransaction.commit();
    }

    public void goToMembership() {
        comeBackFragment();
        toolbarMenuMode(true);
        selectfragment = 8;
        textViewToolbar.setText(R.string.membership);

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_home, membershipFragment, "FM_MEMBERSHIP");
        fragmentTransaction.commit();
    }

    public void goToNotification() {
        comeBackFragment();
        toolbarMenuMode(true);
        selectfragment = 2;
        textViewToolbar.setText(R.string.notifications);

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_home, notificationFragment, "FM_NOTIFICATION");
        fragmentTransaction.commit();
    }

    public void goToSettings() {
        comeBackFragment();
        toolbarMenuMode(true);
        selectfragment = 3;
        textViewToolbar.setText(R.string.settings);

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_home, settingFragment, "FM_SETTING");
        fragmentTransaction.commit();
    }

    public void goToRefeir() {
        comeBackFragment();
        toolbarMenuMode(true);
        selectfragment = 4;
        textViewToolbar.setText(getString(R.string.refeir_friends));

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_home, refeirFragment, "FM_REFEIR");
        fragmentTransaction.commit();
    }

    @Override
    public void goToProfile() {
        selectfragment = 5;
        toolbarMenuMode(true);
        textViewToolbar.setText(R.string.account);
        buttonMenu.setImageResource(R.drawable.ic_back);
        statusButtonMenu = 1;

        fragmentTransaction = fragmentManager.beginTransaction();
        if (fragmentManager.findFragmentByTag("FM_PROFILE") == null) {
            fragmentTransaction.add(R.id.frame_home, profileFragment, "FM_PROFILE");
            fragmentTransaction.commit();
        }

    }

    @Override
    public void goToRead(int i) {
        selectfragment = 6;
        toolbarMenuMode(true);
        buttonMenu.setImageResource(R.drawable.ic_back);
        statusButtonMenu = 1;

        if (i == 1) {
            textViewToolbar.setText(R.string.about_us);
        } else if (i == 2) {
            textViewToolbar.setText(R.string.privacy_policy);
        } else if (i == 3) {
            textViewToolbar.setText(R.string.terms_conditions);
        }

        readHtmlFragment = new ReadHtmlView(i);

        fragmentTransaction = fragmentManager.beginTransaction();
        if (fragmentManager.findFragmentByTag("FM_READ") == null) {
            fragmentTransaction.add(R.id.frame_home, readHtmlFragment, "FM_READ");
            fragmentTransaction.commit();
        }

    }

    @Override
    public void goToContactUs() {
        selectfragment = 7;
        toolbarMenuMode(true);
        buttonMenu.setImageResource(R.drawable.ic_back);
        statusButtonMenu = 1;

        textViewToolbar.setText(R.string.technical_support);

        fragmentTransaction = fragmentManager.beginTransaction();
        if (fragmentManager.findFragmentByTag("FM_CONTACTUS") == null) {
            fragmentTransaction.add(R.id.frame_home, contactUsFragment, "FM_CONTACTUS");
            fragmentTransaction.commit();
        }

    }

    private void sendTokenFirebase() {
        String tokeFirbease = SharedPreferenceManager.getTokenFirebase(this);
        HttpConexion.getUri().create(User.class)
                .registerToken(tokeFirbease, driverPojo.getApiSessionKey())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.code() >= 400 && response.code() < 500) {
                            LogOut.Do(HomeMasterView.this, true);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        sendTokenFirebase();
                    }
                });
    }

    @Override
    public void validationVersionApp() {
        String versionApp = Validation.getAppVersion(this);
        if (Validation.isNetDisponible(this)) {

            HttpConexion.getUri()
                    .create(User.class)
                    .versionApp(versionApp)
                    .enqueue(new Callback<VersionApp>() {
                        @Override
                        public void onResponse(Call<VersionApp> call, Response<VersionApp> response) {

                            if (response.code() >= 400 && response.code() < 500) {
                                Log.e(TAG, "CODE VERSION APP " + response.code());

                                DialogControlVersion dialogControlVersion = new DialogControlVersion(
                                        getString(R.string.something_has_failed),
                                        5,
                                        HomeMasterView.this);
                                dialogControlVersion.show(getSupportFragmentManager(), "DIALOG_FAIL");
                                dialogControlVersion.setCancelable(false);

                            } else {
                                try {

                                    Log.d("ESTATUS VERSION APP", response.body().getResultCode());

                                    if (response.body().getResultCode().equals("MANDATORY_RELEASE")) {

                                        DialogControlVersion dialogControlVersion = new DialogControlVersion(
                                                response.body().getMessage(),
                                                1,
                                                HomeMasterView.this);
                                        dialogControlVersion.show(getSupportFragmentManager(), "DIALOG_VERSION");
                                        dialogControlVersion.setCancelable(false);

                                    } else if (response.body().getResultCode().equals("NO_NEW_RELEASE")) {
                                        getCurrentTravel();

                                    } else if (response.body().getResultCode().equals("OPTIONAL_RELEASE_AVAILABLE")) {
                                        DialogControlVersion dialogControlVersion = new DialogControlVersion(
                                                response.body().getMessage(),
                                                2,
                                                HomeMasterView.this);
                                        dialogControlVersion.show(getSupportFragmentManager(), "DIALOG_VERSION");
                                        dialogControlVersion.setCancelable(false);

                                        getCurrentTravel();

                                    } else if (response.body().getResultCode().equals("INVALID_RELEASE")) {
                                        DialogControlVersion dialogControlVersion = new DialogControlVersion(
                                                response.body().getMessage(),
                                                3,
                                                HomeMasterView.this);
                                        dialogControlVersion.show(getSupportFragmentManager(), "DIALOG_VERSION");
                                        dialogControlVersion.setCancelable(false);

                                    } else if (response.body().getResultCode().equals("UNSUPPORTED_RELEASE")) {
                                        DialogControlVersion dialogControlVersion = new DialogControlVersion(
                                                response.body().getMessage(),
                                                4,
                                                HomeMasterView.this);
                                        dialogControlVersion.show(getSupportFragmentManager(), "DIALOG_VERSION");
                                        dialogControlVersion.setCancelable(false);
                                    }

                                } catch (Exception e) {
                                    Log.e(TAG, "EXECPTION VERSION APP " + e.toString());

                                    DialogControlVersion dialogControlVersion = new DialogControlVersion(
                                            getString(R.string.something_has_failed),
                                            5,
                                            HomeMasterView.this);
                                    dialogControlVersion.show(getSupportFragmentManager(), "DIALOG_FAIL");
                                    dialogControlVersion.setCancelable(false);
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<VersionApp> call, Throwable t) {
                            Log.e(TAG, "ONFAILURE VERSION APP " + t.toString());

                            DialogControlVersion dialogControlVersion = new DialogControlVersion(
                                    getString(R.string.something_has_failed),
                                    5,
                                    HomeMasterView.this);
                            dialogControlVersion.show(getSupportFragmentManager(), "DIALOG_FAIL");
                            dialogControlVersion.setCancelable(false);
                        }
                    });

        } else {
            DialogControlVersion dialogControlVersion = new DialogControlVersion(
                    getString(R.string.must_internet_connection),
                    5,
                    this);

            dialogControlVersion.show(getSupportFragmentManager(), "DIALOG_INTERNET");
            dialogControlVersion.setCancelable(false);
        }
    }

    @Override
    public void starService() {
        if (!ManagerService.isRunning(TrakingServices.class, this)) {
            Intent intent = new Intent(this, TrakingServices.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent);
            } else {
                startService(intent);
            }
        }
    }

    @Override
    public void showInfoDialogMembership() {
        currentTravelFragment.showDialgoInfo("Servicio Inhabilitado", "Membresía inactiva, consulte su estatus o active una membresia para continuar con el servicio");
    }

    @Override
    public void callDirectionsUrl(MarkerOptions place1, MarkerOptions place2) {
        new FetchURL(this).execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");

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

        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + "AIzaSyB074uHXpmpnXqfG-P5hN3VtzGZW_X5j-M";
        //Toast.makeText(this, url, Toast.LENGTH_LONG).show();
        return url;
    }

    @SuppressLint("LongLogTag")
    public void membershipRequestStatus() {
        Log.d("CALL MEMBERSHIP URL", "------------------------------ CALLING");
        if (Validation.isNetDisponible(this)) {
            ProgresDialogCustom progresDialogCustom = new ProgresDialogCustom(getString(R.string.performing_operation));
            progresDialogCustom.show(getSupportFragmentManager(), "DIALOG_PROGRESS");
            DriverPojo driverPojo = SharedPreferenceManager.getUser(this);
            HttpConexion.getUri().create(User.class)
                    .getMembershipSimple(driverPojo.getApiSessionKey(), "es", false).enqueue(new Callback<MembershipSimpleModel>() {
                @Override
                public void onResponse(Call<MembershipSimpleModel> call, Response<MembershipSimpleModel> response) {
                    progresDialogCustom.dismiss();
                    if (response.code() >= 400 && response.code() < 500) {
                        //showNoMembership();
                        showInfoDialogMembership();
                        operationStatus = false;

                    } else {
                        try {

                            if (response.code() == 200) {
                                Log.d("MEMBERSHIP URL", "------------------------------ " + response.body().getMembershipTypeName());
                                operationStatusName = response.body().getOperationStatus();
                                if (response.body().getOperationStatus().equals("activated")) {
                                    operationStatus = true;
                                    operationStatusName = response.body().getOperationStatus();
                                    onDuty();
                                } else {
                                    operationStatus = false;
                                    showInfoDialogMembership();
                                }
                                //membershipModel=;
                            } else {
                                //showNoMembership();
                                operationStatus = false;
                                showInfoDialogMembership();
                            }

                        } catch (Exception e) {
                            //showNoMembership();
                            operationStatus = false;
                            showInfoDialogMembership();
                            ToastCustom.show(0,
                                    getApplicationContext(),
                                    getString(R.string.something_has_failed));
                        }
                    }


                }

                @Override
                public void onFailure(Call<MembershipSimpleModel> call, Throwable t) {
                    //statusButtonReSend(true);.
                    progresDialogCustom.dismiss();
                    Log.e(TAG + " apiOnFailure", t.toString());
                    ToastCustom.show(0
                            , getApplicationContext()
                            , ApplicationMaster.getAppContext().getString(R.string.something_has_failed));
                }
            });

        } else {
            //showLoad(false);
            ToastCustom.show(3, getApplicationContext(), getString(R.string.without_internet));
        }
    }

    //region TRAVEL
    private void onDuty() {

        boolean statusToChange;
        if (driverPojo.getOnDuty()) {
            statusToChange = false;
        } else {
            statusToChange = true;
        }
        //if(operationStatus){ ACTIVAR PARA RESTRINGIR USO DEL SERVICIO SIN MEMBRESIA
        if (Validation.isNetDisponible(this)) {
            ProgresDialogCustom progresDialogCustom = new ProgresDialogCustom(getString(R.string.performing_operation));
            progresDialogCustom.show(getSupportFragmentManager(), "DIALOG_PROGRESS");

            HttpConexion.getUri()
                    .create(Travel.class)
                    .driverStatus(driverPojo.getApiSessionKey(), "es", statusToChange)
                    .enqueue(new Callback<MessagePojo>() {
                        @Override
                        public void onResponse(Call<MessagePojo> call, Response<MessagePojo> response) {
                            progresDialogCustom.dismiss();
                            if (response.code() >= 400 && response.code() < 500) {
                                if (response.code() == 401) {
                                    LogOut.Do(HomeMasterView.this, true);
                                } else {
                                    currentTravelFragment.showDialgoInfo(getString(R.string.important_information), response.message());
                                    changeImageStatusDriver(driverPojo.getOnDuty());
                                }

                            } else {
                                driverPojo.setOnDuty(statusToChange);
                                SharedPreferenceManager.setUser(HomeMasterView.this, driverPojo);
                                changeImageStatusDriver(statusToChange);
                            }
                        }

                        @Override
                        public void onFailure(Call<MessagePojo> call, Throwable t) {
                            progresDialogCustom.dismiss();
                            Log.e(TAG + " cnDuty OnFailure", t.toString());
                            changeImageStatusDriver(driverPojo.getOnDuty());
                            ToastCustom.show(0,
                                    getApplicationContext(),
                                    getString(R.string.something_has_failed));
                        }
                    });


        } else {
            ToastCustom.show(3, this, getString(R.string.without_internet));
        }
     /* }else{
          currentTravelFragment.showDialgoInfo(getString(R.string.important_information),"Active una membresia para continuar");
          changeImageStatusDriver(driverPojo.getOnDuty());
      }*/


    }

    @SuppressLint("NewApi")
    private void changeImageStatusDriver(boolean ban) {
        chkOnDuty.setChecked(ban);
        /*if (ban){

            buttonOnDriver.setColorFilter(this.getResources().getColor(R.color.colorGreen));
        }else {
            buttonOnDriver.setColorFilter(this.getResources().getColor(R.color.colorWhite));
        }*/
    }

    @Override
    public void getCurrentTravel() {
        if (Validation.isNetDisponible(this)) {
            HttpConexion.getUri()
                    .create(Travel.class)
                    .getCurrent(driverPojo.getApiSessionKey(), "es")
                    .enqueue(new Callback<CurrentTravelModel>() {
                        @Override
                        public void onResponse(Call<CurrentTravelModel> call, Response<CurrentTravelModel> response) {
                            if (response.code() >= 400 && response.code() < 500) {
                                if (response.code() == 401) {
                                    LogOut.Do(HomeMasterView.this, true);
                                } else {
                                    clearInfoTravel();
                                }
                            } else {//
                                //
                                try {
                                    Log.d("TRAVEL INFORMACION", response.body().makeJson());
                                    managerStatusTravel(response.body());
                                } catch (Exception e) {
                                    Log.e(TAG + " getCurrentTravel Exception", e.toString());
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<CurrentTravelModel> call, Throwable t) {
                            Log.e(TAG + " getCurrentTravel OnFailure", t.toString());

                        }
                    });

        } else {
            ProgresDialogCustom progresDialogCustom = new ProgresDialogCustom(
                    getString(R.string.must_internet_connection));
            progresDialogCustom.show(getSupportFragmentManager(), "DIALOG_INTERNET");
        }
    }

    public void managerStatusTravel(CurrentTravelModel currentTravelModel) {

        do {
            comeBackFragment();
        } while (selectfragment != 0);
        statusTrip = currentTravelModel.getStatus();

        switch (currentTravelModel.getStatus()) {
            case "assigned":
                showDialogAceptTravel(currentTravelModel);
                break;

            case "accepted":
                chkOnDuty.setVisibility(View.GONE);
                //conectTracking(currentTravelModel);
                setToolbarText();
                showInfoTravel(currentTravelModel);
                break;

            case "arrived & waiting":
                chkOnDuty.setVisibility(View.GONE);
                showInfoTravel(currentTravelModel);
                break;

            case "started":
                chkOnDuty.setVisibility(View.GONE);
                showInfoTravel(currentTravelModel);
                break;
            case "ended":
                chkOnDuty.setVisibility(View.VISIBLE);
                //showInfoTravel(currentTravelModel);
                break;
        }

    }

    public void conectTracking(CurrentTravelModel currentTravelModel) {
        WebSocketConexion.getUri().create(User.class)
                .logintracking(driverPojo.getApiSessionKey())
                .enqueue(new Callback<LoginTrackingModel>() {
                    @Override
                    public void onResponse(Call<LoginTrackingModel> call, Response<LoginTrackingModel> response) {
                        if (response.code() >= 400 && response.code() < 500) {
                            LogOut.Do(HomeMasterView.this, true);
                        }
                        //Toast.makeText(this,"::QUEUE "+response.body().getQueue()+" ::ID "+response.body().getUserConnectionId() ,Toast.LENGTH_LONG).show();
                      /*  LoginTrackingModel loginTrackingModel = new LoginTrackingModel();
                        if (response.body().getQueue() != null && response.body().getUserConnectionId() != null) {
                            //ToastCustom.show(0, getApplicationContext(), "::QUEUE " + response.body().getQueue() + " ::ID " + response.body().getUserConnectionId());
                            loginTrackingModel.setQueue(response.body().getQueue());
                            loginTrackingModel.setUserConnectionId(response.body().getUserConnectionId());
                            //connectStomp(response.body().getUserConnectionId(),response.body().getQueue());
                        }*/
                        //conectTrackingStomp= new ConectTrackingStomp(loginTrackingModel);
                        //connectStomp(response.body().getUserConnectionId());
                        //connectStomp(response.body().getUserConnectionId());

                        //ToastCustom.show(0, getApplicationContext(), "TRACKING " + mensajeStatus);
                    }

                    @Override
                    public void onFailure(Call<LoginTrackingModel> call, Throwable t) {

                    }
                });
        // conectTrackingStomp.connectStomp();
    }

    public void setToolbarText() {
      /* if(statusTrip=="accepted") {
           textViewToolbar.setText(R.string.current_trip);
       }else{
           textViewToolbar.setText(R.string.current_trip_home);
       }*/
    }

    @Override
    public void managerStatusTravelDialog(CurrentTravelModel currentTravelModel) {

        switch (currentTravelModel.getStatus()) {
            case "assigned":
                showDialogAceptTravel(currentTravelModel);
                break;

            case "accepted":
                showDialogArrivedWaitingTravel(currentTravelModel, currentTravelFragment.getLocationDriver());
                break;

            case "arrived & waiting":
                showDialogStartedTravel(currentTravelModel);
                break;

            case "started":
                showDialogEndedTravel(currentTravelModel);
                break;
        }

    }

    private void showInfoTravel(CurrentTravelModel currentTravelModel) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(currentTravelModel);
        Log.d("CALL showInfoTravel", "------------------------------ ACEPTED " + jsonString);

        if (currentTravelFragment != null) {

            currentTravelFragment.showInfoTravel(currentTravelModel, true);
        } else {
            Log.d("CALL currentTravelFragment", "------------------------------ NULL ");
        }
    }

    @SuppressLint("LongLogTag")
    public void membershipRequest() {
        Log.d("CALL MEMBERSHIP URL", "------------------------------ CALLING");
        if (Validation.isNetDisponible(this)) {

            DriverPojo driverPojo = SharedPreferenceManager.getUser(this);
            HttpConexion.getUri().create(User.class)
                    .getMembershipSimple(driverPojo.getApiSessionKey(), "es", false).enqueue(new Callback<MembershipSimpleModel>() {
                @Override
                public void onResponse(Call<MembershipSimpleModel> call, Response<MembershipSimpleModel> response) {

                    if (response.code() >= 400 && response.code() < 500) {
                        //showNoMembership();
                        if (response.code() == 401) {
                            Log.d("MEMBERSHIP ERROR 401", "------------------------------ CALL");
                            showMembershipTravel(response.body(), false);
                        } else {
                            Log.d("MEMBERSHIP ERROR 400 O 500", "------------------------------ CODE " + response.code());
                            showMembershipTravel(response.body(), false);
                        }

                    } else {
                        try {

                            if (response.code() == 200) {
                                Log.d("MEMBERSHIP URL", "------------------------------ " + response.body().getMembershipTypeName());

                                if (response.body().getOperationStatus().equals("activated")) {
                                    operationStatus = true;
                                } else {
                                /*driverPojo.setOnDuty(false);
                                onDuty();*/
                                    operationStatus = false;
                                }
                                showMembershipTravel(response.body(), true);
                                //membershipModel=;
                            } else {
                                //showNoMembership();
                                Log.d("MEMBERSHIP", "------------no data");
                                showMembershipTravel(response.body(), false);
                                ToastCustom.show(0,
                                        getApplicationContext(),
                                        getString(R.string.something_has_failed));
                            }

                        } catch (Exception e) {
                            //showNoMembership();
                            ToastCustom.show(0,
                                    getApplicationContext(),
                                    getString(R.string.something_has_failed));
                        }
                    }


                }

                @Override
                public void onFailure(Call<MembershipSimpleModel> call, Throwable t) {
                    //statusButtonReSend(true);.

                    Log.e(TAG + " apiOnFailure", t.toString());
                    ToastCustom.show(0
                            , getApplicationContext()
                            , ApplicationMaster.getAppContext().getString(R.string.something_has_failed));
                }
            });

        } else {
            //showLoad(false);
            currentTravelFragment.showDialgoInfo(getString(R.string.important_information), getString(R.string.without_internet));
            //ToastCustom.show(3,getApplicationContext(),getString(R.string.without_internet));
        }
    }

    private void showMembershipTravel(MembershipSimpleModel membershipModel, boolean show) {
        Log.d("Show MEMBERSHIP frgment", "------------------------------ " + currentTravelFragment);
        if (currentTravelFragment != null) {
            Log.d("Show MEMBERSHIP", "------------------------------ " + show);

            currentTravelFragment.showMembershipInfo(membershipModel, show);
        }
    }
    @Override
    public void showOnduty(){
        chkOnDuty.setVisibility(View.VISIBLE);
    }

    @Override
    public void clearInfoTravel() {
        if (currentTravelFragment != null) {
            statusTrip = "none";
            //setToolbarText();

            currentTravelFragment.clearInfoTravel();
            membershipRequest();
        }
    }

    @Override
    public void showDialogAceptTravel(CurrentTravelModel currentTravelModel) {
        DialogTripAccep dialogAcepTrip = new DialogTripAccep(currentTravelModel, this);
        dialogAcepTrip.show(getSupportFragmentManager(), "DIALOG_ACCEPT_TRIP");
        dialogAcepTrip.setCancelable(false);
    }

    @Override
    public void showDialogArrivedWaitingTravel(CurrentTravelModel currentTravelModel, Location locationDriver) {
        /*DialogTripWaiting dialogAcepTrip= new DialogTripWaiting(currentTravelModel,this,locationDriver);
        dialogAcepTrip.show(getSupportFragmentManager(),"DIALOG_WAITING_TRIP");
        dialogAcepTrip.setCancelable(false);*/
    }

    @Override
    public void showDialogStartedTravel(CurrentTravelModel currentTravelModel) {
       /* DialogTripStart dialogTripStart= new DialogTripStart(currentTravelModel,this);
        dialogTripStart.show(getSupportFragmentManager(),"DIALOG_STARTED_TRIP");
        dialogTripStart.setCancelable(false);*/
    }

    @Override
    public void showDialogEndedTravel(CurrentTravelModel currentTravelModel) {
        DialogTripEnd dialogTripEnd = new DialogTripEnd(currentTravelModel, this);
        dialogTripEnd.show(getSupportFragmentManager(), "DIALOG_END_TRIP");
        dialogTripEnd.setCancelable(false);
    }

    private void showDialogTutorial() {
        DialogTutorial dialogTutorial = new DialogTutorial();
        dialogTutorial.show(getSupportFragmentManager(), "DIALOG_TUTORIAL");
        dialogTutorial.setCancelable(false);
    }

    @Override
    public void showInvoiceTravel(String tourId) {
        /*ToastCustom.show(0,HomeMasterView.this,
                "Entra a showInvoiceTravel");*/
        if (Validation.isNetDisponible(this)) {

            ProgresDialogCustom progresDialogGetting = new ProgresDialogCustom(getString(R.string.getting_details_trip));
            progresDialogGetting.show(getSupportFragmentManager(), "DIALOG_GETTIN");
            progresDialogGetting.setCancelable(false);

            HttpConexion.getUri()
                    .create(Travel.class)
                    .getInvoice(driverPojo.getApiSessionKey(), "es", tourId)
                    .enqueue(new Callback<TravelInfoModel>() {
                        @Override
                        public void onResponse(Call<TravelInfoModel> call, Response<TravelInfoModel> response) {
                            progresDialogGetting.dismiss();
                            if (response.code() >= 400 && response.code() < 500) {
                                if (response.code() == 401) {
                                    LogOut.Do(HomeMasterView.this, true);
                                } else {
                                    ToastCustom.show(0, HomeMasterView.this,
                                            getString(R.string.data_not_available));
                                }
                            } else {
                                try {

                                    DialogInvoice dialogInvoice = new DialogInvoice(response.body(), HomeMasterView.this);
                                    dialogInvoice.show(getSupportFragmentManager(), "DIALOG_INVOICE");

                                } catch (Exception e) {
                                    Log.e(TAG + " showInvoiceTravel Exception", e.toString());

                                    ToastCustom.show(0, getApplicationContext(),
                                            getString(R.string.getting_details_trip_fail));
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<TravelInfoModel> call, Throwable t) {
                            Log.e(TAG + " showInvoiceTravel OnFailure", t.toString());

                            progresDialogGetting.dismiss();
                            ToastCustom.show(0, getApplicationContext(),
                                    getString(R.string.getting_details_trip_fail));
                        }
                    });

        } else {
            ToastCustom.show(3, getApplicationContext(), getString(R.string.without_internet));
        }
    }

    @Override
    public void focusDriver() {
        if (currentTravelFragment != null) {
            currentTravelFragment.focusMarketDriver();
        }
    }

    @Override
    public void focusRider() {
        if (currentTravelFragment != null) {
            currentTravelFragment.focusMarketRider();
        }
    }

    @Override
    public void focusDestination() {
        if (currentTravelFragment != null) {
            currentTravelFragment.focusMarketDestination();
        }
    }

    @Override
    public void showSecureCode() {
        if (currentTravelFragment != null) {
            currentTravelFragment.showSegureCode(true);
        }
    }
    //endregion

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }


    public void setStatusBar() {

        if (SharedPreferenceManager.getTheme(getApplicationContext()) && DateUtils.validTimeDarkTheme()) {
            setTheme(R.style.AppTheme);
        } else {
            setTheme(R.style.AppThemeDark);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            window.setStatusBarColor(getResources().getColor(R.color.status_bar_color));

        }
        setAdjustMarginToolbar(false);
    }

    public void setStatusBarMaps() {
        if (SharedPreferenceManager.getTheme(getApplicationContext()) && DateUtils.validTimeDarkTheme()) {
            setTheme(R.style.AppTheme);
        } else {
            setTheme(R.style.AppThemeDark);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            //window.setStatusBarColor(getResources().getColor(R.color.status_bar_color));
            window.setStatusBarColor(Color.TRANSPARENT);

            //new
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setAdjustMarginToolbar(true);
    }

    @Override
    public void sendLocationTracking() {
        /*if (conectTrackingStomp != null) {

        }*/

    }

    @Override
    public void onTaskDone(Object... values) {

        if (currentTravelFragment != null) {

            //Toast.makeText(this, "onTask DONE", Toast.LENGTH_LONG).show();

            currentTravelFragment.drawPolylinesMap(values);
        }
    }

}

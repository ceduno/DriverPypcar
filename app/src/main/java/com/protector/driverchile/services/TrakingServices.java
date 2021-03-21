package com.protector.driverchile.services;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.JobIntentService;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.protector.driverchile.R;
import com.protector.driverchile.homeMaster.HomeMasterView;
import com.protector.driverchile.httpData.HttpConexion;
import com.protector.driverchile.httpData.Travel;
import com.protector.driverchile.utils.DataModelJson.DriverPojo;
import com.protector.driverchile.utils.DataModelJson.MessagePojo;
import com.protector.driverchile.utils.LogOut;
import com.protector.driverchile.utils.SharedPreferenceManager;
import com.protector.driverchile.utils.ToastCustom;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrakingServices  extends Service implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,LocationListener {

    private String TAG= "SERVICE TRAKING";
    static private final int NOTIFICATION_ID = 119;
    private GoogleApiClient mGoogleApiClient;
    private LocationManager locationManager;
    private Location location;


    @Override
    public void onCreate() {
        super.onCreate();
        buildGoogleApiClient();
        Log.d("TAG","oncreate");
    }

    @SuppressWarnings("ConstantConditions")
    private void buildGoogleApiClient() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
        }
        mGoogleApiClient.connect();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createNotification();

        if (!mGoogleApiClient.isConnected()) {
            mGoogleApiClient.connect();
        }

        Log.d("TAG","onStartCommand");
        return Service.START_STICKY;
    }

    private  void  createNotification(){
        Intent intent = new Intent(this, HomeMasterView.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,0);

        NotificationCompat.Builder  mBuilder = new NotificationCompat
                .Builder(this,getString(R.string.default_notification_channel_gps_id))
                .setSmallIcon(R.drawable.ic_app)
                .setContentTitle(getString(R.string.location_tracking))
                .setContentText(getString(R.string.location_tracking_on))
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.location_tracking);
            String description = getString(R.string.location_tracking_on);
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel channel = new NotificationChannel
                    (getString(R.string.default_notification_channel_gps_id), name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        startForeground(NOTIFICATION_ID, mBuilder.build());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    //region GPS
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        startLocationUpdate();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @SuppressLint("MissingPermission")
    private void startLocationUpdate() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {

            if (locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER) != null){
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                sendLocation();
            }else if (locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)!= null){
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                sendLocation();
            }

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER
                    , 2000, 10f,this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("TAG","onLocationChange "+location.getLatitude() + location.getLongitude());
        this.location= location;
        sendLocation();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onProviderDisabled(String provider) {
        endService();
    }


    private void sendLocation(){
        DriverPojo driverPojo= SharedPreferenceManager.getUser(this);
        if (driverPojo!=null){

            if (driverPojo.getApiSessionKey()!=null){
                HttpConexion.getUri()
                        .create(Travel.class)
                        .upDateGps(driverPojo.getApiSessionKey(),"es",location.getLatitude(),location.getLongitude())
                        .enqueue(new Callback<MessagePojo>() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void onResponse(Call<MessagePojo> call, Response<MessagePojo> response) {
                                if (response.code()>=400 && response.code()<500){
                                    if (response.code()==401){

                                        Toast.makeText(getApplicationContext(),
                                                getApplication().getText(R.string.app_name)
                                                        +"\n"+
                                                        getApplication().getText(R.string.expired_session)
                                                ,Toast.LENGTH_SHORT).show();

                                        SharedPreferenceManager.deletUser(getApplicationContext());
                                        SharedPreferenceManager.setLogin(getApplicationContext(),false);
                                    }
                                    endService();
                                }
                            }

                            @Override
                            public void onFailure(Call<MessagePojo> call, Throwable t) {
                                sendLocation();
                            }
                        });

            }else {
                onDestroy();
            }

        }else {
            onDestroy();
        }
    }
    //endregion


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void endService(){
        Log.d("TAG","endService");

        //Intent intent = new Intent(this, HomeMasterView.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //stopForeground(intent.getFlags());
        stopLocationUpdate();
        stopSelf();
    }

    @Override
    public void onDestroy() {
        Log.d("TAG","onDestroy");
        stopLocationUpdate();
        super.onDestroy();
    }

    private void stopLocationUpdate() {
        Log.d("TAG","stopLocationUpdate");
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }
}

package com.protector.driverchile.homeMaster;

import android.location.Location;

import com.google.android.gms.maps.model.MarkerOptions;
import com.protector.driverchile.utils.DataModelJson.CurrentTravelModel;
import com.protector.driverchile.utils.DataModelJson.TravelInfoModel;

public interface HomeMasterEventView {
    void goToProfile();
    void goToRead(int i);
    void showDialogLogout();
    void showDialogChangePass();
    void starService();
    void goToContactUs();
    void getCurrentTravel();
    void showDialogAceptTravel(CurrentTravelModel currentTravelModel);
    void showDialogArrivedWaitingTravel(CurrentTravelModel currentTravelModel, Location locationDriver);
    void showDialogStartedTravel(CurrentTravelModel currentTravelModel);
    void showDialogEndedTravel(CurrentTravelModel currentTravelModel);
    void showInvoiceTravel(String tourId);
    void showDialogRating(TravelInfoModel travelInfoModel);
    void managerStatusTravelDialog(CurrentTravelModel currentTravelModel);
    void managerStatusTravel(CurrentTravelModel currentTravelModel);
    void clearInfoTravel();
    void focusRider();
    void focusDriver();
    void focusDestination();
    void showSecureCode();
    void validationVersionApp();
    void screenStatus();
    void themeStatus();
    void membershipRequest();
    void showInfoDialogMembership();
    void callDirectionsUrl(MarkerOptions place1, MarkerOptions place2);
    void setStatusBar();
    void setStatusBarMaps();
    void sendLocationTracking();
    void showOnduty();
}

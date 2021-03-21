package com.protector.driverchile.utils.DataModelJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/***/
public class RatingModel {
    @SerializedName("driverId")
    @Expose
    private String driverId;
    @SerializedName("passengerId")
    @Expose
    private String passengerId;
    @SerializedName("tripId")
    @Expose
    private String tripId;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("rate")
    @Expose
    private float rate;

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}

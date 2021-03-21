package com.protector.driverchile.utils.DataModelJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TourIdModel {
    @SerializedName("tourId")
    @Expose
    private String tourId;

    public String getTourId() {
        return tourId;
    }

    public void setTourId(String tourId) {
        this.tourId = tourId;
    }
}

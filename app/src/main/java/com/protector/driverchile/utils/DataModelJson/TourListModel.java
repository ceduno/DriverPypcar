package com.protector.driverchile.utils.DataModelJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TourListModel {
    @SerializedName("afterTime")
    @Expose
    private Double afterTime;
    @SerializedName("tourList")
    @Expose
    private List<TourModel> tourList = null;

    public Double getAfterTime() {
        return afterTime;
    }

    public void setAfterTime(Double afterTime) {
        this.afterTime = afterTime;
    }

    public List<TourModel> getTourList() {
        return tourList;
    }

    public void setTourList(List<TourModel> tourList) {
        this.tourList = tourList;
    }
}

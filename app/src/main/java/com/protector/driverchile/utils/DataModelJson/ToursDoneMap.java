package com.protector.driverchile.utils.DataModelJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Marlon Viana on 11/06/2019
 * @email 92marlonViana@gmail.com
 */
public class ToursDoneMap {
    @SerializedName("createdAt")
    @Expose
    private Long createdAt;
    @SerializedName("userTourId")
    @Expose
    private String userTourId;
    @SerializedName("driverAmount")
    @Expose
    private String driverAmount;
    @SerializedName("operative_status")
    @Expose
    private String operativeStatus;

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserTourId() {
        return userTourId;
    }

    public void setUserTourId(String userTourId) {
        this.userTourId = userTourId;
    }

    public String getDriverAmount() {
        return driverAmount;
    }

    public void setDriverAmount(String driverAmount) {
        this.driverAmount = driverAmount;
    }

    public String getOperativeStatus() {
        return operativeStatus;
    }

    public void setOperativeStatus(String operativeStatus) {
        this.operativeStatus = operativeStatus;
    }
}

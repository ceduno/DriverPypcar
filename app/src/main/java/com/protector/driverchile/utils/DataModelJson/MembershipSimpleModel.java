package com.protector.driverchile.utils.DataModelJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Carlos Duno
 *
 * {
 * "type": "SUCCESS"
 * " webMembershipType":"webMembershipType",
 * "remainingTrips": remainingTrips,
 * "membershipValidTo": membershipValidTo,
 * "remainingDays": remainingDays,
 * "payable": payable,
 * "toursDone": [{"userTourId": 30001,"createdAt": Long, "driverAmount": Double, "operative_status":"Cerrada o Abierta segun dea el caso"}]
 * }
 */

public class MembershipSimpleModel {

    @SerializedName("operationStatus")
    @Expose
    private String operationStatus;

    @SerializedName("membershipTypeName")
    @Expose
    private String membershipTypeName;
    @SerializedName("remainingTrips")
    @Expose
    private String remainingTrips;

    @SerializedName("remainingDays")
    @Expose
    private String remainingDays;



    public String getMembershipTypeName() {
        return membershipTypeName;
    }

    public void setMembershipTypeName(String webMembershipType) {
        this.membershipTypeName = webMembershipType;
    }

    public String getRemainingTrips() {
        return remainingTrips;
    }

    public void setRemainingTrips(String remainingTrips) {
        this.remainingTrips = remainingTrips;
    }



    public String getRemainingDays() {
        return remainingDays;
    }

    public void setRemainingDays(String remainingDays) {
        this.remainingDays = remainingDays;
    }



    public String getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(String operationStatus) {
        this.operationStatus = operationStatus;
    }


}

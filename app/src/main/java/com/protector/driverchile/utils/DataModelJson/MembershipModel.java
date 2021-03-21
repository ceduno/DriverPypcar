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

public class MembershipModel {
    @SerializedName("planAmount")
    @Expose
    private String planAmount;
    @SerializedName("membershipEndedDate")
    @Expose
    private String membershipEndedDate;
    @SerializedName("membershipStartedDate")
    @Expose
    private String membershipStartedDate;
    @SerializedName("creditExpirationDays")
    @Expose
    private String creditExpirationDays;
    @SerializedName("operationDebtAmount")
    @Expose
    private String operationDebtAmount;
    @SerializedName("creditRate")
    @Expose
    private String creditRate;
    @SerializedName("paymentCondition")
    @Expose
    private String paymentCondition;
    @SerializedName("operationStatus")
    @Expose
    private String operationStatus;
    @SerializedName("paysDone")
    @Expose
    private String paysDone;
    @SerializedName("membershipTypeName")
    @Expose
    private String membershipTypeName;
    @SerializedName("remainingTrips")
    @Expose
    private String remainingTrips;
    @SerializedName("remainingDays")
    @Expose
    private String remainingDays;
    @SerializedName("payable")
    @Expose
    private String payable;
    @SerializedName("limitMembershipTypeTrips")
    @Expose
    private String limitMembershipTypeTrips ;
    @SerializedName("toursDone")
    @Expose
    private List<ToursDoneModel> toursDone = null;

    public String getLimitMembershipTypeTrips() {
        return limitMembershipTypeTrips;
    }

    public void setLimitMembershipTypeTrips(String limitMembershipTypeTrips) {
        this.limitMembershipTypeTrips = limitMembershipTypeTrips;
    }

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

    public String getPayable() {
        return payable;
    }

    public void setPayable(String payable) {
        this.payable = payable;
    }

    public List<ToursDoneModel> getToursDone() {
        return toursDone;
    }

    public void setToursDone(List<ToursDoneModel> toursDone) {
        this.toursDone = toursDone;
    }
    public String getPlanAmount() {
        return planAmount;
    }

    public void setPlanAmount(String planAmount) {
        this.planAmount = planAmount;
    }

    public String getMembershipEndedDate() {
        return membershipEndedDate;
    }

    public void setMembershipEndedDate(String membershipEndedDate) {
        this.membershipEndedDate = membershipEndedDate;
    }

    public String getMembershipStartedDate() {
        return membershipStartedDate;
    }

    public void setMembershipStartedDate(String membershipStartedDate) {
        this.membershipStartedDate = membershipStartedDate;
    }

    public String getCreditExpirationDays() {
        return creditExpirationDays;
    }

    public void setCreditExpirationDays(String creditExpirationDays) {
        this.creditExpirationDays = creditExpirationDays;
    }

    public String getOperationDebtAmount() {
        return operationDebtAmount;
    }

    public void setOperationDebtAmount(String operationDebtAmount) {
        this.operationDebtAmount = operationDebtAmount;
    }

    public String getCreditRate() {
        return creditRate;
    }

    public void setCreditRate(String creditRate) {
        this.creditRate = creditRate;
    }

    public String getPaymentCondition() {
        return paymentCondition;
    }

    public void setPaymentCondition(String paymentCondition) {
        this.paymentCondition = paymentCondition;
    }

    public String getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(String operationStatus) {
        this.operationStatus = operationStatus;
    }

    public String getPaysDone() {
        return paysDone;
    }

    public void setPaysDone(String paysDone) {
        this.paysDone = paysDone;
    }
}

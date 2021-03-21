package com.protector.driverchile.utils.DataModelJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactModel {

    @SerializedName("createdAt")
    @Expose
    private Double createdAt;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("updatedAt")
    @Expose
    private Double updatedAt;
    @SerializedName("updatedBy")
    @Expose
    private String updatedBy;
    @SerializedName("recordStatus")
    @Expose
    private String recordStatus;
    @SerializedName("adminCompanyContactId")
    @Expose
    private String adminCompanyContactId;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("phoneNumberOne")
    @Expose
    private String phoneNumberOne;
    @SerializedName("phoneNumberTwo")
    @Expose
    private String phoneNumberTwo;
    @SerializedName("phoneNumberOneCode")
    @Expose
    private String phoneNumberOneCode;
    @SerializedName("phoneNumberTwoCode")
    @Expose
    private String phoneNumberTwoCode;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("geolocation")
    @Expose
    private String geolocation;
    @SerializedName("fax")
    @Expose
    private String fax;
    @SerializedName("email")
    @Expose
    private String email;

    public Double getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Double createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Double getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Double updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getAdminCompanyContactId() {
        return adminCompanyContactId;
    }

    public void setAdminCompanyContactId(String adminCompanyContactId) {
        this.adminCompanyContactId = adminCompanyContactId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumberOne() {
        return phoneNumberOne;
    }

    public void setPhoneNumberOne(String phoneNumberOne) {
        this.phoneNumberOne = phoneNumberOne;
    }

    public String getPhoneNumberTwo() {
        return phoneNumberTwo;
    }

    public void setPhoneNumberTwo(String phoneNumberTwo) {
        this.phoneNumberTwo = phoneNumberTwo;
    }

    public String getPhoneNumberOneCode() {
        return phoneNumberOneCode;
    }

    public void setPhoneNumberOneCode(String phoneNumberOneCode) {
        this.phoneNumberOneCode = phoneNumberOneCode;
    }

    public String getPhoneNumberTwoCode() {
        return phoneNumberTwoCode;
    }

    public void setPhoneNumberTwoCode(String phoneNumberTwoCode) {
        this.phoneNumberTwoCode = phoneNumberTwoCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(String geolocation) {
        this.geolocation = geolocation;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

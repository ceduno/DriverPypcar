package com.protector.driverchile.utils.DataModelJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Marlon Viana on 22/03/2019
 * @email 92marlonViana@gmail.com
 */
public class DrivingLicenseModel {
    @SerializedName("createdAt")
    @Expose
    private Long createdAt;
    @SerializedName("createdBy")
    @Expose
    private Object createdBy;
    @SerializedName("updatedAt")
    @Expose
    private Long updatedAt;
    @SerializedName("updatedBy")
    @Expose
    private Object updatedBy;
    @SerializedName("recordStatus")
    @Expose
    private Object recordStatus;
    @SerializedName("drivingLicenseInfoId")
    @Expose
    private String drivingLicenseInfoId;
    @SerializedName("fName")
    @Expose
    private Object fName;
    @SerializedName("mName")
    @Expose
    private Object mName;
    @SerializedName("lName")
    @Expose
    private Object lName;
    @SerializedName("driverLicenseCardNumber")
    @Expose
    private Object driverLicenseCardNumber;
    @SerializedName("dob")
    @Expose
    private Long dob;
    @SerializedName("socialSecurityNumber")
    @Expose
    private String socialSecurityNumber;
    @SerializedName("insuranceEffectiveDate")
    @Expose
    private Object insuranceEffectiveDate;
    @SerializedName("insuranceExpirationDate")
    @Expose
    private Object insuranceExpirationDate;
    @SerializedName("insurancePhotoUrl")
    @Expose
    private Object insurancePhotoUrl;
    @SerializedName("drivingLicensePhotoUrl")
    @Expose
    private String drivingLicensePhotoUrl;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("drivingLicenseBackPhotoUrl")
    @Expose
    private Object drivingLicenseBackPhotoUrl;
    @SerializedName("birthAccreditationPassportPhotoUrl")
    @Expose
    private String birthAccreditationPassportPhotoUrl;
    @SerializedName("criminalHistoryPhotoUrl")
    @Expose
    private String criminalHistoryPhotoUrl;
    @SerializedName("auBusinessNo")
    @Expose
    private Object auBusinessNo;
    @SerializedName("socialSecurityPhotoUrl")
    @Expose
    private Object socialSecurityPhotoUrl;
    @SerializedName("licenseExpirationDate")
    @Expose
    private Long licenseExpirationDate;
    @SerializedName("historyCertificateUrl")
    @Expose
    private String historyCertificateUrl;

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Object getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Object createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Object updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Object getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(Object recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getDrivingLicenseInfoId() {
        return drivingLicenseInfoId;
    }

    public void setDrivingLicenseInfoId(String drivingLicenseInfoId) {
        this.drivingLicenseInfoId = drivingLicenseInfoId;
    }

    public Object getFName() {
        return fName;
    }

    public void setFName(Object fName) {
        this.fName = fName;
    }

    public Object getMName() {
        return mName;
    }

    public void setMName(Object mName) {
        this.mName = mName;
    }

    public Object getLName() {
        return lName;
    }

    public void setLName(Object lName) {
        this.lName = lName;
    }

    public Object getDriverLicenseCardNumber() {
        return driverLicenseCardNumber;
    }

    public void setDriverLicenseCardNumber(Object driverLicenseCardNumber) {
        this.driverLicenseCardNumber = driverLicenseCardNumber;
    }

    public Long getDob() {
        return dob;
    }

    public void setDob(Long dob) {
        this.dob = dob;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public Object getInsuranceEffectiveDate() {
        return insuranceEffectiveDate;
    }

    public void setInsuranceEffectiveDate(Object insuranceEffectiveDate) {
        this.insuranceEffectiveDate = insuranceEffectiveDate;
    }

    public Object getInsuranceExpirationDate() {
        return insuranceExpirationDate;
    }

    public void setInsuranceExpirationDate(Object insuranceExpirationDate) {
        this.insuranceExpirationDate = insuranceExpirationDate;
    }

    public Object getInsurancePhotoUrl() {
        return insurancePhotoUrl;
    }

    public void setInsurancePhotoUrl(Object insurancePhotoUrl) {
        this.insurancePhotoUrl = insurancePhotoUrl;
    }

    public String getDrivingLicensePhotoUrl() {
        return drivingLicensePhotoUrl;
    }

    public void setDrivingLicensePhotoUrl(String drivingLicensePhotoUrl) {
        this.drivingLicensePhotoUrl = drivingLicensePhotoUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Object getDrivingLicenseBackPhotoUrl() {
        return drivingLicenseBackPhotoUrl;
    }

    public void setDrivingLicenseBackPhotoUrl(Object drivingLicenseBackPhotoUrl) {
        this.drivingLicenseBackPhotoUrl = drivingLicenseBackPhotoUrl;
    }

    public String getBirthAccreditationPassportPhotoUrl() {
        return birthAccreditationPassportPhotoUrl;
    }

    public void setBirthAccreditationPassportPhotoUrl(String birthAccreditationPassportPhotoUrl) {
        this.birthAccreditationPassportPhotoUrl = birthAccreditationPassportPhotoUrl;
    }

    public String getCriminalHistoryPhotoUrl() {
        return criminalHistoryPhotoUrl;
    }

    public void setCriminalHistoryPhotoUrl(String criminalHistoryPhotoUrl) {
        this.criminalHistoryPhotoUrl = criminalHistoryPhotoUrl;
    }

    public Object getAuBusinessNo() {
        return auBusinessNo;
    }

    public void setAuBusinessNo(Object auBusinessNo) {
        this.auBusinessNo = auBusinessNo;
    }

    public Object getSocialSecurityPhotoUrl() {
        return socialSecurityPhotoUrl;
    }

    public void setSocialSecurityPhotoUrl(Object socialSecurityPhotoUrl) {
        this.socialSecurityPhotoUrl = socialSecurityPhotoUrl;
    }

    public Long getLicenseExpirationDate() {
        return licenseExpirationDate;
    }

    public void setLicenseExpirationDate(Long licenseExpirationDate) {
        this.licenseExpirationDate = licenseExpirationDate;
    }

    public String getHistoryCertificateUrl() {
        return historyCertificateUrl;
    }

    public void setHistoryCertificateUrl(String historyCertificateUrl) {
        this.historyCertificateUrl = historyCertificateUrl;
    }


}

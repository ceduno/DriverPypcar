package com.protector.driverchile.utils.DataModelJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarModel {

    @SerializedName("createdAt")
    @Expose
    private Integer createdAt;
    @SerializedName("createdBy")
    @Expose
    private Object createdBy;
    @SerializedName("updatedAt")
    @Expose
    private Integer updatedAt;
    @SerializedName("updatedBy")
    @Expose
    private Object updatedBy;
    @SerializedName("recordStatus")
    @Expose
    private Object recordStatus;
    @SerializedName("carId")
    @Expose
    private String carId;
    @SerializedName("modelName")
    @Expose
    private String modelName;
    @SerializedName("carColor")
    @Expose
    private String carColor;
    @SerializedName("carPlateNo")
    @Expose
    private String carPlateNo;
    @SerializedName("carYear")
    @Expose
    private Integer carYear;
    @SerializedName("noOfPassenger")
    @Expose
    private Integer noOfPassenger;
    @SerializedName("driverId")
    @Expose
    private Object driverId;
    @SerializedName("carTpeId")
    @Expose
    private String carTpeId;
    @SerializedName("owner")
    @Expose
    private Object owner;
    @SerializedName("make")
    @Expose
    private String make;
    @SerializedName("backImgUrl")
    @Expose
    private String backImgUrl;
    @SerializedName("frontImgUrl")
    @Expose
    private String frontImgUrl;
    @SerializedName("carTitle")
    @Expose
    private Object carTitle;
    @SerializedName("carType")
    @Expose
    private Object carType;
    @SerializedName("registrationPhotoUrl")
    @Expose
    private Object registrationPhotoUrl;
    @SerializedName("insurancePhotoUrl")
    @Expose
    private Object insurancePhotoUrl;
    @SerializedName("inspectionReportPhotoUrl")
    @Expose
    private Object inspectionReportPhotoUrl;
    @SerializedName("vehicleCommercialLicencePhotoUrl")
    @Expose
    private Object vehicleCommercialLicencePhotoUrl;
    @SerializedName("ownerIdUrl")
    @Expose
    private Object ownerIdUrl;
    @SerializedName("ownerDniBackUrl")
    @Expose
    private Object ownerDniBackUrl;
    @SerializedName("ownerContractUrl")
    @Expose
    private Object ownerContractUrl;
    @SerializedName("ownsCar")
    @Expose
    private Boolean ownsCar;
    @SerializedName("rvmCertUrl")
    @Expose
    private String rvmCertUrl;
    @SerializedName("circulationLicenseUrl")
    @Expose
    private String circulationLicenseUrl;
    @SerializedName("leftImgUrl")
    @Expose
    private String leftImgUrl;
    @SerializedName("rightImgUrl")
    @Expose
    private String rightImgUrl;
    @SerializedName("lastAddCar")
    @Expose
    private String lastAddCar;

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public Object getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Object createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Integer updatedAt) {
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

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarPlateNo() {
        return carPlateNo;
    }

    public void setCarPlateNo(String carPlateNo) {
        this.carPlateNo = carPlateNo;
    }

    public Integer getCarYear() {
        return carYear;
    }

    public void setCarYear(Integer carYear) {
        this.carYear = carYear;
    }

    public Integer getNoOfPassenger() {
        return noOfPassenger;
    }

    public void setNoOfPassenger(Integer noOfPassenger) {
        this.noOfPassenger = noOfPassenger;
    }

    public Object getDriverId() {
        return driverId;
    }

    public void setDriverId(Object driverId) {
        this.driverId = driverId;
    }

    public String getCarTpeId() {
        return carTpeId;
    }

    public void setCarTpeId(String carTpeId) {
        this.carTpeId = carTpeId;
    }

    public Object getOwner() {
        return owner;
    }

    public void setOwner(Object owner) {
        this.owner = owner;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getBackImgUrl() {
        return backImgUrl;
    }

    public void setBackImgUrl(String backImgUrl) {
        this.backImgUrl = backImgUrl;
    }

    public String getFrontImgUrl() {
        return frontImgUrl;
    }

    public void setFrontImgUrl(String frontImgUrl) {
        this.frontImgUrl = frontImgUrl;
    }

    public Object getCarTitle() {
        return carTitle;
    }

    public void setCarTitle(Object carTitle) {
        this.carTitle = carTitle;
    }

    public Object getCarType() {
        return carType;
    }

    public void setCarType(Object carType) {
        this.carType = carType;
    }

    public Object getRegistrationPhotoUrl() {
        return registrationPhotoUrl;
    }

    public void setRegistrationPhotoUrl(Object registrationPhotoUrl) {
        this.registrationPhotoUrl = registrationPhotoUrl;
    }

    public Object getInsurancePhotoUrl() {
        return insurancePhotoUrl;
    }

    public void setInsurancePhotoUrl(Object insurancePhotoUrl) {
        this.insurancePhotoUrl = insurancePhotoUrl;
    }

    public Object getInspectionReportPhotoUrl() {
        return inspectionReportPhotoUrl;
    }

    public void setInspectionReportPhotoUrl(Object inspectionReportPhotoUrl) {
        this.inspectionReportPhotoUrl = inspectionReportPhotoUrl;
    }

    public Object getVehicleCommercialLicencePhotoUrl() {
        return vehicleCommercialLicencePhotoUrl;
    }

    public void setVehicleCommercialLicencePhotoUrl(Object vehicleCommercialLicencePhotoUrl) {
        this.vehicleCommercialLicencePhotoUrl = vehicleCommercialLicencePhotoUrl;
    }

    public Object getOwnerIdUrl() {
        return ownerIdUrl;
    }

    public void setOwnerIdUrl(Object ownerIdUrl) {
        this.ownerIdUrl = ownerIdUrl;
    }

    public Object getOwnerDniBackUrl() {
        return ownerDniBackUrl;
    }

    public void setOwnerDniBackUrl(Object ownerDniBackUrl) {
        this.ownerDniBackUrl = ownerDniBackUrl;
    }

    public Object getOwnerContractUrl() {
        return ownerContractUrl;
    }

    public void setOwnerContractUrl(Object ownerContractUrl) {
        this.ownerContractUrl = ownerContractUrl;
    }

    public Boolean getOwnsCar() {
        return ownsCar;
    }

    public void setOwnsCar(Boolean ownsCar) {
        this.ownsCar = ownsCar;
    }

    public String getRvmCertUrl() {
        return rvmCertUrl;
    }

    public void setRvmCertUrl(String rvmCertUrl) {
        this.rvmCertUrl = rvmCertUrl;
    }

    public String getCirculationLicenseUrl() {
        return circulationLicenseUrl;
    }

    public void setCirculationLicenseUrl(String circulationLicenseUrl) {
        this.circulationLicenseUrl = circulationLicenseUrl;
    }

    public String getLeftImgUrl() {
        return leftImgUrl;
    }

    public void setLeftImgUrl(String leftImgUrl) {
        this.leftImgUrl = leftImgUrl;
    }

    public String getRightImgUrl() {
        return rightImgUrl;
    }

    public void setRightImgUrl(String rightImgUrl) {
        this.rightImgUrl = rightImgUrl;
    }

    public String getLastAddCar() {
        return lastAddCar;
    }

    public void setLastAddCar(String lastAddCar) {
        this.lastAddCar = lastAddCar;
    }
}

package com.protector.driverchile.utils.DataModelJson;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TourModel {

    @SerializedName("cancellationCharges")
    @Expose
    private Double cancellationCharges;
    @SerializedName("dateTime")
    @Expose
    private String dateTime;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("sourceLatitude")
    @Expose
    private String sourceLatitude;
    @SerializedName("destinationAddress")
    @Expose
    private String destinationAddress;
    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("perKmFare")
    @Expose
    private Double perKmFare;
    @SerializedName("usage")
    @Expose
    private String usage;
    @SerializedName("discount")
    @Expose
    private Double discount;
    @SerializedName("minimumFare")
    @Expose
    private Double minimumFare;
    @SerializedName("paymentType")
    @Expose
    private String paymentType;
    @SerializedName("mode")
    @Expose
    private String mode;
    @SerializedName("photoUrl")
    @Expose
    private String photoUrl;
    @SerializedName("sourceLongitude")
    @Expose
    private String sourceLongitude;
    @SerializedName("total")
    @Expose
    private Double total;
    @SerializedName("exchangeRate")
    @Expose
    private Double exchangeRate;
    @SerializedName("promoCodeId")
    @Expose
    private String promoCodeId;
    @SerializedName("tourId")
    @Expose
    private String tourId;
    @SerializedName("distributionAmount")
    @Expose
    private Double distributionAmount;
    @SerializedName("phoneCode")
    @Expose
    private String phoneCode;
    @SerializedName("perMinuteFare")
    @Expose
    private Double perMinuteFare;
    @SerializedName("promoCode")
    @Expose
    private String promoCode;
    @SerializedName("destinationLatitude")
    @Expose
    private String destinationLatitude;
    @SerializedName("promoDiscount")
    @Expose
    private Double promoDiscount;
    @SerializedName("usedCredits")
    @Expose
    private Double usedCredits;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("usedCount")
    @Expose
    private String usedCount;
    @SerializedName("promoCodeDiscount")
    @Expose
    private String promoCodeDiscount;
    @SerializedName("sourceAddress")
    @Expose
    private String sourceAddress;
    @SerializedName("isInvoice")
    @Expose
    private Boolean isInvoice;
    @SerializedName("securityCode")
    @Expose
    private String securityCode;
    @SerializedName("carTypeId")
    @Expose
    private String carTypeId;
    @SerializedName("userTourId")
    @Expose
    private String userTourId;
    @SerializedName("destinationLongitude")
    @Expose
    private String destinationLongitude;
    @SerializedName("usageCount")
    @Expose
    private String usageCount;
    @SerializedName("initialFare")
    @Expose
    private Double initialFare;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("charges")
    @Expose
    private Double charges;
    @SerializedName("driverId")
    @Expose
    private String driverId;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("promoCodeApplied")
    @Expose
    private Boolean promoCodeApplied;
    @SerializedName("bookingFees")
    @Expose
    private Double bookingFees;
    @SerializedName("passengerId")
    @Expose
    private String passengerId;
    @SerializedName("isFixedFare")
    @Expose
    private Boolean isFixedFare;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("status")
    @Expose
    private String status;

    public Double getCancellationCharges() {
        return cancellationCharges;
    }

    public void setCancellationCharges(Double cancellationCharges) {
        this.cancellationCharges = cancellationCharges;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSourceLatitude() {
        return sourceLatitude;
    }

    public void setSourceLatitude(String sourceLatitude) {
        this.sourceLatitude = sourceLatitude;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Double getPerKmFare() {
        return perKmFare;
    }

    public void setPerKmFare(Double perKmFare) {
        this.perKmFare = perKmFare;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getMinimumFare() {
        return minimumFare;
    }

    public void setMinimumFare(Double minimumFare) {
        this.minimumFare = minimumFare;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getSourceLongitude() {
        return sourceLongitude;
    }

    public void setSourceLongitude(String sourceLongitude) {
        this.sourceLongitude = sourceLongitude;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getPromoCodeId() {
        return promoCodeId;
    }

    public void setPromoCodeId(String promoCodeId) {
        this.promoCodeId = promoCodeId;
    }

    public String getTourId() {
        return tourId;
    }

    public void setTourId(String tourId) {
        this.tourId = tourId;
    }

    public Double getDistributionAmount() {
        return distributionAmount;
    }

    public void setDistributionAmount(Double distributionAmount) {
        this.distributionAmount = distributionAmount;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public Double getPerMinuteFare() {
        return perMinuteFare;
    }

    public void setPerMinuteFare(Double perMinuteFare) {
        this.perMinuteFare = perMinuteFare;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getDestinationLatitude() {
        return destinationLatitude;
    }

    public void setDestinationLatitude(String destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }

    public Double getPromoDiscount() {
        return promoDiscount;
    }

    public void setPromoDiscount(Double promoDiscount) {
        this.promoDiscount = promoDiscount;
    }

    public Double getUsedCredits() {
        return usedCredits;
    }

    public void setUsedCredits(Double usedCredits) {
        this.usedCredits = usedCredits;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(String usedCount) {
        this.usedCount = usedCount;
    }

    public String getPromoCodeDiscount() {
        return promoCodeDiscount;
    }

    public void setPromoCodeDiscount(String promoCodeDiscount) {
        this.promoCodeDiscount = promoCodeDiscount;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public Boolean getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(Boolean isInvoice) {
        this.isInvoice = isInvoice;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(String carTypeId) {
        this.carTypeId = carTypeId;
    }

    public String getUserTourId() {
        return userTourId;
    }

    public void setUserTourId(String userTourId) {
        this.userTourId = userTourId;
    }

    public String getDestinationLongitude() {
        return destinationLongitude;
    }

    public void setDestinationLongitude(String destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
    }

    public String getUsageCount() {
        return usageCount;
    }

    public void setUsageCount(String usageCount) {
        this.usageCount = usageCount;
    }

    public Double getInitialFare() {
        return initialFare;
    }

    public void setInitialFare(Double initialFare) {
        this.initialFare = initialFare;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Double getCharges() {
        return charges;
    }

    public void setCharges(Double charges) {
        this.charges = charges;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getPromoCodeApplied() {
        return promoCodeApplied;
    }

    public void setPromoCodeApplied(Boolean promoCodeApplied) {
        this.promoCodeApplied = promoCodeApplied;
    }

    public Double getBookingFees() {
        return bookingFees;
    }

    public void setBookingFees(Double bookingFees) {
        this.bookingFees = bookingFees;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public Boolean getIsFixedFare() {
        return isFixedFare;
    }

    public void setIsFixedFare(Boolean isFixedFare) {
        this.isFixedFare = isFixedFare;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String makeJson(){
        Gson gson= new Gson();
        String _jsonstring= gson.toJson(this);
        return  _jsonstring;
    }
}

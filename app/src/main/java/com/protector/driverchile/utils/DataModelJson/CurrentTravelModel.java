package com.protector.driverchile.utils.DataModelJson;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*Carlos Duno**/
public class CurrentTravelModel {
    @SerializedName("dateTime")
    @Expose
    private String dateTime;
    @SerializedName("cancellationCharges")
    @Expose
    private Double cancellationCharges;
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
    @SerializedName("perKmFare")
    @Expose
    private Double perKmFare;
    @SerializedName("rating")
    @Expose
    private float rating;
    @SerializedName("discount")
    @Expose
    private Double discount;
    @SerializedName("jobExpireTime")
    @Expose
    private Double jobExpireTime;
    @SerializedName("minimumFare")
    @Expose
    private Double minimumFare;
    @SerializedName("paymentType")
    @Expose
    private String paymentType;
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
    @SerializedName("tourId")
    @Expose
    private String tourId;
    @SerializedName("phoneCode")
    @Expose
    private String phoneCode;
    @SerializedName("perMinuteFare")
    @Expose
    private Double perMinuteFare;
    @SerializedName("tip")
    @Expose
    private Double tip;
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
    @SerializedName("updatedAt")
    @Expose
    private Double updatedAt;
    @SerializedName("finalAmountCollected")
    @Expose
    private Double finalAmountCollected;
    @SerializedName("sourceAddress")
    @Expose
    private String sourceAddress;
    @SerializedName("securityCode")
    @Expose
    private String securityCode;
    @SerializedName("userTourId")
    @Expose
    private String userTourId;
    @SerializedName("destinationLongitude")
    @Expose
    private String destinationLongitude;
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
    @SerializedName("status")
    @Expose
    private String status;
  @SerializedName("distanceToRiderPickup")
    @Expose
    private Double distanceToRiderPickup;
    @SerializedName("durationToRiderPickup")
    @Expose
    private Double durationToRiderPickup;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("riderGender")
    @Expose
    private String riderGender;

    public String getRiderGender() {
        return riderGender;
    }

    public void setRiderGender(String riderGender) {
        this.riderGender = riderGender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }



    public Double getDistanceToRiderPickup() {
        return distanceToRiderPickup;
    }

    public void setDistanceToRiderPickup(Double distanceToRiderPickup) {
        this.distanceToRiderPickup = distanceToRiderPickup;
    }

    public Double getDurationToRiderPickup() {
        return durationToRiderPickup;
    }

    public void setDurationToRiderPickup(Double durationToRiderPickup) {
        this.durationToRiderPickup = durationToRiderPickup;
    }


    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Double getCancellationCharges() {
        return cancellationCharges;
    }

    public void setCancellationCharges(Double cancellationCharges) {
        this.cancellationCharges = cancellationCharges;
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

    public Double getPerKmFare() {
        return perKmFare;
    }

    public void setPerKmFare(Double perKmFare) {
        this.perKmFare = perKmFare;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getJobExpireTime() {
        return jobExpireTime;
    }

    public void setJobExpireTime(Double jobExpireTime) {
        this.jobExpireTime = jobExpireTime;
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

    public String getTourId() {
        return tourId;
    }

    public void setTourId(String tourId) {
        this.tourId = tourId;
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

    public Double getTip() {
        return tip;
    }

    public void setTip(Double tip) {
        this.tip = tip;
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

    public Double getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Double updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Double getFinalAmountCollected() {
        return finalAmountCollected;
    }

    public void setFinalAmountCollected(Double finalAmountCollected) {
        this.finalAmountCollected = finalAmountCollected;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
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

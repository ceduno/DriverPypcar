package com.protector.driverchile.utils.DataModelJson;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Marlon Viana on 26/05/2019
 * @email 92marlonViana@gmail.com
 */
public class TravelInfoModel {
    @SerializedName("destinationAddress")
    @Expose
    private String destinationAddress;
    @SerializedName("passengerRatings")
    @Expose
    private String passengerRatings;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("creditCardProcessingCharges")
    @Expose
    private Double creditCardProcessingCharges;
    @SerializedName("usage")
    @Expose
    private String usage;
    @SerializedName("discount")
    @Expose
    private Double discount;
    @SerializedName("waitingTime")
    @Expose
    private Double waitingTime;
    @SerializedName("subTotal")
    @Expose
    private Double subTotal;
    @SerializedName("mode")
    @Expose
    private String mode;
    @SerializedName("sourceLongitude")
    @Expose
    private String sourceLongitude;
    @SerializedName("institutionAgreement2")
    @Expose
    private Double institutionAgreement2;
    @SerializedName("cashToBeCollected")
    @Expose
    private Double cashToBeCollected;
    @SerializedName("isPromoCodeApplied")
    @Expose
    private Boolean isPromoCodeApplied;
    @SerializedName("driverComments")
    @Expose
    private String driverComments;
    @SerializedName("fine")
    @Expose
    private Double fine;
    @SerializedName("cashCollected")
    @Expose
    private Double cashCollected;
    @SerializedName("promoCodeId")
    @Expose
    private String promoCodeId;
    @SerializedName("exchangeRate")
    @Expose
    private Double exchangeRate;
    @SerializedName("passengerComments")
    @Expose
    private String passengerComments;
    @SerializedName("percentage")
    @Expose
    private Double percentage;
    @SerializedName("usedCredits")
    @Expose
    private Double usedCredits;
    @SerializedName("destinationLatitude")
    @Expose
    private String destinationLatitude;
    @SerializedName("usedCount")
    @Expose
    private String usedCount;
    @SerializedName("phoneNoCode")
    @Expose
    private String phoneNoCode;
    @SerializedName("finalAmountCollected")
    @Expose
    private Double finalAmountCollected;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("charges")
    @Expose
    private Double charges;
    @SerializedName("driverId")
    @Expose
    private String driverId;
    @SerializedName("bookingFees")
    @Expose
    private Double bookingFees;
    @SerializedName("cashNotReceived")
    @Expose
    private Boolean cashNotReceived;
    @SerializedName("isPassengerRated")
    @Expose
    private Boolean isPassengerRated;
    @SerializedName("timeFare")
    @Expose
    private Double timeFare;
    @SerializedName("favouriteDriver")
    @Expose
    private Boolean favouriteDriver;
    @SerializedName("card")
    @Expose
    private Boolean card;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("driverRatings")
    @Expose
    private String driverRatings;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("dateTime")
    @Expose
    private String dateTime;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("tollAmount")
    @Expose
    private Double tollAmount;
    @SerializedName("sourceLatitude")
    @Expose
    private String sourceLatitude;
    @SerializedName("driverAmount")
    @Expose
    private Double driverAmount;
    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("perKmFare")
    @Expose
    private Double perKmFare;
    @SerializedName("phoneNo")
    @Expose
    private String phoneNo;
    @SerializedName("minimumFare")
    @Expose
    private Double minimumFare;
    @SerializedName("duration")
    @Expose
    private Double duration;
    @SerializedName("estimatedDuration")
    @Expose
    private Double estimatedDuration;

    @SerializedName("photoUrl")
    @Expose
    private String photoUrl;
    @SerializedName("distanceFare")
    @Expose
    private Double distanceFare;
    @SerializedName("total")
    @Expose
    private Double total;
    @SerializedName("tourId")
    @Expose
    private String tourId;
    @SerializedName("distributionAmount")
    @Expose
    private Double distributionAmount;
    @SerializedName("isDriverRated")
    @Expose
    private Boolean isDriverRated;
    @SerializedName("perMinuteFare")
    @Expose
    private Double perMinuteFare;
    @SerializedName("promoCode")
    @Expose
    private String promoCode;
    @SerializedName("promoDiscount")
    @Expose
    private Double promoDiscount;
    @SerializedName("paymentStatus")
    @Expose
    private String paymentStatus;
    @SerializedName("promoCodeDiscount")
    @Expose
    private String promoCodeDiscount;
    @SerializedName("sourceAddress")
    @Expose
    private String sourceAddress;
    @SerializedName("paymentMode")
    @Expose
    private String paymentMode;
    @SerializedName("carTypeId")
    @Expose
    private String carTypeId;
    @SerializedName("userTourId")
    @Expose
    private String userTourId;
    @SerializedName("institutionAgreement1")
    @Expose
    private Double institutionAgreement1;
    @SerializedName("destinationLongitude")
    @Expose
    private String destinationLongitude;
    @SerializedName("usageCount")
    @Expose
    private String usageCount;
    @SerializedName("avgSpeed")
    @Expose
    private Double avgSpeed;
    @SerializedName("initialFare")
    @Expose
    private Double initialFare;
    @SerializedName("passengerId")
    @Expose
    private String passengerId;
    @SerializedName("pFirstName")
    @Expose
    private String pFirstName;
    @SerializedName("pLastName")
    @Expose
    private String pLastName;
    @SerializedName("pPhotoUrl")
    @Expose
    private String pPhotoUrl;
    @SerializedName("riderGender")
    @Expose
    private String riderGender;

    public String getRiderGender() {
        return riderGender;
    }

    public void setRiderGender(String riderGender) {
        this.riderGender = riderGender;
    }

    public TravelInfoModel() {
    }

    public Double getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(Double estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public String getpPhotoUrl() {
        return pPhotoUrl;
    }

    public void setpPhotoUrl(String pPhotoUrl) {
        this.pPhotoUrl = pPhotoUrl;
    }

    public String getpFirstName() {
        return pFirstName;
    }

    public void setpFirstName(String pFirstName) {
        this.pFirstName = pFirstName;
    }

    public String getpLastName() {
        return pLastName;
    }

    public void setpLastName(String pLastName) {
        this.pLastName = pLastName;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getPassengerRatings() {
        return passengerRatings;
    }

    public void setPassengerRatings(String passengerRatings) {
        this.passengerRatings = passengerRatings;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Double getCreditCardProcessingCharges() {
        return creditCardProcessingCharges;
    }

    public void setCreditCardProcessingCharges(Double creditCardProcessingCharges) {
        this.creditCardProcessingCharges = creditCardProcessingCharges;
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

    public Double getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(Double waitingTime) {
        this.waitingTime = waitingTime;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getSourceLongitude() {
        return sourceLongitude;
    }

    public void setSourceLongitude(String sourceLongitude) {
        this.sourceLongitude = sourceLongitude;
    }

    public Double getInstitutionAgreement2() {
        return institutionAgreement2;
    }

    public void setInstitutionAgreement2(Double institutionAgreement2) {
        this.institutionAgreement2 = institutionAgreement2;
    }

    public Double getCashToBeCollected() {
        return cashToBeCollected;
    }

    public void setCashToBeCollected(Double cashToBeCollected) {
        this.cashToBeCollected = cashToBeCollected;
    }

    public Boolean getIsPromoCodeApplied() {
        return isPromoCodeApplied;
    }

    public void setIsPromoCodeApplied(Boolean isPromoCodeApplied) {
        this.isPromoCodeApplied = isPromoCodeApplied;
    }

    public String getDriverComments() {
        return driverComments;
    }

    public void setDriverComments(String driverComments) {
        this.driverComments = driverComments;
    }

    public Double getFine() {
        return fine;
    }

    public void setFine(Double fine) {
        this.fine = fine;
    }

    public Double getCashCollected() {
        return cashCollected;
    }

    public void setCashCollected(Double cashCollected) {
        this.cashCollected = cashCollected;
    }

    public String getPromoCodeId() {
        return promoCodeId;
    }

    public void setPromoCodeId(String promoCodeId) {
        this.promoCodeId = promoCodeId;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getPassengerComments() {
        return passengerComments;
    }

    public void setPassengerComments(String passengerComments) {
        this.passengerComments = passengerComments;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Double getUsedCredits() {
        return usedCredits;
    }

    public void setUsedCredits(Double usedCredits) {
        this.usedCredits = usedCredits;
    }

    public String getDestinationLatitude() {
        return destinationLatitude;
    }

    public void setDestinationLatitude(String destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }

    public String getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(String usedCount) {
        this.usedCount = usedCount;
    }

    public String getPhoneNoCode() {
        return phoneNoCode;
    }

    public void setPhoneNoCode(String phoneNoCode) {
        this.phoneNoCode = phoneNoCode;
    }

    public Double getFinalAmountCollected() {
        return finalAmountCollected;
    }

    public void setFinalAmountCollected(Double finalAmountCollected) {
        this.finalAmountCollected = finalAmountCollected;
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

    public Double getBookingFees() {
        return bookingFees;
    }

    public void setBookingFees(Double bookingFees) {
        this.bookingFees = bookingFees;
    }

    public Boolean getCashNotReceived() {
        return cashNotReceived;
    }

    public void setCashNotReceived(Boolean cashNotReceived) {
        this.cashNotReceived = cashNotReceived;
    }

    public Boolean getIsPassengerRated() {
        return isPassengerRated;
    }

    public void setIsPassengerRated(Boolean isPassengerRated) {
        this.isPassengerRated = isPassengerRated;
    }

    public Double getTimeFare() {
        return timeFare;
    }

    public void setTimeFare(Double timeFare) {
        this.timeFare = timeFare;
    }

    public Boolean getFavouriteDriver() {
        return favouriteDriver;
    }

    public void setFavouriteDriver(Boolean favouriteDriver) {
        this.favouriteDriver = favouriteDriver;
    }

    public Boolean getCard() {
        return card;
    }

    public void setCard(Boolean card) {
        this.card = card;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDriverRatings() {
        return driverRatings;
    }

    public void setDriverRatings(String driverRatings) {
        this.driverRatings = driverRatings;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Double getTollAmount() {
        return tollAmount;
    }

    public void setTollAmount(Double tollAmount) {
        this.tollAmount = tollAmount;
    }

    public String getSourceLatitude() {
        return sourceLatitude;
    }

    public void setSourceLatitude(String sourceLatitude) {
        this.sourceLatitude = sourceLatitude;
    }

    public Double getDriverAmount() {
        return driverAmount;
    }

    public void setDriverAmount(Double driverAmount) {
        this.driverAmount = driverAmount;
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Double getMinimumFare() {
        return minimumFare;
    }

    public void setMinimumFare(Double minimumFare) {
        this.minimumFare = minimumFare;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Double getDistanceFare() {
        return distanceFare;
    }

    public void setDistanceFare(Double distanceFare) {
        this.distanceFare = distanceFare;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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

    public Boolean getIsDriverRated() {
        return isDriverRated;
    }

    public void setIsDriverRated(Boolean isDriverRated) {
        this.isDriverRated = isDriverRated;
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

    public Double getPromoDiscount() {
        return promoDiscount;
    }

    public void setPromoDiscount(Double promoDiscount) {
        this.promoDiscount = promoDiscount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
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

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
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

    public Double getInstitutionAgreement1() {
        return institutionAgreement1;
    }

    public void setInstitutionAgreement1(Double institutionAgreement1) {
        this.institutionAgreement1 = institutionAgreement1;
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

    public Double getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(Double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public Double getInitialFare() {
        return initialFare;
    }

    public void setInitialFare(Double initialFare) {
        this.initialFare = initialFare;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public String makeJson(){
        Gson gson= new Gson();
        String _jsonstring= gson.toJson(this);
        return  _jsonstring;
    }
}

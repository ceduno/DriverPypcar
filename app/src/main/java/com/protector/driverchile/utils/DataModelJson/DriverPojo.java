package com.protector.driverchile.utils.DataModelJson;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DriverPojo{
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
    @SerializedName("carDriversModel")
    @Expose
    private Object carDriversModel;
    @SerializedName("carModel")
    @Expose
    private CarModel carModel;
    @SerializedName("driverBankDetails")
    @Expose
    private DriverBankDetails driverBankDetails;
    @SerializedName("drivingLicenseModel")
    @Expose
    private DrivingLicenseModel drivingLicenseModel;
    @SerializedName("userModel")
    @Expose
    private Object userModel;
    @SerializedName("userProfileModel")
    @Expose
    private Object userProfileModel;
    @SerializedName("apiSessionKey")
    @Expose
    private String apiSessionKey;
    @SerializedName("applicationStatus")
    @Expose
    private String applicationStatus;
    @SerializedName("billAddressLineOne")
    @Expose
    private Object billAddressLineOne;
    @SerializedName("billAddressLineTwo")
    @Expose
    private Object billAddressLineTwo;
    @SerializedName("billCityId")
    @Expose
    private Object billCityId;
    @SerializedName("billCountryId")
    @Expose
    private Object billCountryId;
    @SerializedName("billStateId")
    @Expose
    private Object billStateId;
    @SerializedName("billZipCode")
    @Expose
    private Object billZipCode;
    @SerializedName("codeToRefer")
    @Expose
    private String codeToRefer;
    @SerializedName("companyAddress")
    @Expose
    private Object companyAddress;
    @SerializedName("companyName")
    @Expose
    private Object companyName;
    @SerializedName("docNumber")
    @Expose
    private Object docNumber;
    @SerializedName("docType")
    @Expose
    private Object docType;
    @SerializedName("drivingLicense")
    @Expose
    private Object drivingLicense;
    @SerializedName("drivingLicensephoto")
    @Expose
    private Object drivingLicensephoto;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("hearFromUs")
    @Expose
    private Object hearFromUs;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("mailAddressLineOne")
    @Expose
    private String mailAddressLineOne;
    @SerializedName("mailAddressLineTwo")
    @Expose
    private Object mailAddressLineTwo;
    @SerializedName("mailCityId")
    @Expose
    private Object mailCityId;
    @SerializedName("mailCountryId")
    @Expose
    private String mailCountryId;
    @SerializedName("mailStateId")
    @Expose
    private String mailStateId;
    @SerializedName("mailZipCode")
    @Expose
    private Object mailZipCode;
    @SerializedName("make")
    @Expose
    private Object make;
    @SerializedName("modelName")
    @Expose
    private Object modelName;
    @SerializedName("ownerContractUrl")
    @Expose
    private Object ownerContractUrl;
    @SerializedName("ownerIdUrl")
    @Expose
    private Object ownerIdUrl;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("phoneNo")
    @Expose
    private String phoneNo;
    @SerializedName("phoneNoCode")
    @Expose
    private String phoneNoCode;
    @SerializedName("photoUrl")
    @Expose
    private String photoUrl;
    @SerializedName("referralCode")
    @Expose
    private String referralCode;
    @SerializedName("serviceType")
    @Expose
    private Object serviceType;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("userInfoId")
    @Expose
    private String userInfoId;
    @SerializedName("userRole")
    @Expose
    private String userRole;
    @SerializedName("verificationCode")
    @Expose
    private Object verificationCode;
    @SerializedName("bankInfo")
    @Expose
    private Object bankInfo;
    @SerializedName("cardAvailable")
    @Expose
    private Boolean cardAvailable;
    @SerializedName("documentVerified")
    @Expose
    private Boolean documentVerified;
    @SerializedName("badgeCount")
    @Expose
    private Integer badgeCount;
    @SerializedName("companyDriver")
    @Expose
    private Integer companyDriver;
    @SerializedName("joiningDate")
    @Expose
    private Integer joiningDate;
    @SerializedName("roleId")
    @Expose
    private String roleId;
    @SerializedName("totalIncomes")
    @Expose
    private Integer totalIncomes;
    @SerializedName("totalDistance")
    @Expose
    private Integer totalDistance;
    @SerializedName("loggedInAt")
    @Expose
    private Integer loggedInAt;
    @SerializedName("totalEndedTrips")
    @Expose
    private Integer totalEndedTrips;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("dniUrl")
    @Expose
    private String dniUrl;
    @SerializedName("dniUrlBack")
    @Expose
    private Object dniUrlBack;
    @SerializedName("cvUrl")
    @Expose
    private String cvUrl;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("notificationStatus")
    @Expose
    private Boolean notificationStatus;
    @SerializedName("acceptProcar")
    @Expose
    private Boolean acceptProcar;
    @SerializedName("docNumTypeExist")
    @Expose
    private Boolean docNumTypeExist;
    @SerializedName("deleted")
    @Expose
    private Boolean deleted;
    @SerializedName("sameAsMailing")
    @Expose
    private Boolean sameAsMailing;
    @SerializedName("onDuty")
    @Expose
    private Boolean onDuty;
    @SerializedName("verified")
    @Expose
    private Boolean verified;
    @SerializedName("darktheme")
    @Expose
    private Boolean darktheme;

    public Boolean getDarktheme() {
        return darktheme;
    }

    public void setDarktheme(Boolean darktheme) {
        this.darktheme = darktheme;
    }

    public Boolean getAcceptProcar() {
        return acceptProcar;
    }

    public void setAcceptProcar(Boolean acceptProcar) {
        this.acceptProcar = acceptProcar;
    }
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

    public Object getCarDriversModel() {
        return carDriversModel;
    }

    public void setCarDriversModel(Object carDriversModel) {
        this.carDriversModel = carDriversModel;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public DriverBankDetails getDriverBankDetails() {
        return driverBankDetails;
    }

    public void setDriverBankDetails(DriverBankDetails driverBankDetails) {
        this.driverBankDetails = driverBankDetails;
    }

    public DrivingLicenseModel getDrivingLicenseModel() {
        return drivingLicenseModel;
    }

    public void setDrivingLicenseModel(DrivingLicenseModel drivingLicenseModel) {
        this.drivingLicenseModel = drivingLicenseModel;
    }

    public Object getUserModel() {
        return userModel;
    }

    public void setUserModel(Object userModel) {
        this.userModel = userModel;
    }

    public Object getUserProfileModel() {
        return userProfileModel;
    }

    public void setUserProfileModel(Object userProfileModel) {
        this.userProfileModel = userProfileModel;
    }

    public String getApiSessionKey() {
        return apiSessionKey;
    }

    public void setApiSessionKey(String apiSessionKey) {
        this.apiSessionKey = apiSessionKey;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public Object getBillAddressLineOne() {
        return billAddressLineOne;
    }

    public void setBillAddressLineOne(Object billAddressLineOne) {
        this.billAddressLineOne = billAddressLineOne;
    }

    public Object getBillAddressLineTwo() {
        return billAddressLineTwo;
    }

    public void setBillAddressLineTwo(Object billAddressLineTwo) {
        this.billAddressLineTwo = billAddressLineTwo;
    }

    public Object getBillCityId() {
        return billCityId;
    }

    public void setBillCityId(Object billCityId) {
        this.billCityId = billCityId;
    }

    public Object getBillCountryId() {
        return billCountryId;
    }

    public void setBillCountryId(Object billCountryId) {
        this.billCountryId = billCountryId;
    }

    public Object getBillStateId() {
        return billStateId;
    }

    public void setBillStateId(Object billStateId) {
        this.billStateId = billStateId;
    }

    public Object getBillZipCode() {
        return billZipCode;
    }

    public void setBillZipCode(Object billZipCode) {
        this.billZipCode = billZipCode;
    }

    public String getCodeToRefer() {
        return codeToRefer;
    }

    public void setCodeToRefer(String codeToRefer) {
        this.codeToRefer = codeToRefer;
    }

    public Object getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(Object companyAddress) {
        this.companyAddress = companyAddress;
    }

    public Object getCompanyName() {
        return companyName;
    }

    public void setCompanyName(Object companyName) {
        this.companyName = companyName;
    }

    public Object getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(Object docNumber) {
        this.docNumber = docNumber;
    }

    public Object getDocType() {
        return docType;
    }

    public void setDocType(Object docType) {
        this.docType = docType;
    }

    public Object getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(Object drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public Object getDrivingLicensephoto() {
        return drivingLicensephoto;
    }

    public void setDrivingLicensephoto(Object drivingLicensephoto) {
        this.drivingLicensephoto = drivingLicensephoto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Object getHearFromUs() {
        return hearFromUs;
    }

    public void setHearFromUs(Object hearFromUs) {
        this.hearFromUs = hearFromUs;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMailAddressLineOne() {
        return mailAddressLineOne;
    }

    public void setMailAddressLineOne(String mailAddressLineOne) {
        this.mailAddressLineOne = mailAddressLineOne;
    }

    public Object getMailAddressLineTwo() {
        return mailAddressLineTwo;
    }

    public void setMailAddressLineTwo(Object mailAddressLineTwo) {
        this.mailAddressLineTwo = mailAddressLineTwo;
    }

    public Object getMailCityId() {
        return mailCityId;
    }

    public void setMailCityId(Object mailCityId) {
        this.mailCityId = mailCityId;
    }

    public String getMailCountryId() {
        return mailCountryId;
    }

    public void setMailCountryId(String mailCountryId) {
        this.mailCountryId = mailCountryId;
    }

    public String getMailStateId() {
        return mailStateId;
    }

    public void setMailStateId(String mailStateId) {
        this.mailStateId = mailStateId;
    }

    public Object getMailZipCode() {
        return mailZipCode;
    }

    public void setMailZipCode(Object mailZipCode) {
        this.mailZipCode = mailZipCode;
    }

    public Object getMake() {
        return make;
    }

    public void setMake(Object make) {
        this.make = make;
    }

    public Object getModelName() {
        return modelName;
    }

    public void setModelName(Object modelName) {
        this.modelName = modelName;
    }

    public Object getOwnerContractUrl() {
        return ownerContractUrl;
    }

    public void setOwnerContractUrl(Object ownerContractUrl) {
        this.ownerContractUrl = ownerContractUrl;
    }

    public Object getOwnerIdUrl() {
        return ownerIdUrl;
    }

    public void setOwnerIdUrl(Object ownerIdUrl) {
        this.ownerIdUrl = ownerIdUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPhoneNoCode() {
        return phoneNoCode;
    }

    public void setPhoneNoCode(String phoneNoCode) {
        this.phoneNoCode = phoneNoCode;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public Object getServiceType() {
        return serviceType;
    }

    public void setServiceType(Object serviceType) {
        this.serviceType = serviceType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Object getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(Object verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Object getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(Object bankInfo) {
        this.bankInfo = bankInfo;
    }

    public Boolean getCardAvailable() {
        return cardAvailable;
    }

    public void setCardAvailable(Boolean cardAvailable) {
        this.cardAvailable = cardAvailable;
    }

    public Boolean getDocumentVerified() {
        return documentVerified;
    }

    public void setDocumentVerified(Boolean documentVerified) {
        this.documentVerified = documentVerified;
    }

    public Integer getBadgeCount() {
        return badgeCount;
    }

    public void setBadgeCount(Integer badgeCount) {
        this.badgeCount = badgeCount;
    }

    public Integer getCompanyDriver() {
        return companyDriver;
    }

    public void setCompanyDriver(Integer companyDriver) {
        this.companyDriver = companyDriver;
    }

    public Integer getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Integer joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Integer getTotalIncomes() {
        return totalIncomes;
    }

    public void setTotalIncomes(Integer totalIncomes) {
        this.totalIncomes = totalIncomes;
    }

    public Integer getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Integer totalDistance) {
        this.totalDistance = totalDistance;
    }

    public Integer getLoggedInAt() {
        return loggedInAt;
    }

    public void setLoggedInAt(Integer loggedInAt) {
        this.loggedInAt = loggedInAt;
    }

    public Integer getTotalEndedTrips() {
        return totalEndedTrips;
    }

    public void setTotalEndedTrips(Integer totalEndedTrips) {
        this.totalEndedTrips = totalEndedTrips;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getDniUrl() {
        return dniUrl;
    }

    public void setDniUrl(String dniUrl) {
        this.dniUrl = dniUrl;
    }

    public Object getDniUrlBack() {
        return dniUrlBack;
    }

    public void setDniUrlBack(Object dniUrlBack) {
        this.dniUrlBack = dniUrlBack;
    }

    public String getCvUrl() {
        return cvUrl;
    }

    public void setCvUrl(String cvUrl) {
        this.cvUrl = cvUrl;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(Boolean notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

    public Boolean getDocNumTypeExist() {
        return docNumTypeExist;
    }

    public void setDocNumTypeExist(Boolean docNumTypeExist) {
        this.docNumTypeExist = docNumTypeExist;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getSameAsMailing() {
        return sameAsMailing;
    }

    public void setSameAsMailing(Boolean sameAsMailing) {
        this.sameAsMailing = sameAsMailing;
    }

    public Boolean getOnDuty() {
        return onDuty;
    }

    public void setOnDuty(Boolean onDuty) {
        this.onDuty = onDuty;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public String makeJson(){
        Gson gson= new Gson();
        String _jsonstring= gson.toJson(this);
        return  _jsonstring;
    }

}

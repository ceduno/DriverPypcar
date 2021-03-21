package com.protector.driverchile.utils.DataModelJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Marlon Viana on 22/03/2019
 * @email 92marlonViana@gmail.com
 */
public class DriverBankDetails {
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
    @SerializedName("driverBankDetailsId")
    @Expose
    private String driverBankDetailsId;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("bankName")
    @Expose
    private String bankName;
    @SerializedName("accountNumber")
    @Expose
    private String accountNumber;
    @SerializedName("accountName")
    @Expose
    private Object accountName;
    @SerializedName("routingNumber")
    @Expose
    private String routingNumber;
    @SerializedName("type")
    @Expose
    private String type;

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

    public String getDriverBankDetailsId() {
        return driverBankDetailsId;
    }

    public void setDriverBankDetailsId(String driverBankDetailsId) {
        this.driverBankDetailsId = driverBankDetailsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Object getAccountName() {
        return accountName;
    }

    public void setAccountName(Object accountName) {
        this.accountName = accountName;
    }

    public String getRoutingNumber() {
        return routingNumber;
    }

    public void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

package com.protector.driverchile.utils.DataModelJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
//{"tourNumber": "3144",
//            "driverAmount": 3254,
//            "discountRateByOwed": 0,
//            "billStatus": "open",
//            "invoicePaymentDate": 0,
//            "discountByAmountOwed": 0,
//            "tourDate": 1580480917183}
public class ToursDoneModel {
    @SerializedName("tourNumber")
    @Expose
    private String tourNumber;
    @SerializedName("driverAmount")
    @Expose
    private Double driverAmount;
    @SerializedName("discountRateByOwed")
    @Expose
    private Double discountRateByOwed;
    @SerializedName("totalPayFor")
    @Expose
    private Double totalPayFor;
    @SerializedName("billStatus")
    @Expose
    private String billStatus;
    @SerializedName("invoicePaymentDate")
    @Expose
    private Double invoicePaymentDate;
    @SerializedName("discountByAmountOwed")
    @Expose
    private Double discountByAmountOwed;
    @SerializedName("tourDate")
    @Expose
    private Long tourDate;

    public Double getTotalPayFor() {
        return totalPayFor;
    }

    public void setTotalPayFor(Double totalPayFor) {
        this.totalPayFor = totalPayFor;
    }

    public String getTourNumber() {
        return tourNumber;
    }

    public void setTourNumber(String tourNumber) {
        this.tourNumber = tourNumber;
    }

    public Double getDriverAmount() {
        return driverAmount;
    }

    public void setDriverAmount(Double driverAmount) {
        this.driverAmount = driverAmount;
    }

    public Double getDiscountRateByOwed() {
        return discountRateByOwed;
    }

    public void setDiscountRateByOwed(Double discountRateByOwed) {
        this.discountRateByOwed = discountRateByOwed;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public Double getInvoicePaymentDate() {
        return invoicePaymentDate;
    }

    public void setInvoicePaymentDate(Double invoicePaymentDate) {
        this.invoicePaymentDate = invoicePaymentDate;
    }

    public Double getDiscountByAmountOwed() {
        return discountByAmountOwed;
    }

    public void setDiscountByAmountOwed(Double discountByAmountOwed) {
        this.discountByAmountOwed = discountByAmountOwed;
    }

    public Long getTourDate() {
        return tourDate;
    }

    public void setTourDate(Long tourDate) {
        this.tourDate = tourDate;
    }
}

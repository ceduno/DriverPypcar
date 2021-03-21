package com.protector.driverchile.utils.DataModelJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Marlon Viana on 11/06/2019
 * @email 92marlonViana@gmail.com
 */
public class NotificationModel {
    @SerializedName("notificationMap")
    @Expose
    private List<NotificationMap> notificationMap = null;
    @SerializedName("totalPages")
    @Expose
    private Double totalPages;

    public List<NotificationMap> getNotificationMap() {
        return notificationMap;
    }

    public void setNotificationMap(List<NotificationMap> notificationMap) {
        this.notificationMap = notificationMap;
    }

    public Double getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Double totalPages) {
        this.totalPages = totalPages;
    }
}

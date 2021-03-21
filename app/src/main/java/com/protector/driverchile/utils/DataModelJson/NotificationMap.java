package com.protector.driverchile.utils.DataModelJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Marlon Viana on 11/06/2019
 * @email 92marlonViana@gmail.com
 */
public class NotificationMap {
    @SerializedName("createdAt")
    @Expose
    private Long createdAt;
    @SerializedName("apnsNotificationMessageId")
    @Expose
    private String apnsNotificationMessageId;
    @SerializedName("message")
    @Expose
    private String message;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    private String messageType;

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getApnsNotificationMessageId() {
        return apnsNotificationMessageId;
    }

    public void setApnsNotificationMessageId(String apnsNotificationMessageId) {
        this.apnsNotificationMessageId = apnsNotificationMessageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

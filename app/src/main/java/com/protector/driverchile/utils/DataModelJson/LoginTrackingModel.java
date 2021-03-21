package com.protector.driverchile.utils.DataModelJson;

public class LoginTrackingModel {
    private String userConnectionId;
    private String queue;

    public LoginTrackingModel() {
    }

    public String getUserConnectionId() {
        return userConnectionId;
    }

    public void setUserConnectionId(String userConnectionId) {
        this.userConnectionId = userConnectionId;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }
}

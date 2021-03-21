package com.protector.driverchile.utils.DataModelJson;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**

 */
public class VersionApp {
    @SerializedName("resultCode")
    @Expose
    private String resultCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("appLink")
    @Expose
    private String appLink;
    @SerializedName("releaseNote")
    @Expose
    private String releaseNote;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAppLink() {
        return appLink;
    }

    public void setAppLink(String appLink) {
        this.appLink = appLink;
    }

    public String getReleaseNote() {
        return releaseNote;
    }

    public void setReleaseNote(String releaseNote) {
        this.releaseNote = releaseNote;
    }

    public String makeJson(){
        Gson gson= new Gson();
        String _jsonstring= gson.toJson(this);
        return  _jsonstring;
    }
}

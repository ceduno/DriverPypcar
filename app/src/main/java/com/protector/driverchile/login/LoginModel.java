package com.protector.driverchile.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**

 */
public class LoginModel {
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String pass;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}

package com.protector.driverchile.login;

/**

 */
public interface LoginEventView {
    void requestEmail();
    void requestPass();
    void enableButtonLogin(boolean ban);
    void goToRegisterDriver();
    void goToHome();
    void hideKeyBoard();
    void showPassword();
    void colorEmail(boolean ban);
    void colorPass(boolean ban);
    void showDialgoInfo(String title,String info);
    void showDialgoAutheb(String email,String apiKey);
    void showDialgoRecoveryPass();
    void goToRegistry();
}

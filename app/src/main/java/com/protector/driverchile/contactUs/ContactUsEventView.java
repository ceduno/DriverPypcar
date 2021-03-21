package com.protector.driverchile.contactUs;

import com.protector.driverchile.utils.DataModelJson.ContactModel;

public interface ContactUsEventView {

    void showLoad(boolean ban);
    void showReload(boolean ban);
    void showContect(ContactModel contactModel);
}

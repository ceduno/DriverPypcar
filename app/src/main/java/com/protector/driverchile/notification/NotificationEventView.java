package com.protector.driverchile.notification;

import java.util.List;

/**
 * @author Marlon Viana on 11/06/2019
 * @email 92marlonViana@gmail.com
 */
public interface NotificationEventView {
    void showLoad(boolean ban);
    void showReload(boolean ban);
    void showList(List list);
    void reloadList();
}

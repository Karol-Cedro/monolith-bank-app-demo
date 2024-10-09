package com.kcedro.monolithbankapp.service;

import static java.lang.Thread.sleep;

@org.springframework.stereotype.Service
public class UserOperationsService {

    private final UserSessionService userSessionService;

    private final NotificationService notificationService;

    public UserOperationsService(UserSessionService userSessionService, NotificationService notificationService) {
        this.userSessionService = userSessionService;
        this.notificationService = notificationService;
    }

    public void login () throws InterruptedException {
        sleep(1000); // time needed to provide logging credentials
        userSessionService.createSession();
        notificationService.sendSmsNotificationWithPIN();
    }

    public void editUserData () throws InterruptedException {
        if(userSessionService.isUserLoggedIn()) {
            sleep(20000); // time needed to make some changes in profile
            notificationService.sendSmsNotificationWithPIN();
        }
    }
}

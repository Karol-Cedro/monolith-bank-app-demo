package com.kcedro.monolithbankapp.service;

import org.springframework.stereotype.Service;

import static java.lang.Thread.sleep;

@Service
public class TransactionManagementService {

    private final UserSessionService userSessionService;
    private final NotificationService notificationService;

    public TransactionManagementService(UserSessionService userSessionService, NotificationService notificationService) {
        this.userSessionService = userSessionService;
        this.notificationService = notificationService;
    }

    public void transfer() throws InterruptedException {
        if (userSessionService.isUserLoggedIn()){
            sleep(5000); //time needed to fulfill the transfer details
            notificationService.sendSmsNotificationWithPIN();
        }
    }
}

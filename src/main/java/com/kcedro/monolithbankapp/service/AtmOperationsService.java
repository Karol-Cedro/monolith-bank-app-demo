package com.kcedro.monolithbankapp.service;

import org.springframework.stereotype.Service;

import static java.lang.Thread.sleep;

@Service
public class AtmOperationsService {

    private final NotificationService notificationService;

    public AtmOperationsService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void withdraw() throws InterruptedException {
        notificationService.sendSmsNotificationWithPIN();
        sleep(5000);
    }

    public void deposit() throws InterruptedException {
        notificationService.sendSmsNotificationWithPIN();
        sleep(5000);
    }
}

package com.kcedro.monolithbankapp.service;

import org.springframework.stereotype.Service;

import static java.lang.Thread.sleep;

@Service
public class NotificationService {

    public void sendSmsNotificationWithPIN() throws InterruptedException {
        sleep(500);
    }
}

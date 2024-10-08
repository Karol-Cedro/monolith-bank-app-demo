package com.kcedro.monolithbankapp.service;

import org.springframework.stereotype.Service;

import static java.lang.Thread.sleep;

@Service
public class UserSessionService {

    public void createSession() throws InterruptedException {
        sleep(200);
    }

    public boolean isUserLoggedIn() throws InterruptedException {
        sleep(100);
        return true;
    }

}

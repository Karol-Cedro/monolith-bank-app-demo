package com.kcedro.monolithbankapp.controller;

import com.kcedro.monolithbankapp.service.UserOperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/bank-demo-app")
public class UserOperationController {

    UserOperationService userOperationService;

    public UserOperationController(UserOperationService service) {
        this.userOperationService = service;
    }

    @PostMapping("/login")
    public ResponseEntity<String> auth() throws InterruptedException {
        userOperationService.login();
        return ResponseEntity.ok("logging in ...");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register() throws InterruptedException {
        this.userOperationService.register();
        return ResponseEntity.ok("register new user ...");
    }

    @PostMapping("/edit-user-data")
    public ResponseEntity<String> editUserData() throws InterruptedException {
        this.userOperationService.editUserData();
        return ResponseEntity.ok("edit user data ...");
    }
}

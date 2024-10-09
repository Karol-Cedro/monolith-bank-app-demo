package com.kcedro.monolithbankapp.controller;

import com.kcedro.monolithbankapp.service.UserOperationsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserOperationController {

    UserOperationsService userOperationsService;

    public UserOperationController(UserOperationsService service) {
        this.userOperationsService = service;
    }

    @PostMapping("/login")
    public ResponseEntity<String> auth() throws InterruptedException {
        userOperationsService.login();
        return ResponseEntity.ok("logged in");
    }


    @PostMapping("/edit-user-data")
    public ResponseEntity<String> editUserData() throws InterruptedException {
        this.userOperationsService.editUserData();
        return ResponseEntity.ok("user data edited");
    }
}

package com.kcedro.monolithbankapp.controller;

import com.kcedro.monolithbankapp.service.TransactionManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionManagementController {

    TransactionManagementService transactionManagementService;

    public TransactionManagementController(TransactionManagementService transactionManagementService) {
        this.transactionManagementService = transactionManagementService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer() throws InterruptedException {
        transactionManagementService.transfer();
        return ResponseEntity.ok("transfer of funds completed");
    }

}

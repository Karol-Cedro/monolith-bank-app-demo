package com.kcedro.monolithbankapp.controller;

import com.kcedro.monolithbankapp.service.TransactionManagementService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionManagementController {

    TransactionManagementService transactionManagementService;

    public TransactionManagementController(TransactionManagementService transactionManagementService) {
        this.transactionManagementService = transactionManagementService;
    }
}

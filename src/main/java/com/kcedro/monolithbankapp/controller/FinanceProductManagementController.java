package com.kcedro.monolithbankapp.controller;

import com.kcedro.monolithbankapp.service.FinanceProductManagementService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FinanceProductManagementController {

    FinanceProductManagementService financeProductManagementService;

    public FinanceProductManagementController(FinanceProductManagementService financeProductManagementService) {
        this.financeProductManagementService = financeProductManagementService;
    }
}

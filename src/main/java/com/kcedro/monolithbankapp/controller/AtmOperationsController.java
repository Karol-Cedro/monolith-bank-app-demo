package com.kcedro.monolithbankapp.controller;

import com.kcedro.monolithbankapp.service.AtmOperationsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AtmOperationsController {

    AtmOperationsService atmOperationsService;

    public AtmOperationsController(AtmOperationsService atmOperationsService) {
        this.atmOperationsService = atmOperationsService;
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw() throws InterruptedException {
        atmOperationsService.withdraw();
        return ResponseEntity.ok("withdraw of funds completed");
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit() throws InterruptedException {
        atmOperationsService.deposit();
        return ResponseEntity.ok("deposit od funds completed");
    }
}

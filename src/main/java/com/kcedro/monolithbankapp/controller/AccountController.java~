package com.kcedro.accounts.controller;

import com.kcedro.accounts.constants.AccountConstants;
import com.kcedro.accounts.dto.AccountBalanceDto;
import com.kcedro.accounts.dto.AccountDto;
import com.kcedro.accounts.dto.ResponseDto;
import com.kcedro.accounts.service.IAccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/account", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountController {

    private IAccountService accountsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody AccountDto accountDto) {
        accountsService.createAccount(accountDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @GetMapping("/get-balance")
    public ResponseEntity<Long> fetchAccountsBalance(@RequestParam Long accountId) {
        Long accountBalance = accountsService.fetchAccountBalance(accountId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountBalance);
    }

    @PutMapping("/update-balance")
    public ResponseEntity<ResponseDto> updateAccountBalance(@Valid @RequestBody AccountBalanceDto accountBalanceDto) {
        boolean isUpdated = accountsService.updateAccountBalance(accountBalanceDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam Long accountId) {
        boolean isDeleted = accountsService.deleteAccount(accountId);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_DELETE));
        }
    }
}

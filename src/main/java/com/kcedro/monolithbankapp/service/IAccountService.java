package com.kcedro.monolithbankapp.service;

import com.kcedro.monolithbankapp.dto.AccountBalanceDto;
import com.kcedro.monolithbankapp.dto.AccountDto;

public interface IAccountService {


    void createAccount(AccountDto accountDto);

    Long fetchAccountBalance(Long accountId);

    boolean updateAccountBalance(AccountBalanceDto accountBalanceDto);

    boolean deleteAccount(Long accountId);
}

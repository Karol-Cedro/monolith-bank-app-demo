package com.kcedro.monolithbankapp.mapper;

import com.kcedro.monolithbankapp.dto.AccountDto;
import com.kcedro.monolithbankapp.entity.Account;

public class AccountMapper {

    public static Account mapToAccounts(AccountDto accountDto, Account account) {
        account.setEmail(accountDto.getEmail());
        account.setName(accountDto.getName());
        account.setSurname(accountDto.getSurname());
        account.setMobileNumber(accountDto.getMobileNumber());
        return account;
    }
}

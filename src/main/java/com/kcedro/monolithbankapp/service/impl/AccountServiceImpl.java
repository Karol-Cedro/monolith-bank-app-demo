package com.kcedro.monolithbankapp.service.impl;

import com.kcedro.monolithbankapp.dto.AccountBalanceDto;
import com.kcedro.monolithbankapp.dto.AccountDto;
import com.kcedro.monolithbankapp.entity.Account;
import com.kcedro.monolithbankapp.exception.AccountAlreadyExistsException;
import com.kcedro.monolithbankapp.exception.ResourceNotFoundException;
import com.kcedro.monolithbankapp.mapper.AccountMapper;
import com.kcedro.monolithbankapp.repository.AccountRepository;
import com.kcedro.monolithbankapp.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountRepository accountRepository;


    @Override
    public void createAccount(AccountDto accountDto) {
        Optional<Account> optionalAccount = accountRepository.findByMobileNumber(accountDto.getMobileNumber());
        if (optionalAccount.isPresent()) {
            throw new AccountAlreadyExistsException("Account already exists with given mobile number " + accountDto.getMobileNumber());
        }
        accountRepository.save(createNewAccount(accountDto));
    }

    private Account createNewAccount(AccountDto accountDto) {
        Account newAccount = AccountMapper.mapToAccounts(accountDto, new Account());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setBalance(0L);
        return newAccount;
    }

    @Override
    public Long fetchAccountBalance(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new ResourceNotFoundException("Account", "accountId", accountId.toString())
        );
        return account.getBalance();
    }

    @Override
    public boolean updateAccountBalance(AccountBalanceDto accountBalanceDto) {
        Account account = accountRepository.findById(accountBalanceDto.getAccountId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "accountId", accountBalanceDto.getAccountId().toString())
        );
        account.setBalance(accountBalanceDto.getBalance());
        accountRepository.save(account);
        return true;
    }

    @Override
    public boolean deleteAccount(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new ResourceNotFoundException("Account", "accountId", accountId.toString())
        );
        accountRepository.deleteByAccountId(account.getAccountId());
        return true;
    }

}

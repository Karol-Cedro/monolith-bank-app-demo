package com.kcedro.monolithbankapp.service.impl;


import com.kcedro.monolithbankapp.constants.OperationsConstants;
import com.kcedro.monolithbankapp.dto.AccountBalanceDto;
import com.kcedro.monolithbankapp.dto.CardDebtDto;
import com.kcedro.monolithbankapp.dto.OperationDto;
import com.kcedro.monolithbankapp.entity.Operation;
import com.kcedro.monolithbankapp.repository.OperationsRepository;
import com.kcedro.monolithbankapp.service.IAccountService;
import com.kcedro.monolithbankapp.service.IBankAppService;
import com.kcedro.monolithbankapp.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BankAppServiceImpl implements IBankAppService {

    private final IAccountService accountService;
    private final ICardsService cardsService;
    private final OperationsRepository operationsRepository;

    @Override
    public boolean withdraw(OperationDto operationDto) {
        if (!operationDto.getOperationType().equals(OperationsConstants.WITHDRAW)) return false;

        Long accountId = operationDto.getAccountId();
        Long accountBalance = accountService.fetchAccountBalance(accountId);
        Integer cardAvailableAmount = cardsService.getAvailableAmount(accountId);

        //If bigger than available amount of money on both card and account
        if (accountBalance + cardAvailableAmount < operationDto.getOperationAmount()) return false;

        if (accountBalance > operationDto.getOperationAmount()) {
            //We need to update account balance
            AccountBalanceDto accountBalanceDto = new AccountBalanceDto();
            accountBalanceDto.setAccountId(accountId);
            accountBalanceDto.setBalance(accountBalance - operationDto.getOperationAmount());
            accountService.updateAccountBalance(accountBalanceDto);

        } else {
            //We need to loan from card
            Long remainingAmount = operationDto.getOperationAmount() - accountBalance;

            //set account balance to zero
            AccountBalanceDto accountBalanceDto = new AccountBalanceDto();
            accountBalanceDto.setAccountId(accountId);
            accountBalanceDto.setBalance(0L);
            accountService.updateAccountBalance(accountBalanceDto);

            //loan from card
            CardDebtDto cardDebtDto = new CardDebtDto();
            cardDebtDto.setAccountId(accountId);
            cardDebtDto.setAmount((int) (cardAvailableAmount - remainingAmount));
            cardsService.updateCardAvailableAmount(cardDebtDto);
        }

        Operation operation = new Operation();
        operation.setAccountId(accountId);
        operation.setOperationType(operationDto.getOperationType());
        operation.setOperationAmount(operationDto.getOperationAmount());
        operationsRepository.save(operation);

        return true;
    }

    @Override
    public void deposit(OperationDto operationDto) {

        Integer cardAvailableAmount = cardsService.getAvailableAmount(operationDto.getAccountId());
        Integer cardLimit = cardsService.getCardLimit(operationDto.getAccountId());

        int debt = cardLimit - cardAvailableAmount;

        if (debt > 0 && debt >= operationDto.getOperationAmount()) {
            //Pay debt on card if it exists
            CardDebtDto cardDebtDto = new CardDebtDto();
            cardDebtDto.setAccountId(operationDto.getAccountId());
            cardDebtDto.setAmount(operationDto.getOperationAmount());
            cardsService.payDebt(cardDebtDto);
        } else if (debt > 0) {
            //If there is something left put it on account
            CardDebtDto cardDebtDto = new CardDebtDto();
            cardDebtDto.setAccountId(operationDto.getAccountId());
            cardDebtDto.setAmount(debt);
            cardsService.payDebt(cardDebtDto);

            int restOfMoney = operationDto.getOperationAmount() - debt;
            AccountBalanceDto accountBalanceDto = new AccountBalanceDto();
            accountBalanceDto.setAccountId(operationDto.getAccountId());
            accountBalanceDto.setBalance((long) restOfMoney);
            accountService.updateAccountBalance(accountBalanceDto);
        } else if (debt == 0) {
            Long accountActualBalance = accountService.fetchAccountBalance(operationDto.getAccountId());
            AccountBalanceDto accountBalanceDto = new AccountBalanceDto();
            accountBalanceDto.setAccountId(operationDto.getAccountId());
            accountBalanceDto.setBalance(accountActualBalance + operationDto.getOperationAmount());
            accountService.updateAccountBalance(accountBalanceDto);
        }

        Operation operation = new Operation();
        operation.setAccountId(operationDto.getAccountId());
        operation.setOperationType(operationDto.getOperationType());
        operation.setOperationAmount(operationDto.getOperationAmount());
        operationsRepository.save(operation);
    }

    @Override
    public List<Operation> getOperationsHistory(Long accountId) {
        return operationsRepository.findByAccountId(accountId);
    }
}

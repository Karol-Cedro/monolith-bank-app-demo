package com.kcedro.operations.service.impl;

import com.kcedro.operations.configuration.ServiceProperties;
import com.kcedro.operations.constants.OperationsConstants;
import com.kcedro.operations.dto.AccountBalanceDto;
import com.kcedro.operations.dto.CardDebtDto;
import com.kcedro.operations.dto.OperationDto;
import com.kcedro.operations.entity.Operation;
import com.kcedro.operations.repository.OperationsRepository;
import com.kcedro.operations.service.IOperationsService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OperationsServiceImpl implements IOperationsService {

    private final OperationsRepository operationsRepository;
    private final OperationsRepository operationRepository;

    private final ServiceProperties serviceProperties;

    private final RestTemplate restTemplate;

    public OperationsServiceImpl(OperationsRepository operationsRepository, OperationsRepository operationRepository, ServiceProperties serviceProperties) {
        this.operationsRepository = operationsRepository;
        this.operationRepository = operationRepository;
        this.serviceProperties = serviceProperties;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public boolean withdraw(OperationDto operationDto) {
        if (!operationDto.getOperationType().equals(OperationsConstants.WITHDRAW)) return false;

        Long accountId = operationDto.getAccountId();

        String accountGetBalanceEndpoint = serviceProperties.getAccountsUrl() + "/api/v1/account/get-balance?accountId={accountId}";
        Long accountBalance = restTemplate.getForObject(accountGetBalanceEndpoint, Long.class, accountId);

        String cardGetAvailableAmountEndpoint = serviceProperties.getCardsUrl() + "/api/v1/cards/available-amount?accountId={accountId}";
        Integer cardAvailableAmount = restTemplate.getForObject(cardGetAvailableAmountEndpoint, Integer.class, accountId);

        //If bigger than available amount of money on both card and account
        if (accountBalance + cardAvailableAmount < operationDto.getOperationAmount()) return false;

        if (accountBalance > operationDto.getOperationAmount()) {
            //We need to update account balance
            String accountUpdateBalanceEndpoint = serviceProperties.getAccountsUrl() + "/api/v1/account/update-balance";
            AccountBalanceDto accountBalanceDto = new AccountBalanceDto();
            accountBalanceDto.setAccountId(accountId);
            accountBalanceDto.setBalance(accountBalance - operationDto.getOperationAmount());
            restTemplate.put(accountUpdateBalanceEndpoint, accountBalanceDto, String.class);

        } else {
            //We need to loan from card
            Long remainingAmount = operationDto.getOperationAmount() - accountBalance;

            //set account balance to zero
            String accountUpdateBalanceEndpoint = serviceProperties.getAccountsUrl() + "/api/v1/account/update-balance";
            AccountBalanceDto accountBalanceDto = new AccountBalanceDto();
            accountBalanceDto.setAccountId(accountId);
            accountBalanceDto.setBalance(0L);
            restTemplate.put(accountUpdateBalanceEndpoint, accountBalanceDto, String.class);

            //loan from card
            String cardLoanEndpoint = serviceProperties.getCardsUrl() + "/api/v1/cards/update-available-amount";
            CardDebtDto cardDebtDto = new CardDebtDto();
            cardDebtDto.setAccountId(accountId);
            cardDebtDto.setAmount((int) (cardAvailableAmount - remainingAmount));
            restTemplate.put(cardLoanEndpoint, cardDebtDto, String.class);
        }

        Operation operation = new Operation();
        operation.setAccountId(accountId);
        operation.setOperationType(operationDto.getOperationType());
        operation.setOperationAmount(operationDto.getOperationAmount());
        operationRepository.save(operation);

        return true;
    }

    @Override
    public void deposit(OperationDto operationDto) {

        String cardGetAvailableAmountEndpoint = serviceProperties.getCardsUrl() + "/api/v1/cards/available-amount?accountId={accountId}";
        Integer cardAvailableAmount = restTemplate.getForObject(cardGetAvailableAmountEndpoint, Integer.class, operationDto.getAccountId());

        String cardGetCardLimitEndpoint = serviceProperties.getCardsUrl() + "/api/v1/cards/get-limit?accountId={accountId}";
        Integer cardLimit = restTemplate.getForObject(cardGetCardLimitEndpoint, Integer.class, operationDto.getAccountId());

        int debt = cardLimit - cardAvailableAmount;

        if (debt > 0 && debt >= operationDto.getOperationAmount()) {
            //Pay debt on card if it exists
            String cardPayDebtEndpoint = serviceProperties.getCardsUrl() + "/api/v1/cards/pay-debt";
            CardDebtDto cardDebtDto = new CardDebtDto();
            cardDebtDto.setAccountId(operationDto.getAccountId());
            cardDebtDto.setAmount(operationDto.getOperationAmount());
            restTemplate.put(cardPayDebtEndpoint, cardDebtDto, String.class);
        } else if (debt > 0) {
            //If there is something left put it on account
            String cardPayDebtEndpoint = serviceProperties.getCardsUrl() + "/api/v1/cards/pay-debt";
            CardDebtDto cardDebtDto = new CardDebtDto();
            cardDebtDto.setAccountId(operationDto.getAccountId());
            cardDebtDto.setAmount(debt);
            restTemplate.put(cardPayDebtEndpoint, cardDebtDto, String.class);

            int restOfMoney = operationDto.getOperationAmount() - debt;
            String accountUpdateBalanceEndpoint = serviceProperties.getAccountsUrl() + "/api/v1/account/update-balance";
            AccountBalanceDto accountBalanceDto = new AccountBalanceDto();
            accountBalanceDto.setAccountId(operationDto.getAccountId());
            accountBalanceDto.setBalance((long) restOfMoney);
            restTemplate.put(accountUpdateBalanceEndpoint, accountBalanceDto, String.class);
        } else if (debt == 0) {
            String accountAccountBalanceEndpoint = serviceProperties.getAccountsUrl() + "/api/v1/account/get-balance?accountId={accountId}";
            Long accountActualBalance = restTemplate.getForObject(accountAccountBalanceEndpoint, Long.class, operationDto.getAccountId());

            String accountUpdateBalanceEndpoint = serviceProperties.getAccountsUrl() + "/api/v1/account/update-balance";
            AccountBalanceDto accountBalanceDto = new AccountBalanceDto();
            accountBalanceDto.setAccountId(operationDto.getAccountId());
            accountBalanceDto.setBalance(accountActualBalance + operationDto.getOperationAmount());
            restTemplate.put(accountUpdateBalanceEndpoint, accountBalanceDto, String.class);
        }

        Operation operation = new Operation();
        operation.setAccountId(operationDto.getAccountId());
        operation.setOperationType(operationDto.getOperationType());
        operation.setOperationAmount(operationDto.getOperationAmount());
        operationRepository.save(operation);
    }

    @Override
    public List<Operation> getOperationsHistory(Long accountId) {
        return operationsRepository.findByAccountId(accountId);
    }
}

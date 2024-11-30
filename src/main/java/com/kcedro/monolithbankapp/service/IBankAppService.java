package com.kcedro.monolithbankapp.service;

import com.kcedro.monolithbankapp.dto.OperationDto;
import com.kcedro.monolithbankapp.entity.Operation;

import java.util.List;

public interface IBankAppService {

    boolean withdraw(OperationDto operationDto);

    void deposit(OperationDto operationDto);

    List<Operation> getOperationsHistory(Long accountId);
}

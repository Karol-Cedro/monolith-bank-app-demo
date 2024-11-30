package com.kcedro.monolithbankapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OperationDto {

    @NotEmpty(message = "Operation type cannot be empty")
    private String operationType;

    @Positive(message = "Operation amount should be greater than zero")
    private int operationAmount;

    @Positive(message = "Account Id should be greater than zero")
    private Long accountId;
}

package com.kcedro.monolithbankapp.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class AccountBalanceDto {

    @Positive(message = "Account Id should be greater than zero")
    private Long accountId;

    @PositiveOrZero(message = "Balance should be equal or greater than zero")
    private Long balance;
}

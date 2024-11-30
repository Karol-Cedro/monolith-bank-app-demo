package com.kcedro.monolithbankapp.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CardDebtDto {
    @Positive(message = "AccountId should be greater than zero")
    private Long accountId;

    @Positive(message = "Amount should be greater than zero")
    private int amount;
}

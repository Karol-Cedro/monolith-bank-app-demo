package com.kcedro.cards.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CardDto {

    @NotEmpty(message = "Owner Name can not be a null or empty")
    private String ownerName;

    @NotEmpty(message = "Owner Surname can not be a null or empty")
    private String ownerSurname;

    @Positive(message = "Assigned account id should be greater than zero")
    private Long accountId;

    @Positive(message = "Total card limit should be greater than zero")
    private int totalLimit;

    private String cardNumber;

    private int availableAmount;

}

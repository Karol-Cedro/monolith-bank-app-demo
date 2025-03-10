package com.kcedro.monolithbankapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AccountDto {

    @NotEmpty(message = "Account name cannot be empty")
    @Size(min = 5, max = 30, message = "Account name must be between 5 and 30 characters")
    private String name;

    @NotEmpty(message = "Account name cannot be empty")
    @Size(min = 5, max = 30, message = "Account name must be between 5 and 30 characters")
    private String surname;

    @NotEmpty(message = "Account email cannot be empty")
    @Email(message = "Email address should be valid")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

}

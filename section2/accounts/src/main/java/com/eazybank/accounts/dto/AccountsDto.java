package com.eazybank.accounts.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
//@Builder
//@Valid
public class AccountsDto {
    @NotEmpty(message = "Account Number cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account Number must be 10 digits")
    private Long accountNumber;
    @NotEmpty(message = "Account type cannot be null or empty")
    private String accountType;
    @NotEmpty(message = "Branch Address cannot be null or empty")
    private String branchAddress;
}

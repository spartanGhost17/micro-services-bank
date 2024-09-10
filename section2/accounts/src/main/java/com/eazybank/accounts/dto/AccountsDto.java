package com.eazybank.accounts.dto;

import lombok.Data;

@Data
//@Builder
public class AccountsDto {
    private Long accountNumber;
    private String accountType;
    private String branchAddress;
}

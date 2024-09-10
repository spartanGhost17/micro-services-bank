package com.eazybank.accounts.dto;

import lombok.Data;

@Data
//@Builder
public class CustomerDto {
    private String name;
    private String email;
    private String mobileNumber;
    private AccountsDto accountsDto;
}

package com.eazybank.accounts.service.client;

import com.eazybank.accounts.dto.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import static com.eazybank.accounts.constants.AccountsConstants.EAZYBANK_CORRELATION_ID;

@FeignClient(name = "loans")
public interface LoansFeignClient {

    @GetMapping(value = "/api/fetch", produces = "application/json")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestHeader(EAZYBANK_CORRELATION_ID) String correlationId,
                                                     @RequestParam String mobileNumber);
}

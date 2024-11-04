package com.eazybank.accounts.service.client;

import com.eazybank.accounts.dto.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import static com.eazybank.accounts.constants.AccountsConstants.EAZYBANK_CORRELATION_ID;

// url is given for resolution of DNS in the k8s cluster
// FeignClient will not perform load balancing after getting details from eureka registry because the discovery is now handled by k8s
@FeignClient(name = "loans", url = "http://loans:8090", fallback = LoansFallback.class)
public interface LoansFeignClient {

    @GetMapping(value = "/api/fetch", produces = "application/json")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestHeader(EAZYBANK_CORRELATION_ID) String correlationId,
                                                     @RequestParam String mobileNumber);
}

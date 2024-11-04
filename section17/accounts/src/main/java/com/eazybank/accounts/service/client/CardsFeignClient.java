package com.eazybank.accounts.service.client;

import com.eazybank.accounts.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import static com.eazybank.accounts.constants.AccountsConstants.EAZYBANK_CORRELATION_ID;

// url is given for resolution of DNS in the k8s cluster
// FeignClient will not perform load balancing after getting details from eureka
@FeignClient(name = "cards", url = "http://cards:9000", fallback = CardsFallback.class)
public interface CardsFeignClient {

    @GetMapping(value = "/api/fetch", consumes = "application/json")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestHeader(EAZYBANK_CORRELATION_ID) String correlationId,
                                                     @RequestParam String mobileNumber);
}

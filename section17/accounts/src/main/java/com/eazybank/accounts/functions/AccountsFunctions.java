package com.eazybank.accounts.functions;

import com.eazybank.accounts.service.IAccountsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class AccountsFunctions {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountsFunctions.class);

    @Bean
    public Consumer<Long> updateCommunication(IAccountsService iAccountsService) {
        return accountNumber -> {
          LOGGER.info("Updating communication status for the account number: " + accountNumber.toString());
            iAccountsService.updateCommunicationStatus(accountNumber);
        };
    }

}

package com.eazybank.message.functions;

import com.eazybank.message.dto.AccountsMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunctions {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageFunctions.class);

    /**
     * message broker will invoke this for email
     * Accept input AccountsMsgDto and return output AccountsMsgDto
     * @return - accountsMsgDto
     */
    @Bean
    public Function<AccountsMsgDto, AccountsMsgDto> email() {
        return accountsMsgDto -> {
          LOGGER.info("Sending email with the details: "+ accountsMsgDto.toString());
          return accountsMsgDto;
        };
    }

    /**
     * message broker will invoke this for sms
     * Accept input AccountsMsgDto and return output Long
     * @return - account number
     */
    @Bean
    public Function<AccountsMsgDto, Long> sms() {
        return accountsMsgDto -> {
            LOGGER.info("Sending sms with the details: "+ accountsMsgDto.toString());
            return accountsMsgDto.accountNumber();
        };
    }


}

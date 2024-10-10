package com.eazybank.accounts.service;

import com.eazybank.accounts.dto.CustomerDetailsDto;

public interface ICustomersService {

    /**
     *
     * @param mobileNumber - the Mobile Number
     * @return Customer Details based on a given mobile number
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId);
}

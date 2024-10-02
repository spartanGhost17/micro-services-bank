package com.eazybank.accounts.service;

import com.eazybank.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     *
     * @param customerDto CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber - Input mobile number
     * @return returns account number based on a given mobileNumber
     */
    CustomerDto getAccount(String mobileNumber);

    /**
     *
     * @param customerDto - customerDto object
     * @return boolean indicating if the update of Account details is successful or not
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber - input mobile number
     * @return boolean indicates if the account deletion is successful or not
     */
    boolean deleteAccount(String mobileNumber);
}

package com.infosys.retailer.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Long customerId) {
        super("Customer with ID " + customerId + " not found.");
    }
}

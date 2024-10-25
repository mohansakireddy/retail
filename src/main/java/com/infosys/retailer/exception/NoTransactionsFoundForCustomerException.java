package com.infosys.retailer.exception;

public class NoTransactionsFoundForCustomerException extends RuntimeException {

    public NoTransactionsFoundForCustomerException(Long customerId) {
        super("no transaction found for the customer " + customerId + "in last three months");
    }
}

package com.infosys.retailer.exception;

public class NoTransactionsFoundException extends RuntimeException {

    public NoTransactionsFoundException(Long customerId) {
        super("No Transactions found for the Customer with CustomerID " + customerId + "in last three months");
    }

}

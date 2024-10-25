package com.infosys.retailer.exception;

public class NoTransactionsFoundException extends RuntimeException {

    public NoTransactionsFoundException() {
        super("No Transactions found in last three months");
    }

}

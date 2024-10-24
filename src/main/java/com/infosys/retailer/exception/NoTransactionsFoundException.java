package com.infosys.retailer.exception;

public class NoTransactionsFoundException  extends RuntimeException{

    public NoTransactionsFoundException(Long cutomerId){
        super("No Transactions found for the Customer with CustomerID "+cutomerId+ "in last three months");
    }

}

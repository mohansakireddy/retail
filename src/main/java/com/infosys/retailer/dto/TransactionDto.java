package com.infosys.retailer.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class TransactionDto {

    private Long customerId;
    private double amount;
    private LocalDate transactionDate;


}

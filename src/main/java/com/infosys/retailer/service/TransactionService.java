package com.infosys.retailer.service;

import com.infosys.retailer.dto.TransactionDto;
import com.infosys.retailer.entity.Transaction;

public interface TransactionService {

    Transaction createTransaction(TransactionDto transactionDto);
}

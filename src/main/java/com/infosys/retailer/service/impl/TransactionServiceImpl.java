package com.infosys.retailer.service.impl;

import com.infosys.retailer.dto.TransactionDto;
import com.infosys.retailer.entity.Customer;
import com.infosys.retailer.entity.Transaction;
import com.infosys.retailer.repository.CustomerRepository;
import com.infosys.retailer.repository.TransactionRepository;
import com.infosys.retailer.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Override
    public Transaction createTransaction(TransactionDto transactionDto) {
        Customer customer = customerRepository.findById(transactionDto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTransactionDate(transactionDto.getTransactionDate());

        return transactionRepository.save(transaction);
    }
}

package com.infosys.retailer.service.impl;

import com.infosys.retailer.entity.Customer;
import com.infosys.retailer.entity.Transaction;
import com.infosys.retailer.exception.CustomerNotFoundException;
import com.infosys.retailer.exception.NoTransactionsFoundException;
import com.infosys.retailer.repository.CustomerRepository;
import com.infosys.retailer.repository.TransactionRepository;
import com.infosys.retailer.service.RewardService;
import com.infosys.retailer.variables.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RewardServiceImpl implements RewardService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Override
    public Map<String, Object> getCustomerRewards(Long customerId) {

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
        LocalDate currentDate = LocalDate.now();
        LocalDate threeMonthsAgo = currentDate.minusMonths(3);
        List<Transaction> transactions = transactionRepository.findTransactionsForLastThreeMonths(customerId,threeMonthsAgo);

        if (transactions.isEmpty()){
              throw new NoTransactionsFoundException(customerId);
        }
        Map<String,Integer> monthlyRewardPoints = new LinkedHashMap<>();
        int totalRewardPoints = 0;

        for(Transaction transaction: transactions){
            int rewardPointsPerTransaction = calculateRewardPoints(transaction.getAmount());
            String month = String.valueOf(transaction.getTransactionDate().getMonth());
            monthlyRewardPoints.put(month, monthlyRewardPoints.getOrDefault(month,0)+rewardPointsPerTransaction);
            totalRewardPoints = totalRewardPoints + rewardPointsPerTransaction;

             }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put(Constants.RESPONSE_CUSTOMER_NAME, customer.getName());
        response.put(Constants.RESPONSE_REWARDS_PER_MONTH,monthlyRewardPoints);
        response.put(Constants.RESPONSE_TOTAL_REWARDS, totalRewardPoints);
        return response;
    }

    private int calculateRewardPoints(double amount){
        int points = 0;

        if (amount > 100){
            points= (int) (points + ((amount-100)*2));
            //points = points +50; //assigning 50 points for first 100
            amount = 100;  // initializing the amount to 100 to calculate points for first 100
        }
        if(amount > 50){
            points = (int) (points + (amount - 50));
        }
        return points;
    }
}

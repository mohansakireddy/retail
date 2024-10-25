package com.infosys.retailer.service.impl;

import com.infosys.retailer.entity.Customer;
import com.infosys.retailer.entity.Transaction;
import com.infosys.retailer.exception.CustomerNotFoundException;
import com.infosys.retailer.exception.NoTransactionsFoundException;
import com.infosys.retailer.exception.NoTransactionsFoundForCustomerException;
import com.infosys.retailer.repository.CustomerRepository;
import com.infosys.retailer.repository.TransactionRepository;
import com.infosys.retailer.service.RewardService;
import com.infosys.retailer.variables.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class RewardServiceImpl implements RewardService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Map<String, Object>> getRewardsForRecentTransactions() {
        LocalDate currentDate = LocalDate.now();
        LocalDate threeMonthsAgo = currentDate.minusMonths(3);
        List<Transaction> transactions = transactionRepository.findTransactionsForLastThreeMonths(threeMonthsAgo);

        if (transactions.isEmpty()) {
            throw new NoTransactionsFoundException();
        }

        Map<Long, Map<String, Object>> rewardsPerCustomer = new LinkedHashMap<>();

        for (Transaction transaction : transactions) {
            Long customerId = transaction.getCustomer().getId();
            String month = transaction.getTransactionDate().getMonth().toString();
            int rewardPoints = calculateRewardPoints(transaction.getAmount());

            rewardsPerCustomer.putIfAbsent(customerId, new LinkedHashMap<>(Map.of(
                    Constants.RESPONSE_CUSTOMER_ID, transaction.getCustomer().getId(),
                    Constants.RESPONSE_CUSTOMER_NAME, transaction.getCustomer().getName(),
                    Constants.RESPONSE_REWARDS_PER_MONTH, new LinkedHashMap<String, Integer>(),
                    Constants.RESPONSE_TOTAL_REWARDS, 0

            )));

            Map<String, Object> customerData = rewardsPerCustomer.get(customerId);
            Map<String, Integer> monthlyRewards = (Map<String, Integer>) customerData.get(Constants.RESPONSE_REWARDS_PER_MONTH);

            // Updating monthly and total rewards
            monthlyRewards.put(month, monthlyRewards.getOrDefault(month, 0) + rewardPoints);
            int currentTotal = (int) customerData.get(Constants.RESPONSE_TOTAL_REWARDS);
            customerData.put(Constants.RESPONSE_TOTAL_REWARDS, currentTotal + rewardPoints);


        }
//        List<Map<String, Object>> orderedRewardsList = new ArrayList<>();
//        for (Map.Entry<Long, Map<String, Object>> entry : rewardsPerCustomer.entrySet()) {
//            Map<String, Object> customerInfo = entry.getValue();
//            orderedRewardsList.add(customerInfo);
//        }

        return new ArrayList<>(rewardsPerCustomer.values());

    }


    private int calculateRewardPoints(double amount) {
        int points = 0;

        if (amount > 100) {
            points = (int) (points + ((amount - 100) * 2));
            //points = points +50; //assigning 50 points for first 100
            amount = 100;  // initializing the amount to 100 to calculate points for first 100
        }
        if (amount > 50) {
            points = (int) (points + (amount - 50));
        }
        return points;
    }

    @Override
    public Map<String, Object> getCustomerRewards(Long customerId) {

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
        LocalDate currentDate = LocalDate.now();
        LocalDate threeMonthsAgo = currentDate.minusMonths(3);
        List<Transaction> transactions = transactionRepository.findCustomerTransactionsForLastThreeMonths(customerId, threeMonthsAgo);

        if (transactions.isEmpty()) {
            throw new NoTransactionsFoundForCustomerException(customerId);
        }
        Map<String, Integer> monthlyRewardPoints = new LinkedHashMap<>();
        int totalRewardPoints = 0;

        for (Transaction transaction : transactions) {
            int rewardPointsPerTransaction = calculateRewardPoints(transaction.getAmount());
            String month = String.valueOf(transaction.getTransactionDate().getMonth());
            monthlyRewardPoints.put(month, monthlyRewardPoints.getOrDefault(month, 0) + rewardPointsPerTransaction);
            totalRewardPoints = totalRewardPoints + rewardPointsPerTransaction;

        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put(Constants.RESPONSE_CUSTOMER_NAME, customer.getName());
        response.put(Constants.RESPONSE_REWARDS_PER_MONTH, monthlyRewardPoints);
        response.put(Constants.RESPONSE_TOTAL_REWARDS, totalRewardPoints);
        return response;
    }


}

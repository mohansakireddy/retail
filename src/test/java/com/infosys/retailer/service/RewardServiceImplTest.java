package com.infosys.retailer.service;

import com.infosys.retailer.entity.Customer;
import com.infosys.retailer.entity.Transaction;
import com.infosys.retailer.exception.CustomerNotFoundException;
import com.infosys.retailer.exception.NoTransactionsFoundException;
import com.infosys.retailer.exception.NoTransactionsFoundForCustomerException;
import com.infosys.retailer.repository.CustomerRepository;
import com.infosys.retailer.repository.TransactionRepository;
import com.infosys.retailer.service.impl.RewardServiceImpl;
import com.infosys.retailer.variables.Constants;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class RewardServiceImplTest {

    @InjectMocks
    private RewardServiceImpl rewardService;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private TransactionRepository transactionRepository;
    private Customer customer;


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        customer = new Customer();
        customer.setId(1L);
        customer.setName("mohan");

    }

    @Test
    void testGetRewardsForRecentTransactions() {

        Transaction transaction1 = new Transaction();
        transaction1.setCustomer(customer);
        transaction1.setAmount(175);
        transaction1.setTransactionDate(LocalDate.now().minusMonths(0));

        Transaction transaction2 = new Transaction();
        transaction2.setCustomer(customer);
        transaction2.setAmount(75);
        transaction2.setTransactionDate(LocalDate.now().minusMonths(1));

        when(transactionRepository.findTransactionsForLastThreeMonths(LocalDate.now().minusMonths(3))).thenReturn(Arrays.asList(transaction1, transaction2));

        List<Map<String, Object>> result = rewardService.getRewardsForRecentTransactions();

        Map<String, Object> customerData = result.get(0);
        assertEquals(1L, customerData.get(Constants.RESPONSE_CUSTOMER_ID));
        assertEquals("mohan", customerData.get(Constants.RESPONSE_CUSTOMER_NAME));

        Map<String, Integer> customerRewards = (Map<String, Integer>) customerData.get(Constants.RESPONSE_REWARDS_PER_MONTH);
        assertEquals(200, customerRewards.get(LocalDate.now().minusMonths(0).getMonth().toString()));
        assertEquals(25, customerRewards.get(LocalDate.now().minusMonths(1).getMonth().toString()));

    }

    @Test
    void testGetRewardsForRecentTransactions_NoTransactionFound() {
        when(transactionRepository.findTransactionsForLastThreeMonths(LocalDate.now().minusMonths(3))).thenReturn(Arrays.asList());

        assertThrows(NoTransactionsFoundException.class, () -> rewardService.getRewardsForRecentTransactions());
    }

    @Test
    void testGetCustomerRewards_Success() {

        List<Transaction> transactions = Arrays.asList(
                new Transaction(1L, customer, LocalDate.now().minusMonths(0), 175),
                new Transaction(2L, customer, LocalDate.now().minusMonths(1), 75),
                new Transaction(3L, customer, LocalDate.now().minusMonths(2), 5)
        );
        customer.setTransactions(transactions);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(transactionRepository.findCustomerTransactionsForLastThreeMonths(1L, LocalDate.now().minusMonths(3))).thenReturn(transactions);

        Map<String, Object> response = rewardService.getCustomerRewards(customer.getId());

        assertEquals("mohan", response.get("Customer"));

        Map<String, Object> rewardsPerMonth = (Map<String, Object>) response.get("Rewards Per Month");
        assertEquals(200, rewardsPerMonth.get(LocalDate.now().minusMonths(0).getMonth().toString()));
        assertEquals(25, rewardsPerMonth.get(LocalDate.now().minusMonths(1).getMonth().toString()));
        assertEquals(0, rewardsPerMonth.get(LocalDate.now().minusMonths(2).getMonth().toString()));

        assertEquals(225, response.get("Total Rewards"));

//        assertEquals(0, rewardsPerMonth.get("AUGUST"));
//        assertEquals(25, rewardsPerMonth.get("SEPTEMBER"));
//        assertEquals(200, rewardsPerMonth.get("OCTOBER"));

    }

    @Test
    void testGetCustomerRewards_CustomerNotFound() {
        when(customerRepository.findById(customer.getId())).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> rewardService.getCustomerRewards(customer.getId()));
    }

    @Test
    void testGetCustomerRewards_NoTransactionsFound() {
        Customer customer = new Customer(1L, "mohan", null);
        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        when(transactionRepository.findCustomerTransactionsForLastThreeMonths(eq(customer.getId()), any(LocalDate.class))).thenReturn(Collections.emptyList());

        assertThrows(NoTransactionsFoundForCustomerException.class, () -> rewardService.getCustomerRewards(customer.getId()));

    }


}

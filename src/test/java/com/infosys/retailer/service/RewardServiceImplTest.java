package com.infosys.retailer.service;

import com.infosys.retailer.entity.Customer;
import com.infosys.retailer.entity.Transaction;
import com.infosys.retailer.exception.CustomerNotFoundException;
import com.infosys.retailer.exception.NoTransactionsFoundException;
import com.infosys.retailer.repository.CustomerRepository;
import com.infosys.retailer.repository.TransactionRepository;
import com.infosys.retailer.service.impl.RewardServiceImpl;
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
    void setup(){
        MockitoAnnotations.openMocks(this);
         customer = new Customer();
        customer.setId(1L);
        customer.setName("mohan");



    }

    @Test
    void testGetCustomerRewards_Success(){

    List<Transaction> transactions = Arrays.asList(
            new Transaction(1L,customer, LocalDate.now().minusMonths(0),175),
            new Transaction(2L,customer,  LocalDate.now().minusMonths(1),75 ),
            new Transaction(3L,customer,  LocalDate.now().minusMonths(2),5 )
    );
       customer.setTransactions(transactions);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(transactionRepository.findTransactionsForLastThreeMonths(1L, LocalDate.now().minusMonths(3))).thenReturn(transactions);

        Map<String , Object> response = rewardService.getCustomerRewards(customer.getId());

        assertEquals("mohan", response.get("Customer"));

        Map<String , Object> rewardsPerMonth = (Map<String, Object>) response.get("Rewards Per Month");
        assertEquals(200, rewardsPerMonth.get(LocalDate.now().minusMonths(0).getMonth().toString()));
        assertEquals(25, rewardsPerMonth.get(LocalDate.now().minusMonths(1).getMonth().toString()));
        assertEquals(0, rewardsPerMonth.get(LocalDate.now().minusMonths(2).getMonth().toString()));

        assertEquals(225, response.get("Total Rewards"));

//        assertEquals(0, rewardsPerMonth.get("AUGUST"));
//        assertEquals(25, rewardsPerMonth.get("SEPTEMBER"));
//        assertEquals(200, rewardsPerMonth.get("OCTOBER"));

}

@Test
void  testGetCustomerRewards_CustomerNotFound(){
        when(customerRepository.findById(customer.getId())).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> rewardService.getCustomerRewards(customer.getId()));
}

@Test
   void testGetCustomerRewards_NoTransactionsFound(){
       Customer customer = new Customer(1L, "mohan", null);
        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        when(transactionRepository.findTransactionsForLastThreeMonths(eq(customer.getId()),any(LocalDate.class))).thenReturn(Collections.emptyList());

        assertThrows(NoTransactionsFoundException.class, () ->rewardService.getCustomerRewards(customer.getId()));

   }


}

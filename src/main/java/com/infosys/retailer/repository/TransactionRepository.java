package com.infosys.retailer.repository;

import com.infosys.retailer.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.transactionDate >= :startDate")
    List<Transaction> findTransactionsForLastThreeMonths(@Param("startDate") LocalDate startDate);

    List<Transaction> findByCustomerId(Long customerId);

    @Query("SELECT t FROM Transaction t WHERE t.customer.id = :customerId AND t.transactionDate >= :threeMonthsAgo")
    List<Transaction> findCustomerTransactionsForLastThreeMonths(@Param("customerId") Long customerId, @Param("threeMonthsAgo") LocalDate threeMonthsAgo);
}
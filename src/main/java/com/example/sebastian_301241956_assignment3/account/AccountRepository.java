package com.example.sebastian_301241956_assignment3.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findByCustomerCustomerId(int customerId);

    @Query("SELECT new map(a.accountNumber as accountNumber, " +
            "a.accountType as accountType, " +
            "a.balance as balance, " +
            "a.overDraftLimit as overDraftLimit, " +
            "c.customerId as customerId, " +
            "c.customerName as customerName) " +
            "FROM Account a JOIN a.customer c " +
            "WHERE c.customerId = :customerId")
    List<Map<String, Object>> findAccountsWithCustomerNameById(int customerId);
}

package com.example.sebastian_301241956_assignment3.account.type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {
    List<AccountType> findAll();

    @Query(nativeQuery = true,
            value = "SELECT at.account_type_id as accountTypeId, " +
                    "at.account_type_name as accountTypeName, " +
                    "COUNT(a.account_number) as totalAccounts, " +
                    "SUM(a.balance) as totalBalance " +
                    "FROM account_type at " +
                    "LEFT JOIN account a ON at.account_type_id = a.account_type_id " +
                    "GROUP BY at.account_type_id, at.account_type_name " +
                    "ORDER BY totalBalance DESC")
    List<Map<String, Object>> findAccountTypesSummary();
}

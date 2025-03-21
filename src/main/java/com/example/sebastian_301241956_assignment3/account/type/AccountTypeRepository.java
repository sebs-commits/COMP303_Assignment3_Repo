package com.example.sebastian_301241956_assignment3.account.type;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {
    List<AccountType> findAll();
}

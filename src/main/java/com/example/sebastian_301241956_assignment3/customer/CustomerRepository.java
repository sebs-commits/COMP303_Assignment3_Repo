package com.example.sebastian_301241956_assignment3.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    //
    List<Customer> findByAdminFalse();
}

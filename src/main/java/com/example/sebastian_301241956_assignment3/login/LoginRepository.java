package com.example.sebastian_301241956_assignment3.login;

import com.example.sebastian_301241956_assignment3.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Customer, Integer> {
    Customer findByUsername(String username);
    Customer findByPassword(String password);
}

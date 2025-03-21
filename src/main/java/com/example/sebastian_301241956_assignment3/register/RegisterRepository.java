package com.example.sebastian_301241956_assignment3.register;

import com.example.sebastian_301241956_assignment3.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<Customer, Integer> {
    Customer findByUsername(String username);
    Customer findByEmail(String email);
}

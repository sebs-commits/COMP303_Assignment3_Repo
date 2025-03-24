package com.example.sebastian_301241956_assignment3.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    //
    List<Customer> findByAdminFalse();

    @Query("SELECT new map(" +
            "c.customerId as customerId, " +
            "c.customerName as customerName, " +
            "c.email as email, " +
            "c.phone as phone, " +
            "c.address as address, " +
            "c.city as city, " +
            "c.postalCode as postalCode) " +
            "FROM Customer c " +
            "WHERE c.admin = false")
    List<Map<String, Object>> findAllCustomersWithDetails();
}

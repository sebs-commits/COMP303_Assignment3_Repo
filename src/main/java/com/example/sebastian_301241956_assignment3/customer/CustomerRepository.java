package com.example.sebastian_301241956_assignment3.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    // Return non admin customers
    @Query("SELECT new map(" +
            "c.customerId as customerId, " +
            "c.customerName as customerName, " +
            "c.username as username, " +
            "c.email as email, " +
            "c.phone as phone, " +
            "c.address as address, " +
            "c.city as city, " +
            "c.postalCode as postalCode) " +
            "FROM Customer c " +
            "WHERE c.customerId = :id AND c.admin = false")
    Optional<Map<String, Object>> findCustomerDetailsById(@Param("id") int id);

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

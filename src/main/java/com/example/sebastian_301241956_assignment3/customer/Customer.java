package com.example.sebastian_301241956_assignment3.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @Column(name = "customer_id")
    private int customerId;

    @NotNull(message = "Username is required")
    @Column(name = "username")
    private String username;
    @NotNull(message = "Password is required")
    @Column(name = "password")
    private String password;

    @Column(name = "customer_name")
    private String customerName;

    @NotNull(message = "Date of Birth is required")
    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @NotNull(message = "Email is required")
    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "is_admin")
    private boolean admin;

}

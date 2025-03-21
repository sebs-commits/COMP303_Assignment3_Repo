package com.example.sebastian_301241956_assignment3.account;

import com.example.sebastian_301241956_assignment3.account.type.AccountType;
import com.example.sebastian_301241956_assignment3.customer.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @Column(name = "account_number")
    private int accountNumber;

    @ManyToOne
    @JoinColumn(name = "account_type_id", nullable = false)
    private AccountType accountType;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "over_draft_limit")
    private BigDecimal overDraftLimit;

}

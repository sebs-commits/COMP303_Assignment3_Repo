package com.example.sebastian_301241956_assignment3.account.type;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "account_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountType {
    @Id
    @Column(name = "account_type_id")
    private int accountTypeId;
    @Column(name = "account_type_name")
    private String accountTypeName;
    @Column(name = "account_type_description")
    private String accountTypeDescription;
    @Column(name = "minimum_balance")
    private BigDecimal minimumBalance;
    @Column(name = "has_overdraft")
    private boolean hasOverdraft;
}

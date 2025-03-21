package com.example.sebastian_301241956_assignment3.account.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountTypeController {
    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @GetMapping("/account-types")
    public ResponseEntity<List<AccountType>> getAllAccountTypes() {
        return ResponseEntity.ok(accountTypeRepository.findAll());
    }

}

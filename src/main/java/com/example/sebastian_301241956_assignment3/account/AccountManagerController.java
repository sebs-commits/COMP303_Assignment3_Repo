package com.example.sebastian_301241956_assignment3.account;

import com.example.sebastian_301241956_assignment3.customer.Customer;
import com.example.sebastian_301241956_assignment3.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AccountManagerController {
    @Autowired
    private CustomerRepository  customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/customers")
    public ResponseEntity<List<Map<String, Object>>> getAllCustomers() {
        List<Customer> customers = customerRepository.findByAdminFalse();

        List<Map<String, Object>> result = new ArrayList<>();

        for (Customer customer : customers) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", customer.getCustomerId());
            map.put("name", customer.getCustomerName());
            map.put("email", customer.getEmail());
            map.put("admin", customer.isAdmin());

            result.add(map);
        }

        return ResponseEntity.ok(result);
    }


}

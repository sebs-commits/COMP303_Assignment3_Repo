package com.example.sebastian_301241956_assignment3.account;

import com.example.sebastian_301241956_assignment3.account.type.AccountTypeRepository;
import com.example.sebastian_301241956_assignment3.customer.Customer;
import com.example.sebastian_301241956_assignment3.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api")
public class AccountManagerController {
    @Autowired
    private CustomerRepository  customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountTypeRepository accountTypeRepository;


    @GetMapping("/customers/{id}/accounts")
    public ResponseEntity<?> getCustomerAccounts(@PathVariable int id) {
        // Check if customer exists
        if (!customerRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        List<Map<String, Object>> accountsInfo = accountRepository.findAccountsWithCustomerNameById(id);

        return ResponseEntity.ok(accountsInfo);
    }

    @GetMapping("/account-types/summary")
    public ResponseEntity<?> getAccountTypesSummary() {
        List<Map<String, Object>> accountTypesSummary = accountTypeRepository.findAccountTypesSummary();
        return ResponseEntity.ok(accountTypesSummary);
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getCustomersWithAccounts() {
        List<Map<String, Object>> customers = customerRepository.findAllCustomersWithDetails();
        List<Map<String, Object>> accounts = accountRepository.findAllAccountsWithCustomerId();

        // Create a map to hold accounts by customerId
        Map<Integer, List<Map<String, Object>>> accountsByCustomer = new HashMap<>();

        // Go through all accounts and organize them by customer ID
        for (Map<String, Object> account : accounts) {
            Integer customerId = (Integer) account.get("customerId");

            // Then if we already started a list for this customer
            if (accountsByCustomer.get(customerId) == null) {
                // If not, then create a new empty list instead
                List<Map<String, Object>> customerAccountsList = new ArrayList<>();
                accountsByCustomer.put(customerId, customerAccountsList);
            }

            // Get the customer account list and add this account to it
            List<Map<String, Object>> customerAccountsList = accountsByCustomer.get(customerId);
            customerAccountsList.add(account);
        }

        // Then add the accounts to each customer
        for (Map<String, Object> customer : customers) {
            Integer customerId = (Integer) customer.get("customerId");

            // Get this customer's accounts create an empty list if none found
            List<Map<String, Object>> customerAccounts = accountsByCustomer.get(customerId);
            if (customerAccounts == null) {
                customerAccounts = new ArrayList<>();
            }

            // Add the accounts and count to the customer data
            customer.put("accounts", customerAccounts);
            customer.put("totalAccounts", customerAccounts.size());
        }

        return ResponseEntity.ok(customers);
    }


//    @GetMapping("/temp")
//    public ResponseEntity<List<Map<String, Object>>> getAllCustomers() {
//        List<Customer> customers = customerRepository.findByAdminFalse();
//
//        List<Map<String, Object>> result = new ArrayList<>();
//
//        for (Customer customer : customers) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("id", customer.getCustomerId());
//            map.put("name", customer.getCustomerName());
//            map.put("email", customer.getEmail());
//            map.put("admin", customer.isAdmin());
//
//            result.add(map);
//        }
//
//        return ResponseEntity.ok(result);
//    }


}

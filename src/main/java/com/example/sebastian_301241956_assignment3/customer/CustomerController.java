package com.example.sebastian_301241956_assignment3.customer;


import jakarta.servlet.http.HttpSession;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    // Get customer info based on session
    @GetMapping("/customer")
    public ResponseEntity<?>getCustomerInfo(HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");

        if(customerId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Not Logged In");
        }
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Customer Not Found!");
        }
        return ResponseEntity.ok(customer);
    }
}

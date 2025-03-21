package com.example.sebastian_301241956_assignment3.customer;


import jakarta.servlet.http.HttpSession;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                    .body("You are not logged in!");
        }
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Customer Not Found!");
        }
        return ResponseEntity.ok(customer);
    }
    @PutMapping("/customer")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");

            if(customerId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("You are not logged in!");
            }

            try{
            Customer existingCustomer = customerRepository.findById(customerId).orElse(null);
            if(existingCustomer == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Customer Not Found!");
            }

            existingCustomer.setAddress(customer.getAddress());
            existingCustomer.setCity(customer.getCity());
            existingCustomer.setPostalCode(customer.getPostalCode());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPhone(customer.getPhone());

            Customer updatedCustomer = customerRepository.save(existingCustomer);
            return ResponseEntity.ok(updatedCustomer);

            } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
            }
    }
}

package com.example.sebastian_301241956_assignment3.register;

import com.example.sebastian_301241956_assignment3.customer.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RegisterController {
    @Autowired
    private RegisterRepository registerRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody Customer customer) {
        try{
            // Check if user already exists
            if(registerRepository.findByUsername(customer.getUsername()) != null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Username already exists!");
            }
            // To check if email already exists
            if(registerRepository.findByEmail(customer.getEmail()) != null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Email already exists!");
            }

            // Else we do this
            // Save the customer
            Customer savedCustomer = registerRepository.save(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

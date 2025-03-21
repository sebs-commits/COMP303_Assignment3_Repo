package com.example.sebastian_301241956_assignment3.login;

import com.example.sebastian_301241956_assignment3.customer.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private LoginRepository loginRepository;
    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestBody Customer customer, HttpSession session) {
        try{
            // First check if user exists
            if(loginRepository.findByUsername(customer.getUsername()) == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Username does not exist!");
            }
            Customer existingCustomer = loginRepository.findByUsername(customer.getUsername());
            // If this returns false, then the password is incorrect
            if(!existingCustomer.getPassword().equals(customer.getPassword())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Incorrect Password!");
            }

            // Storing user information in the session
            session.setAttribute("customerId", existingCustomer.getCustomerId());
            session.setAttribute("username", existingCustomer.getUsername());
            // 10 minutes
            session.setMaxInactiveInterval(60 * 10);

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("status", "success");
            responseBody.put("message", "Login successful");
            // Only returning a status message for now
            return ResponseEntity.ok(responseBody);


        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logoutCustomer(HttpSession session) {
        try{
            // Remove session information
            session.invalidate();
            return ResponseEntity.ok("Logout successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}

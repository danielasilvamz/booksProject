package com.usbbog.booksproject.controllers;

import com.usbbog.booksproject.entities.CustomersEntity;
import com.usbbog.booksproject.services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")

public class CustomersController {
    @Autowired
    private CustomersService customersService;

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        return customersService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable UUID id) {
        return customersService.getCustomerById(id);
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CustomersEntity customer) {
        return customersService.createCustomer(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable UUID id, @RequestBody CustomersEntity customer) {
        return customersService.updateCustomer(id, customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable UUID id) {
        return customersService.deleteCustomer(id);
    }
}

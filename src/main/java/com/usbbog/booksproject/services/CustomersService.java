package com.usbbog.booksproject.services;

import com.usbbog.booksproject.entities.CustomersEntity;
import com.usbbog.booksproject.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomersService {
    @Autowired
    private CustomersRepository customersRepository;

    public ResponseEntity<Map<String, Object>> getAllCustomers() {
        Map<String, Object> response = new HashMap<>();
        List<CustomersEntity> customersList = customersRepository.findAll();
        response.put("Customers", customersList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> getCustomerById(UUID id) {
        Map<String, Object> response = new HashMap<>();
        Optional<CustomersEntity> customerFound = customersRepository.findById(id);
        if (customerFound.isPresent()) {
            response.put("Customer", customerFound.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Error", "Customer not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Map<String, Object>> createCustomer(CustomersEntity customer) {
        Map<String, Object> response = new HashMap<>();
        customer.setId(UUID.randomUUID());
        if (customersRepository.existsById(customer.getId())) {
            response.put("Status", "Customer already exists");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } else {
            CustomersEntity newCustomer = customersRepository.save(customer);
            response.put("Status", "Customer created successfully");
            response.put("Customer", newCustomer);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Map<String, Object>> updateCustomer(UUID id, CustomersEntity customer) {
        Map<String, Object> response = new HashMap<>();
        Optional<CustomersEntity> customerFound = customersRepository.findById(id);
        if (customerFound.isPresent()) {
            CustomersEntity existingCustomer = customerFound.get();
            existingCustomer.setName(customer.getName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPhone(customer.getPhone());
            customersRepository.save(existingCustomer);
            response.put("Status", "Customer updated successfully");
            response.put("UpdatedCustomer", existingCustomer);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Status", "Customer not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Map<String, Object>> deleteCustomer(UUID id) {
        Map<String, Object> response = new HashMap<>();
        Optional<CustomersEntity> customerFound = customersRepository.findById(id);
        if (customerFound.isPresent()) {
            customersRepository.deleteById(id);
            response.put("Status", "Customer deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Status", "Customer not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}

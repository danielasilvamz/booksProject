package com.usbbog.booksproject.services;

import com.usbbog.booksproject.entities.OrdersEntity;
import com.usbbog.booksproject.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    public ResponseEntity<Map<String, Object>> getAllOrders() {
        Map<String, Object> response = new HashMap<>();
        List<OrdersEntity> ordersList = ordersRepository.findAll();
        response.put("Orders", ordersList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> getOrderById(UUID id) {
        Map<String, Object> response = new HashMap<>();
        Optional<OrdersEntity> orderFound = ordersRepository.findById(id);
        if (orderFound.isPresent()) {
            response.put("Order", orderFound.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Error", "Order not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Map<String, Object>> createOrder(OrdersEntity order) {
        Map<String, Object> response = new HashMap<>();
        order.setId(UUID.randomUUID());
        if (ordersRepository.existsById(order.getId())) {
            response.put("Status", "Order already exists");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } else {
            OrdersEntity newOrder = ordersRepository.save(order);
            response.put("Status", "Order created successfully");
            response.put("Order", newOrder);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Map<String, Object>> updateOrder(UUID id, OrdersEntity order) {
        Map<String, Object> response = new HashMap<>();
        Optional<OrdersEntity> orderFound = ordersRepository.findById(id);
        if (orderFound.isPresent()) {
            OrdersEntity existingOrder = orderFound.get();
            existingOrder.setCustomerId(order.getCustomerId());
            existingOrder.setBookId(order.getBookId());
            existingOrder.setAmount(order.getAmount());
            ordersRepository.save(existingOrder);
            response.put("Status", "Order updated successfully");
            response.put("UpdatedOrder", existingOrder);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Status", "Order not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Map<String, Object>> deleteOrder(UUID id) {
        Map<String, Object> response = new HashMap<>();
        Optional<OrdersEntity> orderFound = ordersRepository.findById(id);
        if (orderFound.isPresent()) {
            ordersRepository.deleteById(id);
            response.put("Status", "Order deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Status", "Order not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}

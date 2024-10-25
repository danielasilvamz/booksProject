package com.usbbog.booksproject.controllers;

import com.usbbog.booksproject.entities.OrdersEntity;
import com.usbbog.booksproject.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        return ordersService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable UUID id) {
        return ordersService.getOrderById(id);
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrdersEntity order) {
        return ordersService.createOrder(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable UUID id, @RequestBody OrdersEntity order) {
        return ordersService.updateOrder(id, order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable UUID id) {
        return ordersService.deleteOrder(id);
    }
}

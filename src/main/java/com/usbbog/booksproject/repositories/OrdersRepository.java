package com.usbbog.booksproject.repositories;

import com.usbbog.booksproject.entities.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface OrdersRepository extends JpaRepository<OrdersEntity, UUID>{
}

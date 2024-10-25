package com.usbbog.booksproject.repositories;

import com.usbbog.booksproject.entities.CustomersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
public interface CustomersRepository extends JpaRepository<CustomersEntity, UUID>{
}

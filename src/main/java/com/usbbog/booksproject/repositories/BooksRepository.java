package com.usbbog.booksproject.repositories;

import com.usbbog.booksproject.entities.BooksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BooksRepository extends JpaRepository<BooksEntity, UUID> {
    //clase de jpa: tiene todas las consultas completas
}

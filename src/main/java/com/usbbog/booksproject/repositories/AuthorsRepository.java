package com.usbbog.booksproject.repositories;

import com.usbbog.booksproject.entities.AuthorsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorsRepository extends JpaRepository<AuthorsEntity, UUID>{
}

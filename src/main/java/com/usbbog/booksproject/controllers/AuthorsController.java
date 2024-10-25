package com.usbbog.booksproject.controllers;

import com.usbbog.booksproject.entities.AuthorsEntity;
import com.usbbog.booksproject.services.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorsController {
    @Autowired
    private AuthorsService authorsService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllAuthors() {
        return authorsService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable UUID id) {
        return authorsService.getAuthorById(id);
    }

    @PostMapping
    public ResponseEntity<?> createAuthor(@RequestBody AuthorsEntity author) {
        return authorsService.createAuthor(author);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateAuthor(@PathVariable UUID id, @RequestBody AuthorsEntity author) {
        return authorsService.updateAuthor(id, author);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteAuthor(@PathVariable UUID id) {
        return authorsService.deleteAuthor(id);
    }
}
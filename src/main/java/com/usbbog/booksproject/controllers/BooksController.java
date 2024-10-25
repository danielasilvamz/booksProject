package com.usbbog.booksproject.controllers;

import com.usbbog.booksproject.entities.BooksEntity;
import com.usbbog.booksproject.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
public class BooksController {
    @Autowired
    private BooksService booksService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllBooks() {
        return booksService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable UUID id) {
        return booksService.getBookById(id);
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody BooksEntity book) {
        return booksService.createBook(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateBook(
            @PathVariable UUID id, @RequestBody BooksEntity book) {
        return booksService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteBook(@PathVariable UUID id) {
        return booksService.deleteBook(id);
    }
}

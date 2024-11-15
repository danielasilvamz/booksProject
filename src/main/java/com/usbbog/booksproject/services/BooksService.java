package com.usbbog.booksproject.services;

import com.usbbog.booksproject.entities.BooksEntity;
import com.usbbog.booksproject.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BooksService {
    @Autowired
    //uso de base de datos con ORM
    private BooksRepository booksRepository;

    // findAll() -> select * from tabla -> lo entrega formateada
    // el metodo se actualiza cada vez que se hagan cambios en la BD
    public ResponseEntity<Map<String, Object>> getAllBooks() {
        Map<String, Object> response = new HashMap<>();
        List<BooksEntity> booksList = booksRepository.findAll();
        response.put("Books", booksList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // Optional -> si retorna nulos no crea error
    public ResponseEntity<Map<String, Object>> getBookById(UUID id) {
        Map<String, Object> response = new HashMap<>();
        Optional<BooksEntity> bookFound = booksRepository.findById(id);
        if (bookFound.isPresent()) {
            response.put("Book", bookFound.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Error", "Book not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Map<String, Object>> createBook(BooksEntity book) {
        Map<String, Object> response = new HashMap<>();
        book.setId(UUID.randomUUID());
        if (booksRepository.existsById(book.getId())) {
            response.put("Status", "Item already exists");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } else {
            BooksEntity newBook = booksRepository.save(book);
            response.put("Status", "Item inserted successfully");
            response.put("Book", newBook);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Map<String, Object>> updateBook(UUID id, BooksEntity book) {
        Map<String, Object> response = new HashMap<>();
        Optional<BooksEntity> bookFound = booksRepository.findById(id);
        if (bookFound.isPresent()) {
            BooksEntity existingBook = bookFound.get();
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setCategory(book.getCategory());
            existingBook.setPrice(book.getPrice());
            existingBook.setStock(book.getStock());
            booksRepository.save(existingBook);
            response.put("Status", "Book updated successfully");
            response.put("UpdatedBook", existingBook);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Status", "Book not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Map<String, Object>> deleteBook(UUID id) {
        Map<String, Object> response = new HashMap<>();
        Optional<BooksEntity> bookFound = booksRepository.findById(id);
        if (bookFound.isPresent()) {
            booksRepository.deleteById(id);
            response.put("Status", "Book deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            response.put("Status", "Book not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}

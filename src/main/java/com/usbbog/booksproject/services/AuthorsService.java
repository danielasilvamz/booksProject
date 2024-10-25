package com.usbbog.booksproject.services;

import com.usbbog.booksproject.entities.AuthorsEntity;
import com.usbbog.booksproject.repositories.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthorsService {
    @Autowired
    private AuthorsRepository authorsRepository;

    public ResponseEntity<Map<String, Object>> getAllAuthors() {
        Map<String, Object> response = new HashMap<>();
        List<AuthorsEntity> authorsList = authorsRepository.findAll();
        response.put("Authors", authorsList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> getAuthorById(UUID id) {
        Map<String, Object> response = new HashMap<>();
        Optional<AuthorsEntity> authorFound = authorsRepository.findById(id);
        if (authorFound.isPresent()) {
            response.put("Author", authorFound.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Author", "Author not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Map<String, Object>> createAuthor(AuthorsEntity author) {
        Map<String, Object> response = new HashMap<>();
        author.setId(UUID.randomUUID());
        if (authorsRepository.existsById(author.getId())) {
            response.put("Status", "Item already exists");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } else {
            AuthorsEntity newBook = authorsRepository.save(author);
            response.put("Status", "Item inserted successfully");
            response.put("Author", newBook);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Map<String, Object>> updateAuthor(UUID id, AuthorsEntity author) {
        Map<String, Object> response = new HashMap<>();
        Optional<AuthorsEntity> authorFound = authorsRepository.findById(id);
        if (authorFound.isPresent()) {
            AuthorsEntity existingAuthor = authorFound.get();
            existingAuthor.setName(author.getName());
            existingAuthor.setNationality(author.getNationality());
            authorsRepository.save(existingAuthor);
            response.put("Status", "Author updated successfully");
            response.put("UpdatedAuthor", existingAuthor);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Status", "Author not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Map<String, Object>> deleteAuthor(UUID id) {
        Map<String, Object> response = new HashMap<>();
        Optional<AuthorsEntity> authorFound = authorsRepository.findById(id);
        if (authorFound.isPresent()) {
            authorsRepository.deleteById(id);
            response.put("Status", "Author deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            response.put("Status", "Author not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}

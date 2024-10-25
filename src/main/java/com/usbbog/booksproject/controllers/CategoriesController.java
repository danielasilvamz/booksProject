package com.usbbog.booksproject.controllers;

import com.usbbog.booksproject.entities.CategoriesEntity;
import com.usbbog.booksproject.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        return categoriesService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable UUID id) {
        return categoriesService.getCategoryById(id);
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoriesEntity category) {
        return categoriesService.createCategory(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable UUID id, @RequestBody CategoriesEntity category) {
        return categoriesService.updateCategory(id, category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable UUID id) {
        return categoriesService.deleteCategory(id);
    }
}

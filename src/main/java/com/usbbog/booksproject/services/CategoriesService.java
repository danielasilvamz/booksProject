package com.usbbog.booksproject.services;

import com.usbbog.booksproject.entities.CategoriesEntity;
import com.usbbog.booksproject.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;

    public ResponseEntity<Map<String, Object>> getAllCategories() {
        Map<String, Object> response = new HashMap<>();
        List<CategoriesEntity> categoriesList = categoriesRepository.findAll();
        response.put("Categories", categoriesList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> getCategoryById(UUID id) {
        Map<String, Object> response = new HashMap<>();
        Optional<CategoriesEntity> categoryFound = categoriesRepository.findById(id);
        if (categoryFound.isPresent()) {
            response.put("Category", categoryFound.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Error", "Category not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Map<String, Object>> createCategory(CategoriesEntity category) {
        Map<String, Object> response = new HashMap<>();
        category.setId(UUID.randomUUID());
        if (categoriesRepository.existsById(category.getId())) {
            response.put("Status", "Category already exists");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } else {
            CategoriesEntity newCategory = categoriesRepository.save(category);
            response.put("Status", "Category created successfully");
            response.put("Category", newCategory);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Map<String, Object>> updateCategory(UUID id, CategoriesEntity category) {
        Map<String, Object> response = new HashMap<>();
        Optional<CategoriesEntity> categoryFound = categoriesRepository.findById(id);
        if (categoryFound.isPresent()) {
            CategoriesEntity existingCategory = categoryFound.get();
            existingCategory.setName(category.getName());
            existingCategory.setDescription(category.getDescription());
            categoriesRepository.save(existingCategory);
            response.put("Status", "Category updated successfully");
            response.put("UpdatedCategory", existingCategory);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Status", "Category not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Map<String, Object>> deleteCategory(UUID id) {
        Map<String, Object> response = new HashMap<>();
        Optional<CategoriesEntity> categoryFound = categoriesRepository.findById(id);
        if (categoryFound.isPresent()) {
            categoriesRepository.deleteById(id);
            response.put("Status", "Category deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Status", "Category not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}

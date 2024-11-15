package com.usbbog.booksproject.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class BooksEntity {
    @Id
    private UUID id;
    private String title;
    private String author;
    private String category;
    private double price;
    private int stock;

    @PrePersist
    public void generateUUID() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}

/*
{
    "title": "Mis días en la librería de Morisaki",
    "author": "Satoshi Yagisawa",
    "category": "Literatura Universal",
    "price": 120.00,
    "stock": 15
}
 */
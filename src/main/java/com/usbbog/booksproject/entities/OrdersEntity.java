package com.usbbog.booksproject.entities;

import jakarta.persistence.*;
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
public class OrdersEntity {
    @Id
    private UUID id;
    private String customerName;
    private String bookName;
    private int amount;

    @PrePersist
    public void generateUUID() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}

/*
{
    "customerName": "Daniela Silva",
    "bookName": "Mis días en la librería de Morisaki",
    "amount": 2
}
 */
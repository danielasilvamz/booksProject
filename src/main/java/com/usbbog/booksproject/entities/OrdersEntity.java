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
    private String customerId;
    private String BookId;
    private int Amount;

    @PrePersist
    public void generateUUID() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}

/*
{
    "customerId": "a917b400-a1ae-4e2b-aff2-8b288034a671",
    "bookId": "a917b400-a1ae-4e2b-aff2-8b288034a671",
    "amount": 2
}
 */
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
public class CustomersEntity {
    @Id
    private UUID id;
    private String name;
    private String email;
    private String phone;

    @PrePersist
    public void generateUUID() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}

/*
{
    "name": "Daniela Silva",
    "email": "daniela@gmail.com",
    "phone": "3112524845"
}
 */

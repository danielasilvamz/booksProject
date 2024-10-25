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
public class CategoriesEntity {
    @Id
    private UUID id;
    private String name;
    private String description;

    @PrePersist
    public void generateUUID() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}

/*
{
    "name": "Literatura Universal",
    "description": "Obras literarias de relevancia mundial, atemporales y representativas de diversas culturas"
}
 */

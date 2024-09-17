package org.example.rssamocatclone.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
}

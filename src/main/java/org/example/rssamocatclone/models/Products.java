package org.example.rssamocatclone.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String description;

    private int price;

    @Column(name = "category_id")
    @OneToOne(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Categories categoriesId;

    @Column(name = "stock_quantity")
    private int stockQuantity;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;
}

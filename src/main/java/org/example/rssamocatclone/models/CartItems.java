package org.example.rssamocatclone.models;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "cart_id")
    private int cartId;
    @Column(name = "product_id")

    private int productId;
    private int quantity;
}

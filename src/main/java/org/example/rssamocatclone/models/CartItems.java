package org.example.rssamocatclone.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cart_items")
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "cart_id", foreignKey = @ForeignKey(name = "FK_CART"), nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "FK_PRODUCT"), nullable = false)
    private Products product;

    @Column(name = "quantity")
    private Integer quantity;
}

package org.example.rssamocatclone.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "order_items")
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "FK_ORDER"), nullable = false)
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "FK_PRODUCT"), nullable = false)
    private Products product;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price_per_unit")
    private Double pricePerUnit;

}

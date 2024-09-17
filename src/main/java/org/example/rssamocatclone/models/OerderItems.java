package org.example.rssamocatclone.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "order_items")
public class OerderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    private Orders orderId;

    @OneToMany(mappedBy = "product_id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Products> productId;

    private int quantity;

    @Column(name = "price_per_unit")
    private int pricePerUnit;

}

package org.example.rssamocatclone.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "order_items")
public class OerderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "order_id")
    @OneToOne(mappedBy = "id",fetch = FetchType.EAGER)
    private Orders orderId;
    @Column(name = "product_id")
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Products> productId;
    private int quantity;
    @Column(name = "price_per_unit")
    private int pricePerUnit;

}

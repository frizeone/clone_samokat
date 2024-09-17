package org.example.rssamocatclone.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_id")
    @OneToOne(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Users users;

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "status_id")
    @OneToOne(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private OrderStatus statusId;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "update_at")
    private String updatedAt;

}

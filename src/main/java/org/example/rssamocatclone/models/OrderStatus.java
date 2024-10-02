package org.example.rssamocatclone.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "order_status")
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "status_name")
    private String statusName;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
    private List<Orders> orders;

}

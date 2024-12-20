package org.example.rssamocatclone.dto;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.List;

public class OrdersDTO extends RepresentationModel<OrdersDTO> {

    private int id;
    private int userId;
    private Double totalPrice;
    private String status;
    private LocalDateTime createdAt;
    private List<OrderItemsDTO> orderItems;

    // Обновленный конструктор
    public OrdersDTO(int userId, Double totalPrice, String status) {
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = LocalDateTime.now(); // Присваиваем текущую дату и время
    }

    // Конструктор для создания по ID
    public OrdersDTO(int id, int userId, Double totalPrice, String status, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
    }

    public OrdersDTO() {
    }

    // Getters и Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<OrderItemsDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemsDTO> orderItems) {
        this.orderItems = orderItems;
    }
    public String logMethod(){
        return "[" + LocalDateTime.now().toString() + "]" +  " -> " + this.userId + " " + this.totalPrice + " " + this.status + " " + this.createdAt + " " + this.orderItems ;
    }
}

package org.example.rssamocatclone.dto;

import java.time.LocalDateTime;
import java.util.List;


public class OrderItemsDTO {

    private int id;
    private int orderId;
    private int productId;
    private Integer quantity;
    private Double pricePerUnit;

    public OrderItemsDTO() {
    }

    public OrderItemsDTO(int productId, Integer quantity, Double pricePerUnit) {
        this.productId = productId;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
    public String logMethod(){
        return "[" + LocalDateTime.now().toString() + "]" +  " -> " + this.orderId + " " + this.productId + " " + this.quantity + " " + this.pricePerUnit;
    }
}

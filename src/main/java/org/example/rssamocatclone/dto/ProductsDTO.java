package org.example.rssamocatclone.dto;


import java.time.LocalDateTime;

public class ProductsDTO {


    private int id;

    private String name;

    private String description;

    private Double price;

    private String categoryName;

    private int stockQuantity;

    private String createdAt;

    private String updatedAt;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String logMethod(){
        return "[" + LocalDateTime.now().toString() + "]" +  " -> " + this.getName() + " -> " + this.getDescription() + " -> " + this.getPrice() + " -> " + this.getCategoryName() + " -> " + this.getStockQuantity() + " -> " + this.getUpdatedAt();
    }
}


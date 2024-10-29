package org.example.rssamocatclone.GraphqlClass;

public class OrderItemInput {
    private int productId;
    private Integer quantity;
    private Double pricePerUnit;

    // Геттеры и сеттеры

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
}


package org.example.rssamocatclone.dto;


import org.example.rssamocatclone.models.CartItems;

import java.util.List;

public class CartDTO {

    private int id;

    private int userId;

    private String createdAt;

    private List<CartItemsDTO> cartItems;

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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public List<CartItemsDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemsDTO> cartItems) {
        this.cartItems = cartItems;
    }
}

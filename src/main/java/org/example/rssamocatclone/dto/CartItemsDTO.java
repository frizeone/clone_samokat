package org.example.rssamocatclone.dto;

import java.time.LocalDateTime;
import java.util.List;


public class CartItemsDTO {

    private int id;

    private int cartDTOId;

    private int productId;

    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCartDTOId() {
        return cartDTOId;
    }

    public void setCartDTOId(int cartDTOId) {
        this.cartDTOId = cartDTOId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String logMethod(){
        return "[" + LocalDateTime.now().toString() + "]" +  " -> " + this.cartDTOId + " " + this.productId + " " + this.quantity ;
    }
}

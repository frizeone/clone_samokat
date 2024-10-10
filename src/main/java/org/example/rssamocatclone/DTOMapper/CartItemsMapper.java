package org.example.rssamocatclone.DTOMapper;

import org.example.rssamocatclone.dto.CartItemsDTO;
import org.example.rssamocatclone.models.Cart;
import org.example.rssamocatclone.models.CartItems;
import org.example.rssamocatclone.models.Products;

public class CartItemsMapper {
    public static CartItemsDTO toDTO(CartItems cartItem) {
        CartItemsDTO dto = new CartItemsDTO();
        dto.setId(cartItem.getId());
        dto.setProductId(cartItem.getProduct().getId());
        dto.setQuantity(cartItem.getQuantity());
        return dto;
    }

    public static CartItems toEntity(CartItemsDTO dto, Cart cart, Products product) {
        CartItems cartItem = new CartItems();
        cartItem.setQuantity(dto.getQuantity());
        return cartItem;
    }
}

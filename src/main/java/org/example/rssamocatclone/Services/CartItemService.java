package org.example.rssamocatclone.Services;

import org.example.rssamocatclone.dto.CartItemsDTO;

import java.util.List;

public interface CartItemService {
    CartItemsDTO createCartItem(CartItemsDTO cartItemsDTO);

    CartItemsDTO updateCartItem(int id, CartItemsDTO cartItemsDTO);

    CartItemsDTO getCartItemById(int id);

    List<CartItemsDTO> getAllCartItems();

    void deleteCartItem(int id);
}

package org.example.rssamocatclone.Services;

import org.example.rssamocatclone.dto.CartDTO;
import org.example.rssamocatclone.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartService extends JpaRepository<Cart, Integer> {

    CartDTO createCart(CartDTO cartDTO);

    CartDTO getCartById(int id);

    CartDTO getCartByUserId(int userId);

    CartDTO updateCart(int id, CartDTO cartDTO);

    void deleteCart(int id);
}

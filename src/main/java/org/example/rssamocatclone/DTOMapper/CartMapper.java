package org.example.rssamocatclone.DTOMapper;

import org.example.rssamocatclone.dto.CartDTO;
import org.example.rssamocatclone.models.Cart;

import java.util.stream.Collectors;

public class CartMapper {
        public static CartDTO toDTO(Cart cart) {
            CartDTO dto = new CartDTO();
            dto.setId(cart.getId());
            dto.setUserId(cart.getUser().getId());
            dto.setCartItems(cart.getCartItems().stream()
                    .map(CartItemsMapper::toDTO)
                    .collect(Collectors.toList()));
            return dto;
        }

        public static Cart toEntity(CartDTO dto) {
            Cart cart = new Cart();
            return cart;
        }
}

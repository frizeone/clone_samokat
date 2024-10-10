package org.example.rssamocatclone.Controllers;

import org.example.rssamocatclone.Services.CartItemService;
import org.example.rssamocatclone.dto.CartItemsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {
    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }


    @PostMapping
    public ResponseEntity<CartItemsDTO> createCartItem(@RequestBody CartItemsDTO cartItemsDTO) {
        CartItemsDTO createdCartItem = cartItemService.createCartItem(cartItemsDTO);
        return ResponseEntity.ok(createdCartItem);
    }


    @GetMapping
    public ResponseEntity<List<CartItemsDTO>> getAllCartItems() {
        List<CartItemsDTO> cartItems = cartItemService.getAllCartItems();
        return ResponseEntity.ok(cartItems);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CartItemsDTO> getCartItemById(@PathVariable int id) {
        CartItemsDTO cartItem = cartItemService.getCartItemById(id);
        return ResponseEntity.ok(cartItem);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CartItemsDTO> updateCartItem(@PathVariable int id, @RequestBody CartItemsDTO cartItemsDTO) {
        CartItemsDTO updatedCartItem = cartItemService.updateCartItem(id, cartItemsDTO);
        return ResponseEntity.ok(updatedCartItem);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable int id) {
        cartItemService.deleteCartItem(id);
        return ResponseEntity.noContent().build();
    }
}

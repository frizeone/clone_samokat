package org.example.rssamocatclone.Controllers;

import org.example.rssamocatclone.Services.CartItemService;
import org.example.rssamocatclone.dto.CartItemsDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {
    static final String EXCHANGE_NAME = "testExchange";

    private final RabbitTemplate rabbitTemplate;

    private final CartItemService cartItemService;

    public CartItemController(RabbitTemplate rabbitTemplate, CartItemService cartItemService) {
        this.rabbitTemplate = rabbitTemplate;
        this.cartItemService = cartItemService;
    }


    @PostMapping
    public ResponseEntity<CartItemsDTO> createCartItem(@RequestBody CartItemsDTO cartItemsDTO) {
        CartItemsDTO createdCartItem = cartItemService.createCartItem(cartItemsDTO);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[CartItemController][Сработал метод createCartItem]" + createdCartItem.logMethod());
        return ResponseEntity.ok(createdCartItem);
    }


    @GetMapping
    public ResponseEntity<List<CartItemsDTO>> getAllCartItems() {
        List<CartItemsDTO> cartItems = cartItemService.getAllCartItems();
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[CartItemController][Сработал метод getAllCartItems]");
        return ResponseEntity.ok(cartItems);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CartItemsDTO> getCartItemById(@PathVariable int id) {
        CartItemsDTO cartItem = cartItemService.getCartItemById(id);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[CartItemController][Сработал метод getCartItemById]" + cartItem.logMethod());
        return ResponseEntity.ok(cartItem);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CartItemsDTO> updateCartItem(@PathVariable int id, @RequestBody CartItemsDTO cartItemsDTO) {
        CartItemsDTO updatedCartItem = cartItemService.updateCartItem(id, cartItemsDTO);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[CartItemController][Сработал метод updateCartItem]" + updatedCartItem.logMethod());
        return ResponseEntity.ok(updatedCartItem);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable int id) {
        cartItemService.deleteCartItem(id);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[CartItemController][Сработал метод deleteCartItem][Удалена элемент карзины с id = {" + id + "}]");
        return ResponseEntity.noContent().build();
    }
}

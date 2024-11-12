package org.example.rssamocatclone.Controllers;

import org.example.rssamocatclone.Services.CartService;
import org.example.rssamocatclone.dto.CartDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    static final String EXCHANGE_NAME = "testExchange";

    private final RabbitTemplate rabbitTemplate;

    private final CartService cartService;

    public CartController(RabbitTemplate rabbitTemplate, CartService cartService) {
        this.rabbitTemplate = rabbitTemplate;
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<CartDTO> createCart(@RequestBody CartDTO cartDTO) {
        CartDTO createdCart = cartService.createCart(cartDTO);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[CartController][Сработал метод createCart]" + createdCart.logMethod());
        return ResponseEntity.ok(createdCart);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable int id) {
        CartDTO cartDTO = cartService.getCartById(id);
        if (cartDTO != null) {
            rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[CartController][Сработал метод getCartById][+]" + cartDTO.logMethod());
            return ResponseEntity.ok(cartDTO);
        } else {
            rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[CartController][Сработал метод getCartById][-]" + cartDTO.logMethod());
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<CartDTO> getCartByUserId(@PathVariable int userId) {
        CartDTO cartDTO = cartService.getCartByUserId(userId);
        if (cartDTO != null) {
            rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[CartController][Сработал метод getCartByUserId][+]" + cartDTO.logMethod());
            return ResponseEntity.ok(cartDTO);
        } else {
            rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[CartController][Сработал метод getCartByUserId][-]" + cartDTO.logMethod());
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<CartDTO> updateCart(@PathVariable int id, @RequestBody CartDTO cartDTO) {
        CartDTO updatedCart = cartService.updateCart(id, cartDTO);
        if (updatedCart != null) {
            rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[CartController][Сработал метод updateCart][+]" + cartDTO.logMethod());
            return ResponseEntity.ok(updatedCart);
        } else {
            rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[CartController][Сработал метод updateCart][-]" + cartDTO.logMethod());
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable int id) {
        cartService.deleteCart(id);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[CartController][Сработал метод deleteCart][Удалена карзина с id = {" + id + "}]");
        return ResponseEntity.noContent().build();
    }
}
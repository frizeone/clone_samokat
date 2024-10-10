package org.example.rssamocatclone.Services.ServicesImpl;

import org.example.rssamocatclone.DTOMapper.CartItemsMapper;
import org.example.rssamocatclone.Services.CartItemService;
import org.example.rssamocatclone.dto.CartItemsDTO;
import org.example.rssamocatclone.models.Cart;
import org.example.rssamocatclone.models.CartItems;
import org.example.rssamocatclone.models.Products;
import org.example.rssamocatclone.repository.CartItemRepository;
import org.example.rssamocatclone.repository.CartRepository;
import org.example.rssamocatclone.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;

    private final CartRepository cartRepository;

    private final ProductsRepository productsRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository, CartRepository cartRepository, ProductsRepository productsRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.productsRepository = productsRepository;
    }

    // Create CartItem
    public CartItemsDTO createCartItem(CartItemsDTO cartItemsDTO) {
        Cart cart = cartRepository.findById(cartItemsDTO.getCartDTOId()).orElseThrow();
        Products product = productsRepository.findById(cartItemsDTO.getProductId()).orElseThrow();

        CartItems cartItem = CartItemsMapper.toEntity(cartItemsDTO, cart, product);
        cartItem = cartItemRepository.save(cartItem);
        return CartItemsMapper.toDTO(cartItem);
    }

    // Get all CartItems
    public List<CartItemsDTO> getAllCartItems() {
        return cartItemRepository.findAll()
                .stream()
                .map(CartItemsMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get CartItem by ID
    public CartItemsDTO getCartItemById(int id) {
        CartItems cartItem = cartItemRepository.findById(id).orElseThrow();
        return CartItemsMapper.toDTO(cartItem);
    }

    // Update CartItem
    public CartItemsDTO updateCartItem(int id, CartItemsDTO cartItemsDTO) {
        CartItems existingCartItem = cartItemRepository.findById(id).orElseThrow();

        Cart cart = cartRepository.findById(cartItemsDTO.getCartDTOId()).orElseThrow();
        Products product = productsRepository.findById(cartItemsDTO.getProductId()).orElseThrow();

        existingCartItem.setQuantity(cartItemsDTO.getQuantity());
        existingCartItem.setCart(cart);
        existingCartItem.setProduct(product);

        CartItems updatedCartItem = cartItemRepository.save(existingCartItem);
        return CartItemsMapper.toDTO(updatedCartItem);
    }

    // Delete CartItem
    public void deleteCartItem(int id) {
        cartItemRepository.deleteById(id);
    }
}

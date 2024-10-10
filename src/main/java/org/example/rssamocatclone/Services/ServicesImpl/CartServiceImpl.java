package org.example.rssamocatclone.Services.ServicesImpl;

import org.example.rssamocatclone.DTOMapper.CartItemsMapper;
import org.example.rssamocatclone.DTOMapper.CartMapper;
import org.example.rssamocatclone.Services.CartService;
import org.example.rssamocatclone.dto.CartDTO;
import org.example.rssamocatclone.models.Cart;
import org.example.rssamocatclone.repository.CartRepository;
import org.example.rssamocatclone.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    // CREATE (POST)
    public CartDTO createCart(CartDTO cartDTO) {
        Cart cart = CartMapper.toEntity(cartDTO);
        cart = cartRepository.save(cart);
        return CartMapper.toDTO(cart);
    }

    // READ (GET) by ID
    public CartDTO getCartById(int id) {
        Optional<Cart> cart = cartRepository.findById(id);
        return cart.map(CartMapper::toDTO).orElse(null); // Возвращаем DTO или null
    }

    // READ (GET) by UserId
    public CartDTO getCartByUserId(int userId) {
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        return cart.map(CartMapper::toDTO).orElse(null);
    }

    // UPDATE (PUT)
    public CartDTO updateCart(int id, CartDTO cartDTO) {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            // Обновляем поля
            cart.setCartItems(CartMapper.toEntity(cartDTO).getCartItems());
            cart = cartRepository.save(cart);
            return CartMapper.toDTO(cart);
        } else {
            return null; // Если корзина не найдена
        }
    }

    // DELETE
    public void deleteCart(int id) {
        cartRepository.deleteById(id);
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Cart> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Cart> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<Cart> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Cart getOne(Integer integer) {
        return null;
    }

    @Override
    public Cart getById(Integer integer) {
        return null;
    }

    @Override
    public Cart getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Cart> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Cart> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Cart> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Cart> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Cart> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Cart> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Cart, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Cart> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Cart> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<Cart> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<Cart> findAll() {
        return List.of();
    }

    @Override
    public List<Cart> findAllById(Iterable<Integer> integers) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Cart entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Cart> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Cart> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Cart> findAll(Pageable pageable) {
        return null;
    }
}

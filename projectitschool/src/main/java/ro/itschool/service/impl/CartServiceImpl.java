package ro.itschool.service.impl;

import ro.itschool.entity.Cart;
import ro.itschool.repository.CartRepository;
import ro.itschool.service.CartService;

import java.util.List;
import java.util.UUID;

public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart findById(UUID id) {
        return cartRepository.findById(id);
    }

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void updateCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void closeCart(Cart cart) {
        cart.setPaid(true);
    }
}

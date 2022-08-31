package ro.itschool.service.impl;

import org.springframework.stereotype.Service;
import ro.itschool.entity.Cart;
import ro.itschool.entity.Tower;
import ro.itschool.repository.CartRepository;
import ro.itschool.service.CartService;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart findById(Long id) {
        return cartRepository.findById(id).orElseThrow();
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

    @Override
    public void deleteById(Long id) {

    }
}

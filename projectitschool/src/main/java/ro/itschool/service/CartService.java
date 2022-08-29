package ro.itschool.service;

import org.springframework.stereotype.Service;
import ro.itschool.entity.Cart;
import ro.itschool.entity.Tower;

import java.util.List;
import java.util.UUID;

@Service
public interface CartService {

    Cart findById(UUID id);

    List<Cart> findAll();

    void deleteById(UUID id);

    Cart saveCart(Cart cart);

    void updateCart(Cart cart);

    void closeCart(Cart cart);

}

package ro.itschool.service;

import org.springframework.stereotype.Service;
import ro.itschool.entity.Cart;
import ro.itschool.entity.Tower;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public interface CartService {

    List<Cart> findAll();

    Cart findById(Long id);

    Cart saveCart(Cart cart);

    void updateCart(Cart cart);

    void closeCart(Cart cart);

    void deleteById(Long id);

}

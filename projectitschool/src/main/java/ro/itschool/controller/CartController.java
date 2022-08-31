package ro.itschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.itschool.entity.Cart;
import ro.itschool.entity.Tower;
import ro.itschool.service.CartService;
import ro.itschool.service.TowerService;
import ro.itschool.service.UserService;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class CartController {

    @Autowired
    TowerService towerService;

    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;

    @RequestMapping("/addToCart/{id}")
    public String addToCart(@PathVariable("id") Long id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Cart> carts = Optional.of(userService.findUserByUserName(authentication.getName()).getCarts().stream().filter(Cart::isActive).collect(Collectors.toList())).orElse(new ArrayList<Cart>());
        if(carts.isEmpty()) {
            Cart cart = new Cart();
            cart.setUser(userService.findUserByUserName(authentication.getName()));
            cart.setActive(true);
            cart.getTowers().add(towerService.findById(id));
            Tower tower = towerService.findById(id);
            tower.getCarts().add(cart);
            cartService.saveCart(cart);
            towerService.saveTower(tower);
        }
        else {
            carts.get(0).getTowers().add(towerService.findById(id));
            Tower tower = towerService.findById(id);
            tower.getCarts().add(carts.get(0));
            cartService.saveCart(carts.get(0));
            towerService.saveTower(tower);
        }
        return "redirect:/shop";
    }
}

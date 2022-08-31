package ro.itschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.itschool.entity.Cart;
import ro.itschool.entity.Tower;
import ro.itschool.service.CartService;
import ro.itschool.service.TowerService;
import ro.itschool.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CheckoutController {
    @Autowired
    TowerService towerService;

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @RequestMapping("/checkout")
    public String checkout(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Cart> carts = userService.findUserByUserName(authentication.getName())
                .getCarts().stream().filter(Cart::isActive).toList();
        carts.get(0).calculatePrice();
            model.addAttribute("cartTowers", carts.get(0).getTowers());
            model.addAttribute("total", carts.get(0).getPrice());
        return "checkout";
    }

    @RequestMapping("/checkout-success")
    public String success() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Cart> carts = userService.findUserByUserName(authentication.getName())
                .getCarts().stream().filter(Cart::isActive).toList();
        carts.get(0).setActive(false);
        carts.get(0).setPaid(true);
        cartService.saveCart(carts.get(0));
        return "redirect:/index";
    }
}

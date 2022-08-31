package ro.itschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.itschool.entity.Cart;
import ro.itschool.entity.MyUser;
import ro.itschool.entity.Role;
import ro.itschool.entity.Tower;
import ro.itschool.service.TowerService;
import ro.itschool.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    TowerService towerService;


    @RequestMapping("/account")
    public String account(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUser user = userService.findUserByUserName(auth.getName());
        List<Tower> towers = userService.findUserByUserName(auth.getName()).getCarts().stream().filter(Cart::isActive)
                .map(Cart::getTowers).toList().stream().flatMap(Collection::stream).toList();
        user.getCarts().forEach(Cart::calculatePrice);
        model.addAttribute("cartTowers", towers);
        model.addAttribute("user", user.getFullName());
        model.addAttribute("userDetails", user);
        model.addAttribute("towers", towerService.findAll());
        model.addAttribute("shoppingCarts", user.getCarts());

        return "account";
    }
}

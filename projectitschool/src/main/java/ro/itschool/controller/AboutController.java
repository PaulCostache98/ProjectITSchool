package ro.itschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.itschool.entity.Cart;
import ro.itschool.entity.Tower;
import ro.itschool.service.TowerService;
import ro.itschool.service.UserService;

import java.util.Collection;
import java.util.List;

@Controller
public class AboutController {

    @Autowired
    UserService userService;

    @Autowired
    TowerService towerService;

    @RequestMapping("/about")
    String about(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Tower> towers = userService.findUserByUserName(authentication.getName()).getCarts().stream().filter(Cart::isActive)
                .map(Cart::getTowers).toList().stream().flatMap(Collection::stream).toList();
        model.addAttribute("cartTowers", towers);
        model.addAttribute("user", userService.findUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).getFullName());
        return "about";
    }
}

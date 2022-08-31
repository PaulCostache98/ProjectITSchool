package ro.itschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ro.itschool.entity.Cart;
import ro.itschool.entity.Tower;
import ro.itschool.service.TowerService;
import ro.itschool.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class TowerController {

    @Autowired
    TowerService towerService;

    @Autowired
    UserService userService;

    @RequestMapping("towers/{id}")
    String tower(@PathVariable int id, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("tower", towerService.findById(id));
        List<Tower> towers = new ArrayList<>();
        model.addAttribute("towers", towerService.findAll());
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("user", userService.findUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).getFullName());
            towers = userService.findUserByUserName(authentication.getName()).getCarts().stream().filter(Cart::isActive)
                    .map(Cart::getTowers).toList().stream().flatMap(Collection::stream).toList();
        }
        model.addAttribute("cartTowers", towers);
        return "tower";
    }
}

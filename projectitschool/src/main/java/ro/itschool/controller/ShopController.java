package ro.itschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.itschool.service.TowerService;
import ro.itschool.service.UserService;

@Controller
public class ShopController {

    @Autowired
    TowerService towerService;

    @Autowired
    UserService userService;

    @RequestMapping("/shop")
    String shop(Model model) {

        model.addAttribute("towers", towerService.findAll());
        model.addAttribute("user", userService.findUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "shop";
    }
}

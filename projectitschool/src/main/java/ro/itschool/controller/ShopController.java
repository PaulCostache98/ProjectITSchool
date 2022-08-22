package ro.itschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.itschool.service.TowerService;

@Controller
public class ShopController {

    @Autowired
    TowerService towerService;

    @RequestMapping("/shop")
    String shop(Model model) {

        model.addAttribute("towers", towerService.findAll());
        return "shop";
    }
}

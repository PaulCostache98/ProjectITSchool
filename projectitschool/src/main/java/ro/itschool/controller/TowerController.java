package ro.itschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ro.itschool.service.TowerService;
import ro.itschool.service.UserService;

@Controller
public class TowerController {

    @Autowired
    TowerService towerService;

    @Autowired
    UserService userService;

    @RequestMapping("towers/{id}")
    String tower(@PathVariable int id, Model model) {

        model.addAttribute("tower", towerService.findById(id));
        model.addAttribute("user", userService.findUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "tower";
    }
}

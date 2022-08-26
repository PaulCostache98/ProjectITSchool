package ro.itschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.itschool.service.UserService;

@Controller
public class AboutController {

    @Autowired
    UserService userService;

    @RequestMapping("/about")
    String about(Model model) {
        model.addAttribute("user", userService.findUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "about";
    }
}

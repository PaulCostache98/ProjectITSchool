package ro.itschool.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.itschool.entity.MyUser;
import ro.itschool.entity.Tower;
import ro.itschool.repository.UserRepository;
import ro.itschool.service.TowerService;
import ro.itschool.service.UserService;

import java.util.List;
import java.util.logging.Logger;


@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    TowerService towerService;

    @Autowired
    HttpServletRequest request;


    @RequestMapping("/login")
    public String login(Model model) {
        request.getSession();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Tower> towerList = towerService.findAll();
        Tower tower1 = towerList.remove(1);
        model.addAttribute("towers", towerList);
        model.addAttribute("tower1", tower1);
//        model.addAttribute("name", userService.findUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).getFullName());
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "redirect:/index";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login.html";
    }
}

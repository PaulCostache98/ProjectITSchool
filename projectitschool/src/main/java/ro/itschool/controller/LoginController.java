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
import ro.itschool.repository.UserRepository;
import ro.itschool.service.UserService;

import java.util.logging.Logger;


@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest request;


    @RequestMapping("/login")
    public String login(Model model) {
        request.getSession();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            return "login";
        }
        if(authentication.getPrincipal().toString().equalsIgnoreCase("anonymousUser")) {
            authentication.setAuthenticated(false);
            return "login";
        }
        if(!authentication.isAuthenticated()) {
            return "login";
        }


        System.out.println("*************************************** " + authentication.getName());
        return "index";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login.html";
    }
}

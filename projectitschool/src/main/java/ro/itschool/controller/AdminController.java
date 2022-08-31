package ro.itschool.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.itschool.entity.Cart;
import ro.itschool.entity.MyUser;
import ro.itschool.entity.Role;
import ro.itschool.entity.Tower;
import ro.itschool.exception.TowerNotFoundException;
import ro.itschool.exception.UserNotFoundException;
import ro.itschool.service.TowerService;
import ro.itschool.service.UserService;

import java.util.*;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    TowerService towerService;

    @Autowired
    HttpServletRequest request;


    @RequestMapping("/admin-page")
    public String adminPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Tower> towers =
                userService.findUserByUserName(authentication.getName()).getCarts().stream().filter(Cart::isActive)
                .map(Cart::getTowers).toList().stream().flatMap(Collection::stream).toList();
        model.addAttribute("cartTowers", towers);
        model.addAttribute("user", userService.findUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).getFullName());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("towers", towerService.findAll());
        MyUser user = new MyUser();
        return "admin-page";
    }

    @RequestMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable("id") Long id) throws UserNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(userService.findUserByUserName(authentication.getName()).isAdmin()) {
            MyUser user = userService.findById(id);
            userService.deleteById(id);
        }
        return "redirect:/admin-page";
    }

    @RequestMapping("/delete-tower/{id}")
    public String deleteTower(@PathVariable("id") Long id) throws TowerNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(userService.findUserByUserName(authentication.getName()).isAdmin()) {
            Tower tower = Optional.ofNullable(towerService.findById(id)).orElseThrow(TowerNotFoundException::new);
            towerService.deleteById(id);
        }
        return "redirect:/admin-page";
    }

    @RequestMapping("/update-user/{id}")
    public String updateUser(@PathVariable("id") Long id, Model model) throws UserNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Tower> towers = userService.findUserByUserName(authentication.getName()).getCarts().stream().filter(Cart::isActive)
                .map(Cart::getTowers).toList().stream().flatMap(Collection::stream).toList();
        model.addAttribute("cartTowers", towers);
        model.addAttribute("user", userService.findUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).getFullName());
        if(userService.findUserByUserName(authentication.getName()).isAdmin()) {
            MyUser user = userService.findById(id);
            model.addAttribute("savedUser", user);
        }

        return "update-user";
    }

    @GetMapping("/save-user")
    public String saveUser(Model model) {
        model.addAttribute("savedUser", new MyUser());
        return "save-user";
    }

    @PostMapping("/save-user")
    public String saveUser(@ModelAttribute MyUser user, Model model) throws UserNotFoundException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(Objects.equals(userService.findUserByUserName(authentication.getName()).getId(), user.getId())) {
            Set<Role> roles = userService.findById(user.getId()).getRoles();
            Set<Cart> carts = userService.findById(user.getId()).getCarts();
            user.setRoles(roles);
            user.setCarts(carts);
            model.addAttribute("savedUser", user);
            Authentication newAuthentication = new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), authentication.getAuthorities());
            userService.saveUser(user);

            SecurityContextHolder.getContext().setAuthentication(newAuthentication);
        }
        Set<Role> roles = userService.findById(user.getId()).getRoles();
        Set<Cart> carts = userService.findById(user.getId()).getCarts();
        user.setRoles(roles);
        user.setCarts(carts);
        model.addAttribute("savedUser", user);
        userService.saveUser(user);
        return "redirect:/admin-page";
    }

    @GetMapping("/add-user")
    public String addUser(Model model) {
        MyUser user = new MyUser();
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        model.addAttribute("user", user);

        return "add-user";
    }

    @PostMapping(value = "/add-user")
    public String addUser(@ModelAttribute("user") @RequestBody MyUser user) {
            user.setRoles(Collections.singleton(new Role("ROLE_USER")));
            userService.saveUser(user);
            return "redirect:/admin-page";
    }

    @RequestMapping("/make-admin/{id}")
    public String makeAdmin(@PathVariable("id") Long id, Model model) throws UserNotFoundException {
        Set<Role> roles = userService.findById(id).getRoles();
        roles.add(new Role("ROLE_ADMIN"));
        MyUser user = userService.findById(id);
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin-page";
    }

    @RequestMapping("/update-tower/{id}")
    public String updateTower(@PathVariable("id") Long id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Tower> towers = userService.findUserByUserName(authentication.getName()).getCarts().stream().filter(Cart::isActive)
                .map(Cart::getTowers).toList().stream().flatMap(Collection::stream).toList();
        model.addAttribute("cartTowers", towers);
        model.addAttribute("user", userService.findUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).getFullName());
        if(userService.findUserByUserName(authentication.getName()).isAdmin()) {
            Tower tower = towerService.findById(id);
            model.addAttribute("savedTower", tower);
        }
        return "update-tower";
    }

    @GetMapping("/save-tower")
    public String saveTower(Model model) {
        model.addAttribute("savedTower", new Tower());
        return "save-tower";
    }

    @PostMapping("/save-tower")
    public String saveTower(@ModelAttribute Tower tower, Model model) {
        Set<Cart> carts = towerService.findById(tower.getId()).getCarts();
        tower.setCarts(carts);
        towerService.saveTower(tower);
        return "redirect:/admin-page";
    }

    @GetMapping("/add-tower")
    public String addTower(Model model) {
        Tower tower = new Tower();

        model.addAttribute("tower", tower);

        return "add-tower";
    }

    @PostMapping(value = "/add-tower")
    public String addTower(@ModelAttribute("tower") @RequestBody Tower tower) {
        towerService.saveTower(tower);
        return "redirect:/admin-page";
    }
}

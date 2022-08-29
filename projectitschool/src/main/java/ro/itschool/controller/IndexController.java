package ro.itschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.itschool.entity.MyUser;
import ro.itschool.entity.Tower;
import ro.itschool.service.TowerService;
import ro.itschool.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Controller
public class IndexController {

    @Autowired
    private TowerService towerService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/index-logged-out"})
    String indexLoggedOut(Model model) {
        Random random = new Random();
        List<Tower> towerList = towerService.findAll();
        towerList.remove(0);
        Tower tower1 = towerService.findById(1);
        model.addAttribute("towers", towerList);
        model.addAttribute("tower1", tower1);
        return "index-logged-out";
    }

    @RequestMapping("/index")
    String index(Model model) {
        List<Tower> towerList = towerService.findAll();
        Tower tower1 = towerList.remove(1);
        model.addAttribute("towers", towerList);
        model.addAttribute("tower1", tower1);
        model.addAttribute("name", userService.findUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).getFullName());

        return "index";
    }
}

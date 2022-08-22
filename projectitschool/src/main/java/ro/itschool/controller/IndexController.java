package ro.itschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.itschool.entity.Tower;
import ro.itschool.service.TowerService;

import java.util.List;
import java.util.Random;

@Controller
public class IndexController {

    @Autowired
    private TowerService towerService;

    @RequestMapping(value = {"/index-logged-out", "/"})
    String indexLoggedOut(Model model) {
        Random random = new Random();
        List<Tower> towerList = towerService.findAll();
        Tower towerDummy = new Tower();
        towerDummy.setImageSource("https://3.grgs.ro/images/products/1/6472/2415300/normal/office-start-a3g-powered-by-asus-amd-athlon-3000g-35ghz-8gb-ddr4-500gb-ssd-amd-radeon-vega-3-2093c71e4b86babe85c6348d49222b6e.jpg");
        towerList.remove(0);
        model.addAttribute("towers", towerList);
        model.addAttribute("tower1", towerDummy);
        return "index-logged-out";
    }

    @RequestMapping("/index")
    String index(Model model) {
        return "index";
    }
}

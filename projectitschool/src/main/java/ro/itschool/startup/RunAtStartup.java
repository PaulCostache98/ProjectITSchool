package ro.itschool.startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ro.itschool.entity.MyUser;
import ro.itschool.entity.Role;
import ro.itschool.entity.Tower;
import ro.itschool.service.TowerService;
import ro.itschool.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Component
public class RunAtStartup {

    @Autowired
    private TowerService towerService;

    @Autowired
    private UserService userService;

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {

        Tower tower = new Tower("https://3.grgs.ro/images/products/1/6472/2415300/normal/office-start-a3g-powered-by-asus-amd-athlon-3000g-35ghz-8gb-ddr4-500gb-ssd-amd-radeon-vega-3-2093c71e4b86babe85c6348d49222b6e.jpg");
        tower.setName("PC 1");
        tower.setPrice(12222);

        Tower tower2 = new Tower("https://2.grgs.ro/images/products/1/2001/2253086/thumbnails/work-play-r315-powered-by-asus-amd-ryzen-5-5600g-16gb-ddr4-500gb-ssd-amd-radeon-r7-b7a75be786b8b7908e45aba868419413.jpg");
        tower2.setName("PC 2");
        tower2.setPrice(10000);

        Tower tower3 = new Tower("https://5.grgs.ro/images/products/1/2001/2366808/thumbnails/office-pro-c7h7-powered-by-asus-intel-i7-11700-25ghz-16gb-ddr4-512gb-ssd-gma-uhd-750-40f999d29f8492953ab86a459a6d9cf3.jpg");
        tower3.setName("PC 3");
        tower3.setPrice(20000);

        towerService.saveTower(tower);
        towerService.saveTower(tower2);
        towerService.saveTower(tower3);

        MyUser myUser = new MyUser();
        myUser.setUsername("user0");
        myUser.setPassword("user0");
        myUser.setRandomToken("randomToken");
        final Role roleUser = new Role("ROLE_USER");
        final Set<Role> roles = new HashSet<>();
        roles.add(roleUser);
        myUser.setRoles(roles);
        myUser.setEnabled(true);
        myUser.setAccountNonExpired(true);
        myUser.setAccountNonLocked(true);
        myUser.setCredentialsNonExpired(true);
        myUser.setEmail("user@gmail.com");
        myUser.setFullName("Bahaohha");
        myUser.setPasswordConfirm("user0");
        myUser.setRandomTokenEmail("randomToken");

        userService.saveUser(myUser);


    }
}

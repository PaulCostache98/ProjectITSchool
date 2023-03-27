package ro.itschool.startup;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ro.itschool.entity.Cart;
import ro.itschool.entity.MyUser;
import ro.itschool.entity.Role;
import ro.itschool.entity.Tower;
import ro.itschool.service.CartService;
import ro.itschool.service.TowerService;
import ro.itschool.service.UserService;

import java.io.FileReader;
import java.util.*;

@Component
public class RunAtStartup {

    @Autowired
    private TowerService towerService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {

        Tower tower = new Tower("https://3.grgs.ro/images/products/1/6472/2415300/normal/office-start-a3g-powered-by-asus-amd-athlon-3000g-35ghz-8gb-ddr4-500gb-ssd-amd-radeon-vega-3-2093c71e4b86babe85c6348d49222b6e.jpg");
        tower.setName("PC 1");
        tower.setModel("aaaa");
        tower.setPrice(12222);
        tower.setHasWarranty(true);
        tower.setWarrantyLength(5);
        tower.setProcessor("Intel i3");
        tower.setGPU("GTX 660");
        tower.setMotherboard("MSI bla bla");
        tower.setRAM("16GB DDR4");
        tower.setHasHDD(false);
        tower.setHasSSD(true);
        tower.setSSD("Samsung 1TB SSD");
        tower.setTowerCase("Ryzix Cool Blue");
        tower.setPowerSupply("550W");
        tower.setCooling("Nitrogen");
        String frontPanel = "2x USB, 3x VGA, 1x USB 3.0";
        tower.setFrontPanel(frontPanel);
        String backPanel = "5x USB, 2x HDMI, 3x USB 3.0, 1x Audio Jack";
        tower.setBackPanel(backPanel);
        String networking = "WiFi, LAN";
        tower.setNetworking(networking);
        tower.setHasOpticUnit(false);
        tower.setHasOperatingSystem(true);
        tower.setOperatingSystemName("Windows 11");

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
        myUser.setId(1L);
        myUser.setPassword("user0");
        myUser.setRandomToken("randomToken");
        final Role roleUser = new Role("ROLE_USER");
        final Role roleAdmin = new Role("ROLE_ADMIN");
        final Set<Role> roles = new HashSet<>();
        roles.add(roleUser);
        roles.add(roleAdmin);
        myUser.setRoles(roles);
        myUser.setEnabled(true);
        myUser.setAccountNonExpired(true);
        myUser.setAccountNonLocked(true);
        myUser.setCredentialsNonExpired(true);
        myUser.setEmail("user@gmail.com");
        myUser.setFullName("Bahaohha");
        myUser.setPasswordConfirm("user0");
        myUser.setRandomTokenEmail("randomToken");
        Cart cart = new Cart();
        cart.setPaid(true);
        cart.setTowers(List.of(tower));
        Set<Cart> carts = new HashSet<>();
        cart.calculatePrice();
        carts.add(cart);
        myUser.setCarts(carts);
        userService.saveUser(myUser);
        cartService.saveCart(cart);
        cart.setUser(myUser);
        tower.setCarts(Collections.singleton(cart));
        towerService.saveTower(tower);
        cartService.saveCart(cart);
        List<Tower> towers = readDataLineByLine("projectitschool/src/main/resources/towers.csv");
        System.out.println(towers);
        for (Tower towerSave: towers) {
            towerService.saveTower(towerSave);
        }

}

    public static List<Tower> readDataLineByLine(String fileName) {
        List<Tower> towerList = new ArrayList<>();
        try (FileReader fileReader = new FileReader(fileName)) {
            CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                Tower tower = new Tower(nextRecord[0], nextRecord[1], Boolean.parseBoolean(nextRecord[2]), Integer.parseInt(nextRecord[3]), nextRecord[4],
                        nextRecord[5], nextRecord[6], nextRecord[7], Boolean.parseBoolean(nextRecord[8]), nextRecord[9], Boolean.parseBoolean(nextRecord[10]),
                        nextRecord[11], nextRecord[12], nextRecord[13], nextRecord[14], nextRecord[15], nextRecord[16], nextRecord[17],
                        Boolean.parseBoolean(nextRecord[18]), nextRecord[19], Boolean.parseBoolean(nextRecord[20]), nextRecord[21], Integer.parseInt(nextRecord[22]),
                        nextRecord[23], Boolean.parseBoolean(nextRecord[24]));
                towerList.add(tower);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return towerList;
    }
}

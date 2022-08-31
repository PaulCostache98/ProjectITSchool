package ro.itschool.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "id")
public class Tower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tower_id")
    private Long id;
    private String name;
    private String model;
    private boolean hasWarranty;
    private int warrantyLength;
    private String processor;
    private String GPU;
    private String motherboard;
    private String RAM;
    private boolean hasHDD;
    private String HDD;
    private boolean hasSSD;
    private String SSD;
    private String towerCase;
    private String powerSupply;
    private String cooling;
    private String frontPanel;
    private String backPanel;
    private String networking;
    private boolean hasOpticUnit;
    private String opticUnit;
    private boolean hasOperatingSystem;
    private String operatingSystemName;

    private Integer price;

    private String imageSource;

    private boolean hasPaymentPlan;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "towers_carts",
            joinColumns = @JoinColumn(name = "tower_id", referencedColumnName = "tower_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_id", referencedColumnName = "cart_id"))
    @ToString.Exclude
    private Set<Cart> carts;

    public Tower(String imageSource) {
        this.imageSource = imageSource;
    }

    public Tower(Tower tower) {
        this.id = tower.getId();
        this.name = tower.getName();
        this.model = tower.getModel();
        this.hasWarranty = tower.isHasWarranty();
        this.warrantyLength = tower.getWarrantyLength();
        this.processor = tower.getProcessor();
        this.GPU = tower.getGPU();
        this.motherboard = tower.getMotherboard();
        this.RAM = tower.getRAM();
        this.hasHDD = tower.isHasHDD();
        this.HDD = tower.getHDD();
        this.hasSSD = tower.isHasSSD();
        this.SSD = tower.getSSD();
        this.towerCase = tower.getTowerCase();
        this.powerSupply = tower.getPowerSupply();
        this.cooling = tower.getCooling();
        this.frontPanel = tower.getFrontPanel();
        this.backPanel = tower.getBackPanel();
        this.networking = tower.getNetworking();
        this.hasOpticUnit = tower.isHasOpticUnit();
        this.opticUnit = tower.getOpticUnit();
        this.hasOperatingSystem = tower.isHasOperatingSystem();
        this.operatingSystemName = tower.getOperatingSystemName();

        this.price = tower.getPrice();

        this.imageSource = tower.getImageSource();

        this.hasPaymentPlan = tower.isHasPaymentPlan();
        this.carts = tower.getCarts();
    }

    public Tower(String name, String model, boolean hasWarranty, int warrantyLength, String processor, String GPU, String motherboard, String RAM,
                 boolean hasHDD, String HDD, boolean hasSSD, String SSD, String towerCase, String powerSupply, String cooling, String frontPanel,
                 String backPanel, String networking, boolean hasOpticUnit, String opticUnit, boolean hasOperatingSystem, String operatingSystemName,
                 Integer price, String imageSource, boolean hasPaymentPlan) {
        this.name = name;
        this.model = model;
        this.hasWarranty = hasWarranty;
        this.warrantyLength = warrantyLength;
        this.processor = processor;
        this.GPU = GPU;
        this.motherboard = motherboard;
        this.RAM = RAM;
        this.hasHDD = hasHDD;
        this.HDD = HDD;
        this.hasSSD = hasSSD;
        this.SSD = SSD;
        this.towerCase = towerCase;
        this.powerSupply = powerSupply;
        this.cooling = cooling;
        this.frontPanel = frontPanel;
        this.backPanel = backPanel;
        this.networking = networking;
        this.hasOpticUnit = hasOpticUnit;
        this.opticUnit = opticUnit;
        this.hasOperatingSystem =  hasOperatingSystem;
        this.operatingSystemName = operatingSystemName;
        this.price = price;
        this.imageSource = imageSource;
        this.hasPaymentPlan = hasPaymentPlan;

    }

}

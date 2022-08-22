package ro.itschool.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
    private List<String> frontPanel;
    private List<String> backPanel;
    private List<String> networking;
    private boolean hasOpticUnit;
    private String opticUnit;
    private boolean hasOperatingSystem;
    private String operatingSystemName;

    private int price;

    private String imageSource;

    private boolean hasPaymentPlan;

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
    }

}

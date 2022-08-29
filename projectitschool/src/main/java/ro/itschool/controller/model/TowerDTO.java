package ro.itschool.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TowerDTO {

    private String name;
    private String model;
    private int warrantyLength;
    private String processor;
    private String GPU;
    private String motherboard;
    private String RAM;
    private String HDD;
    private String SSD;
    private String towerCase;
    private String powerSupply;
    private String cooling;
    private List<String> frontPanel;
    private List<String> backPanel;
    private List<String> networking;
    private String opticUnit;
    private String operatingSystemName;
    private int price;


}

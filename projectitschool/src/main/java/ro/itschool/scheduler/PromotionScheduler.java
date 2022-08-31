package ro.itschool.scheduler;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import ro.itschool.entity.Tower;
import ro.itschool.service.TowerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
public class PromotionScheduler {

    @Autowired
    TowerService towerService;


    @Scheduled(cron = "0 * * * * *")
    public List<Tower> Promotion() {
        Random random = new Random();
        List<Tower> towerList = towerService.findAll();
        List<Tower> promotions = new ArrayList<>();
        for(int i=0;i<3;i++) {
            promotions.add(towerList.get(random.nextInt(10)));
        }
        return promotions;
    }
}

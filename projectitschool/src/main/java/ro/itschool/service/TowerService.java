package ro.itschool.service;

import org.springframework.stereotype.Service;
import ro.itschool.entity.Tower;

import java.util.List;

@Service
public interface TowerService {

    Tower findByName(String name);

    Tower findById(long id);

    List<Tower> findAll();

    void deleteById(long id);

    Tower saveTower(Tower tower);

    void updateTower(Tower tower);

    List<Tower> searchTower(String keyword);
}

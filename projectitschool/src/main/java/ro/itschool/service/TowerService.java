package ro.itschool.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.itschool.entity.Tower;
import ro.itschool.exception.TowerNotFoundException;

import java.util.List;

@Service
public interface TowerService {

    Tower findByName(String name);

    Tower findById(long id);

    List<Tower> findAll();

    void deleteById(long id) throws TowerNotFoundException;

    Tower saveTower(Tower tower);

    void updateTower(Tower tower);

    List<Tower> searchTower(String keyword);
}

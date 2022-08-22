package ro.itschool.service.impl;

import org.springframework.stereotype.Service;
import ro.itschool.entity.Tower;
import ro.itschool.repository.TowerRepository;
import ro.itschool.service.TowerService;

import java.util.List;

@Service
public class TowerServiceImpl implements TowerService {

    private final TowerRepository towerRepository;


    public TowerServiceImpl(TowerRepository towerRepository) {
        this.towerRepository = towerRepository;
    }


    @Override
    public Tower findByName(String name) {
        return towerRepository.findByName(name);
    }

    @Override
    public Tower findById(long id) {
        return towerRepository.findById(id).get();
    }

    @Override
    public List<Tower> findAll() {
        return towerRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        towerRepository.deleteById(id);
    }

    @Override
    public Tower saveTower(Tower receivedTower) {
        Tower auxTower = new Tower(receivedTower);

        return towerRepository.save(auxTower);
    }

    @Override
    public void updateTower(Tower tower) {
        towerRepository.save(tower);
    }
}

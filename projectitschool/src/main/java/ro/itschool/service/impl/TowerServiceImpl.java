package ro.itschool.service.impl;

import org.springframework.stereotype.Service;
import ro.itschool.entity.Tower;
import ro.itschool.exception.TowerNotFoundException;
import ro.itschool.repository.CartRepository;
import ro.itschool.repository.TowerRepository;
import ro.itschool.service.TowerService;

import java.util.List;

@Service
public class TowerServiceImpl implements TowerService {

    private final TowerRepository towerRepository;

    private final CartRepository cartRepository;




    public TowerServiceImpl(TowerRepository towerRepository, CartRepository cartRepository) {
        this.towerRepository = towerRepository;
        this.cartRepository = cartRepository;
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
    public void deleteById(long id) throws TowerNotFoundException {
        towerRepository.deleteById(id);
    }

    @Override
    public Tower saveTower(Tower receivedTower) {

        return towerRepository.save(receivedTower);
    }

    @Override
    public void updateTower(Tower tower) {
        towerRepository.save(tower);
    }

    @Override
    public List<Tower> searchTower(String keyword) {
        return towerRepository.searchTower(keyword);
    }
}

package ro.itschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.itschool.entity.Tower;

public interface TowerRepository extends JpaRepository<Tower, Long> {

    Tower findByName(String name);

}

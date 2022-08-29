package ro.itschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.itschool.entity.Tower;

import java.util.List;

public interface TowerRepository extends JpaRepository<Tower, Long> {

    Tower findByName(String name);

    Tower findByNameIgnoreCase(String name);

    Tower findByModel(String model);

    @Query(
            value = "SELECT * FROM tower t WHERE t.name LIKE %:keyword% OR t.model LIKE %:keyword% OR t.id LIKE %:keyword% ",
            nativeQuery = true)
    List<Tower> searchTower(@Param("keyword") String keyword);

}

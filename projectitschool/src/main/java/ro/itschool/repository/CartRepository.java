package ro.itschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.itschool.entity.Cart;
import ro.itschool.entity.MyUser;

import java.util.Set;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Set<Cart> findByUser(MyUser user);

    Cart findById(UUID id);

    void deleteById(UUID id);


}

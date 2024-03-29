package ro.itschool.repository;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.itschool.entity.Cart;
import ro.itschool.entity.MyUser;
import ro.itschool.entity.Tower;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Set<Cart> findByUser(MyUser user);

}

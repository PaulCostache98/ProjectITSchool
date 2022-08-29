package ro.itschool.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Cart {

    @Id
    @GeneratedValue
    @Column(name="cart_id")
    private UUID id = UUID.randomUUID();

    private Set<Tower> towers;

    private Double price;

    private boolean isPaid;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private MyUser user;

    public Cart() {
        this.towers = new HashSet<Tower>();
        this.createdAt = LocalDateTime.now();
        this.price = 0D;
        this.isPaid = false;
    }
}

package ro.itschool.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @ManyToMany(mappedBy = "carts")
    @JsonIgnore
    @ToString.Exclude
    private List<Tower> towers;

    private int price;

    private boolean isPaid;

    private boolean isActive;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private MyUser user;

    public Cart() {
        this.towers = new ArrayList<Tower>();
        this.createdAt = LocalDateTime.now();
        this.price = 0;
        this.isPaid = false;
    }

    public void calculatePrice() {
        if (this.price == 0) {
            for (Tower tower : this.towers) {
                this.price += tower.getPrice();
            }
        }
    }
}

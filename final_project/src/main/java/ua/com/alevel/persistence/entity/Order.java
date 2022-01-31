package ua.com.alevel.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.store.Cube;
import ua.com.alevel.persistence.entity.store.Shop;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.listener.ProductVisibleGenerationListener;
import ua.com.alevel.persistence.type.OrderStatus;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne(cascade = {
    })
    private Shop shop;

    @ManyToMany(mappedBy = "orders", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private Set<Cube> cubes;

    @ManyToOne(cascade = {
    })
    private User user;

    public Order() {
        super();
        this.cubes = new HashSet<>();
    }

    @PreRemove
    private void removeOrder() {
        this.getCubes().stream().forEach(cube -> {
            cube.setAmount(cube.getAmount() + 1);
            cube.getOrders().remove(this);
            ProductVisibleGenerationListener.generateCubeVisible(cube);
        });
        this.setCubes(Collections.emptySet());
    }
}

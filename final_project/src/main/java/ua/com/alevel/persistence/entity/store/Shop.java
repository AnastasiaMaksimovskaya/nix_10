package ua.com.alevel.persistence.entity.store;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "shops")
public class Shop extends BaseEntity {

    @AttributeOverride(name = "id", column = @Column(name = "shop_id"))

    private String address;
    private String openTime;
    private String closedTime;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
//            CascadeType.MERGE,
            CascadeType.REMOVE
    })
    @JoinTable(
            name = "shop_product",
            joinColumns = @JoinColumn(name = "shop_id"),
            inverseJoinColumns = @JoinColumn(name = "cube_id"))

    private Set<Cube> cubes;

    public Shop() {
        super();
        this.cubes= new HashSet<>();
    }

    public void addProduct(Cube cube) {
        cubes.add(cube);
        cube.getShops().add(this);
    }

    public void removeProduct(Cube cube) {
        cubes.remove(cube);
        cube.getShops().remove(this);
    }
}

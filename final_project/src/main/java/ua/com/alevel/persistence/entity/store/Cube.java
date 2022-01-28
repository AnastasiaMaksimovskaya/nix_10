package ua.com.alevel.persistence.entity.store;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.persistence.type.CubeCategory;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "cubes")
public class Cube extends BaseEntity {

    @AttributeOverride(name = "id", column = @Column(name = "cube_id"))

    private Integer price;

    @Column(columnDefinition ="TEXT")
    private String description;

    private String image;
    private Integer amount;
    private Boolean visible;

    @Enumerated(EnumType.STRING)
    @Column(name = "category_cubes", nullable = false)
    private CubeCategory cubeCategory;

    @ManyToMany(mappedBy = "cubes", cascade = {
//            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    private Set<Shop> shops;

    @ManyToMany(cascade = {
//            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    @JoinTable(
            name = "cube_order",
            joinColumns = @JoinColumn(name = "cube_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private Set<Order> orders;

    @ManyToOne(cascade = {
//            CascadeType.MERGE
    })
    private Brand brand;

    public Cube() {
        super();
        this.orders = new HashSet<>();
        this.shops = new HashSet<>();
    }

    @PreRemove
    private void removeCube(){
            brand.getCubes().remove(this);
            this.setBrand(null);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cube cube = (Cube) o;
        return price.equals(cube.price) && description.equals(cube.description) && image.equals(cube.image) && amount.equals(cube.amount) && visible.equals(cube.visible) && cubeCategory == cube.cubeCategory && shops.equals(cube.shops) && orders.equals(cube.orders) && brand.equals(cube.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, description, image, amount, visible, cubeCategory, shops, orders, brand);
    }
}

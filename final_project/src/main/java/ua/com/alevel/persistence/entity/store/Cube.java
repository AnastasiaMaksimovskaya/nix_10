package ua.com.alevel.persistence.entity.store;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.type.CubeCategory;

import javax.persistence.*;
import java.util.HashSet;
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
            CascadeType.MERGE,
            CascadeType.REMOVE
    })
    private Set<Shop> shops;

    @ManyToOne(cascade = {
            CascadeType.ALL
//            CascadeType.MERGE,
//            CascadeType.DETACH
    })

    private Brand brand;

    public Cube() {
        super();
        this.shops = new HashSet<>();
    }
}

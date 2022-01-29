package ua.com.alevel.persistence.entity.store;

import com.neovisionaries.i18n.CountryCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@ToString
@Entity
@Table(name = "brands")
public class Brand extends BaseEntity {

    private String name;

    @AttributeOverride(name = "id", column = @Column(name = "brand_id"))

    @OneToMany(
            mappedBy = "brand",
            cascade = {
//            CascadeType.REMOVE,
//            CascadeType.PERSIST
    })
    private Set<Cube> cubes;

    @Enumerated(EnumType.STRING)
    private CountryCode country;

    public Brand(){
        super();
        this.cubes = new HashSet<>();
    }


    public void addCube(Cube cube) {
        cubes.add(cube);
        cube.setBrand(this);
    }
}

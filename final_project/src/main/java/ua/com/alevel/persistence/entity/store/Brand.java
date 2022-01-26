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

    @OneToMany(cascade = {
           CascadeType.ALL
    })
    private Set<Cube> cubes;

    @Enumerated(EnumType.STRING)
    private CountryCode country;

    public Brand(){
        super();
        this.cubes = new HashSet<>();
    }
    public void removeCube(Cube cube) {
        cubes.remove(cube);
    }

    public void addCube(Cube cube) {
        cubes.add(cube);
        cube.setBrand(this);
    }
}

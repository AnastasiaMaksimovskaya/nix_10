package ua.com.alevel.persistence.entity.store;

import com.neovisionaries.i18n.CountryCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@ToString
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "brands")
public class Brand extends BaseEntity {

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Cube> cubes;

    @Enumerated(EnumType.STRING)
    private CountryCode country;

    public Brand(){
        super();
    }
}

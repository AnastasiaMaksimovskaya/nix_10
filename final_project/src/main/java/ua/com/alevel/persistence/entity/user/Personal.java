package ua.com.alevel.persistence.entity.user;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.listener.FullNameGenerationListener;
import ua.com.alevel.persistence.type.RoleType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@DiscriminatorValue("PERSONAL")
@EntityListeners({
        FullNameGenerationListener.class,
//        AgeByBirthDayGenerationListener.class
})
public class Personal extends User {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birth_day")
    private Date birthDay;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Transient
    private String fullName;

    @Transient
    private Integer age;

    public Personal() {
        super();
        setRoleType(RoleType.ROLE_PERSONAL);
    }
}

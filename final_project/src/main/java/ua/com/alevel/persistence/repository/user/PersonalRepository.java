package ua.com.alevel.persistence.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.entity.user.Personal;

@Repository
public interface PersonalRepository extends UserRepository<Personal> {
    Personal findPersonalByEmail(String email);
}

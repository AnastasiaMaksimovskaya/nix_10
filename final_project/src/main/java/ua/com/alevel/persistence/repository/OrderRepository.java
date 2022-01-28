package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.persistence.entity.user.User;

@Repository
public interface OrderRepository extends BaseRepository<Order> {
}

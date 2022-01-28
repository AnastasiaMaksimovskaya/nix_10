package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.persistence.entity.user.User;

public interface OrderService extends BaseService<Order>{
    User findUserByEmail(String email);
}

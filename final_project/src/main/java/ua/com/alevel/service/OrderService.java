package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Order;
import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.entity.user.User;

public interface OrderService extends BaseService<Order>{
    Personal findUserByEmail(String email);
    Admin findAdminByEmail(String email);
}

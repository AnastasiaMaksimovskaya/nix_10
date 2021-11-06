package ua.com.alevel.dao;

import ua.com.alevel.entity.Product;

public interface ProductDao extends BaseDao<Product> {
    String findShop(String id);
}
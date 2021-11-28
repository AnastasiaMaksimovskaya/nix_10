package ua.com.alevel.db;

import ua.com.alevel.entity.Product;

public interface ProductDB extends BaseDB<Product> {
    String findShop(String id);
}
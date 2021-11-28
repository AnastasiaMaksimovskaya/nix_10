package ua.com.alevel.service;

import ua.com.alevel.entity.Product;

public interface ProductService extends BaseService<Product> {
    String findShop(String id);
}
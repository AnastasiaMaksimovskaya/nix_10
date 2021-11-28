package ua.com.alevel.dao;

import ua.com.alevel.entity.Product;
import ua.com.alevel.entity.Shop;

import java.util.List;

public interface ShopDao extends BaseDao<Shop> {
    boolean existByAddress(String address);
    List<Product> findAllProducts(Shop shop);
}
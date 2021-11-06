package ua.com.alevel.dao;

import ua.com.alevel.entity.Product;
import ua.com.alevel.entity.Shop;

public interface ShopDao extends BaseDao<Shop> {
    boolean existByAddress(String address);
    Product[] findAllProducts(Shop shop);
}
package ua.com.alevel.db;

import ua.com.alevel.entity.Product;
import ua.com.alevel.entity.Shop;

import java.util.List;

public interface ShopDB extends BaseDB<Shop> {
    boolean existByAddress(String address);
    List<Product> findAllProducts(Shop shop);
}
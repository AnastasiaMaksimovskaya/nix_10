package ua.com.alevel.db;

import ua.com.alevel.entity.Product;
import ua.com.alevel.entity.Shop;

public interface ShopDB extends BaseDB<Shop> {
    boolean existByAddress(String address);
    Product[] findAllProducts(Shop shop);
}
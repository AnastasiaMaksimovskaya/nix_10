package ua.com.alevel.service;

import ua.com.alevel.entity.Product;
import ua.com.alevel.entity.Shop;

public interface ShopService extends  BaseService<Shop> {
    boolean existByAddress(String address);
    Product[] findAllProducts(Shop shop);
}
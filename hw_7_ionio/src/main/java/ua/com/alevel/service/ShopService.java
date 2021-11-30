package ua.com.alevel.service;

import ua.com.alevel.entity.Product;
import ua.com.alevel.entity.Shop;

import java.util.List;

public interface ShopService extends BaseService<Shop> {
    boolean existByAddress(String address);

    List<Product> findAllProducts(String shopId);
}
package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Shop;

import java.util.Map;

public interface ShopDao extends BaseDao<Shop> {
    Map<Long, String> findAllProductsByShopId(Long productId);
}

package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Shop;

import java.util.Map;

public interface ShopService extends BaseService<Shop> {
    Map<Long, String> findAllProductsByShopId(Long productId);
}

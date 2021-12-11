package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Product;

import java.util.Map;

public interface ProductDao extends BaseDao<Product> {
    Map<Long, String> findAllByShopId(Long shopId);
}

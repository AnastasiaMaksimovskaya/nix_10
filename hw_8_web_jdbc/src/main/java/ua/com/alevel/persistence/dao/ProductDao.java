package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductDao extends BaseDao<Product> {
    Map<Long, String> findAllByShopId(Long shopId);
    void createRelationship(Product p, List<Integer> shopsId);
}

package ua.com.alevel.service.store;

import ua.com.alevel.persistence.entity.store.Shop;
import ua.com.alevel.service.BaseService;

import java.util.List;
import java.util.Map;

public interface ShopService extends BaseService<Shop> {
    Map<Long, String> findAllProductsByShopId(Long shopId);
    List<Shop> findAll();
}

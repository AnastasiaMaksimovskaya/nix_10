package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.Product;
import ua.com.alevel.persistence.repository.ShopRepository;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Shop;
import ua.com.alevel.service.ShopService;
import ua.com.alevel.util.WebResponseUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class ShopServiceImpl implements ShopService {

    private final CrudRepositoryHelper<Shop, ShopRepository> crudRepositoryHelper;
    private final ShopRepository shopRepository;

    public ShopServiceImpl(CrudRepositoryHelper<Shop, ShopRepository> crudRepositoryHelper, ShopRepository shopRepository) {
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.shopRepository = shopRepository;
    }

    @Override
    public void create(Shop entity) {
        crudRepositoryHelper.create(shopRepository,entity);
    }

    @Override
    public void update(Shop entity) {
        crudRepositoryHelper.update(shopRepository,entity);

    }

    @Override
    public void delete(Long id) {
        crudRepositoryHelper.delete(shopRepository,id);
    }

    @Override
    public Shop findById(Long id) {
        return crudRepositoryHelper.findById(shopRepository,id).get();
    }

    @Override
    public DataTableResponse<Shop> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(shopRepository,request);
    }

    @Override
    public Map<Long, String> findAllProductsByShopId(Long shopId) {
        Map<Long, String> map = new HashMap<>();
        Set<Product> products = findById(shopId).getProducts();
        for (Product product : products) {
            map.put(product.getId(), product.getName());
        }
        return map;
    }
}

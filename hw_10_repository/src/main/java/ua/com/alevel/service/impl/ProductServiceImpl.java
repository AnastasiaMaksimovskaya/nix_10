package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.Shop;
import ua.com.alevel.persistence.repository.ProductRepository;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Product;
import ua.com.alevel.service.ProductService;
import ua.com.alevel.util.WebResponseUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private final CrudRepositoryHelper<Product,ProductRepository> crudRepositoryHelper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(CrudRepositoryHelper<Product, ProductRepository> crudRepositoryHelper, ProductRepository productRepository) {
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.productRepository = productRepository;
    }

    @Override
    public void create(Product entity) {
        crudRepositoryHelper.create(productRepository,entity);
    }

    @Override
    public void update(Product entity) {
        crudRepositoryHelper.update(productRepository,entity);
    }

    @Override
    public void delete(Long id) {
        crudRepositoryHelper.delete(productRepository,id);
    }

    @Override
    public Product findById(Long id) {
        return crudRepositoryHelper.findById(productRepository,id).get();
    }

    @Override
    public DataTableResponse<Product> findAll(DataTableRequest request) {
            return crudRepositoryHelper.findAll(productRepository, request);
    }

    @Override
    public Map<Long, String> findAllShopsByProductId(Long productId) {
        Map<Long, String> map = new HashMap<>();
        Set<Shop> shops = findById(productId).getShops();
        for (Shop shop : shops) {
            map.put(shop.getId(), shop.getName());
        }
        return map;
    }

}

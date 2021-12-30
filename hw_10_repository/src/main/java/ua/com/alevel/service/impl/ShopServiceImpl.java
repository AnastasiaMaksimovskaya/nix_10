package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.Product;
import ua.com.alevel.persistence.repository.ProductRepository;
import ua.com.alevel.persistence.repository.ShopRepository;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Shop;
import ua.com.alevel.service.ProductService;
import ua.com.alevel.service.ShopService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {

    private final CrudRepositoryHelper<Shop, ShopRepository> crudRepositoryHelper;
    private final ShopRepository shopRepository;
    private final ProductService productService;

    public ShopServiceImpl(CrudRepositoryHelper<Shop, ShopRepository> crudRepositoryHelper, ShopRepository shopRepository, ProductRepository productRepository, ProductService productService) {
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.shopRepository = shopRepository;
        this.productService = productService;
    }

    @Override
    public void create(Shop entity) {
        crudRepositoryHelper.create(shopRepository, entity);
    }

    @Override
    public void update(Shop entity) {
        crudRepositoryHelper.update(shopRepository, entity);
    }

    @Override
    public void delete(Long id) {
        Shop shop = crudRepositoryHelper.findById(shopRepository, id).get();
        List<Product> one = shop.getProducts().stream().filter(product -> productService.findAllShopsByProductId(product.getId()).size() == 1).collect(Collectors.toList());
        shop.getProducts().retainAll(one);
        crudRepositoryHelper.update(shopRepository, shop);
        crudRepositoryHelper.delete(shopRepository, id);
    }

    @Override
    public Shop findById(Long id) {
        return crudRepositoryHelper.findById(shopRepository, id).get();
    }

    @Override
    public DataTableResponse<Shop> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(shopRepository, request);
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

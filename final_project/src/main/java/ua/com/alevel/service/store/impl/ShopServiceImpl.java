package ua.com.alevel.service.store.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.store.Cube;
import ua.com.alevel.persistence.entity.store.Shop;
import ua.com.alevel.persistence.repository.store.ShopRepository;
import ua.com.alevel.service.store.CubeService;
import ua.com.alevel.service.store.ShopService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {

    private final CrudRepositoryHelper<Shop, ShopRepository> crudRepositoryHelper;
    private final ShopRepository shopRepository;
    private final CubeService cubeService;

    public ShopServiceImpl(CrudRepositoryHelper<Shop, ShopRepository> crudRepositoryHelper, ShopRepository shopRepository, CubeService cubeService) {
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.shopRepository = shopRepository;
        this.cubeService = cubeService;
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
        List<Cube> one = shop.getCubes().stream().filter(product -> cubeService.findAllShopsByProductId(product.getId()).size() == 1).collect(Collectors.toList());
        shop.getCubes().retainAll(one);
        crudRepositoryHelper.update(shopRepository, shop);
        crudRepositoryHelper.delete(shopRepository, id);
    }

    @Override
    public Optional<Shop> findById(Long id) {
        return crudRepositoryHelper.findById(shopRepository, id);
    }

    @Override
    public DataTableResponse<Shop> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(shopRepository, request);
    }

    @Override
    public Map<Long, String> findAllProductsByShopId(Long shopId) {
        Map<Long, String> map = new HashMap<>();
        Set<Cube> cubes = findById(shopId).get().getCubes();
        for (Cube cube : cubes) {
            map.put(cube.getId(), cube.getName());
        }
        return map;
    }

    @Override
    public List<Shop> findAll() {
        return crudRepositoryHelper.findAll(shopRepository);
    }

}

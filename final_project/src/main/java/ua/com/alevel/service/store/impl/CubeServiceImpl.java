package ua.com.alevel.service.store.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.store.Cube;
import ua.com.alevel.persistence.entity.store.Shop;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.store.CubeRepository;
import ua.com.alevel.persistence.repository.store.ShopRepository;
import ua.com.alevel.service.store.CubeService;

import java.util.*;

@Service
public class CubeServiceImpl implements CubeService {

    private final CrudRepositoryHelper<Cube, CubeRepository> crudRepositoryHelper;
    private final CubeRepository cubeRepository;

    public CubeServiceImpl(CrudRepositoryHelper<Cube, CubeRepository> crudRepositoryHelper, CubeRepository cubeRepository) {
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.cubeRepository = cubeRepository;
    }

    @Override
    public void create(Cube entity) {
        crudRepositoryHelper.create(cubeRepository, entity);
    }

    @Override
    public void update(Cube entity) {
        crudRepositoryHelper.update(cubeRepository, entity);
    }

    @Override
    public void delete(Long id) {
        cubeRepository.detachCubeFromShop(id);
        crudRepositoryHelper.delete(cubeRepository, id);
    }

    @Override
    public Optional<Cube> findById(Long id) {
        return crudRepositoryHelper.findById(cubeRepository, id);
    }

    @Override
    public DataTableResponse<Cube> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(cubeRepository, request);
    }

    @Override
    public Map<Long, String> findAllShopsByProductId(Long productId) {
        Map<Long, String> map = new HashMap<>();
        Set<Shop> shops = findById(productId).get().getShops();
        for (Shop shop : shops) {
            map.put(shop.getId(), shop.getName());
        }
        return map;
    }
}

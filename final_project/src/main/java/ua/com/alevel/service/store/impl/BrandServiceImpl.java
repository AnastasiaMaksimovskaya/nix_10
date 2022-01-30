package ua.com.alevel.service.store.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.store.Brand;
import ua.com.alevel.persistence.entity.store.Cube;
import ua.com.alevel.persistence.repository.store.BrandRepository;
import ua.com.alevel.persistence.repository.store.CubeRepository;
import ua.com.alevel.service.store.BrandService;
import ua.com.alevel.service.store.CubeService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BrandServiceImpl implements BrandService {
    private final CrudRepositoryHelper<Brand, BrandRepository> crudRepositoryHelper;

    private final BrandRepository brandRepository;
    private final CubeService cubeService;

    public BrandServiceImpl(CrudRepositoryHelper<Brand, BrandRepository> crudRepositoryHelper, BrandRepository brandRepository, CubeService cubeService) {
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.brandRepository = brandRepository;
        this.cubeService = cubeService;
    }

    @Override
    public void create(Brand entity) {
        crudRepositoryHelper.create(brandRepository, entity);
    }

    @Override
    public void update(Brand entity) {
        crudRepositoryHelper.update(brandRepository, entity);
    }

    @Override
    public void delete(Long id) {
        Set<Cube> cubeSet = findById(id).get().getCubes();
        cubeSet.stream().forEach(cube -> cubeService.delete(cube.getId()));
        crudRepositoryHelper.delete(brandRepository,id);
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return crudRepositoryHelper.findById(brandRepository, id);
    }

    @Override
    public DataTableResponse<Brand> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(brandRepository, request);
    }

    @Override
    public List<Brand> findAll() {
        return crudRepositoryHelper.findAll(brandRepository);
    }
}

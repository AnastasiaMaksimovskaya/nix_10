package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.store.Brand;
import ua.com.alevel.persistence.entity.store.Cube;
import ua.com.alevel.persistence.repository.store.BrandRepository;
import ua.com.alevel.persistence.repository.store.CubeRepository;
import ua.com.alevel.service.PLPService;
import ua.com.alevel.util.WebRequestUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PLPServiceImpl implements PLPService {

    private final CubeRepository cubeRepository;
    private final BrandRepository brandRepository;
    private final CrudRepositoryHelper<Brand, BrandRepository> crudRepositoryHelper;

    public PLPServiceImpl(CubeRepository cubeRepository, BrandRepository brandRepository, CrudRepositoryHelper<Brand, BrandRepository> crudRepositoryHelper) {
        this.cubeRepository = cubeRepository;
        this.brandRepository = brandRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }


    @Override
    public List<Cube> search(Map<String, Object> queryMap) {
        if (queryMap.get(WebRequestUtil.BRAND_PARAM) != null) {
            Long brandId = Long.parseLong(String.valueOf(queryMap.get(WebRequestUtil.BRAND_PARAM)));
            Optional<Brand> brand = crudRepositoryHelper.findById(brandRepository, brandId);
            return cubeRepository.findByBrand(brand.get());
        }
        if (queryMap.get(WebRequestUtil.SEARCH_CUBE_PARAM) != null) {
            String searchCube = (String) queryMap.get(WebRequestUtil.SEARCH_CUBE_PARAM);
            return cubeRepository.findByNameContaining(searchCube);
        }
        return cubeRepository.findAll();
    }
}

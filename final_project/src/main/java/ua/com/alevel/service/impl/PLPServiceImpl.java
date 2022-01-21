package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
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
    private final CrudRepositoryHelper<Cube, CubeRepository> cubeCrudRepositoryHelper;

    public PLPServiceImpl(CubeRepository cubeRepository, BrandRepository brandRepository, CrudRepositoryHelper<Brand, BrandRepository> crudRepositoryHelper, CrudRepositoryHelper<Cube, CubeRepository> cubeCrudRepositoryHelper) {
        this.cubeRepository = cubeRepository;
        this.brandRepository = brandRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.cubeCrudRepositoryHelper = cubeCrudRepositoryHelper;
    }


    @Override
    public DataTableResponse<Cube> search(Map<String, Object> queryMap, DataTableRequest dataTableRequest) {
        String sortBy = dataTableRequest.getSort();
        String orderBy = dataTableRequest.getOrder();
        if (queryMap.get(WebRequestUtil.BRAND_PARAM) != null) {
            Long brandId = Long.parseLong(String.valueOf(queryMap.get(WebRequestUtil.BRAND_PARAM)));
            Optional<Brand> brand = crudRepositoryHelper.findById(brandRepository, brandId);
            List<Cube> cubes = cubeRepository.findByBrand(brand.get());
            return initDataTableResponseCube(cubes,dataTableRequest);
        }
        if (queryMap.get(WebRequestUtil.SEARCH_CUBE_PARAM) != null) {
            String searchCube = (String) queryMap.get(WebRequestUtil.SEARCH_CUBE_PARAM);
            List<Cube> cubes = cubeRepository.findByNameContaining(searchCube);
            return initDataTableResponseCube(cubes,dataTableRequest);
        }
        return cubeCrudRepositoryHelper.findAll(cubeRepository,dataTableRequest);
    }
    private DataTableResponse<Cube> initDataTableResponseCube(List<Cube> cubes,DataTableRequest dataTableRequest){
        String sortBy = dataTableRequest.getSort();
        String orderBy = dataTableRequest.getOrder();
        DataTableResponse<Cube> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItemsSize(cubes.size());
        dataTableResponse.setItems(cubes);
        dataTableResponse.setOrder(orderBy);
        dataTableResponse.setSort(sortBy);
        dataTableResponse.setCurrentPage(dataTableRequest.getPage());
        dataTableResponse.setPageSize(dataTableRequest.getSize());
        return dataTableResponse;
    }
}

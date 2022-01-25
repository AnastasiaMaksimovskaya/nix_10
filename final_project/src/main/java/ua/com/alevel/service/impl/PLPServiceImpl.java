package ua.com.alevel.service.impl;

import org.springframework.data.domain.*;
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
        int size = dataTableRequest.getSize();
        int page = dataTableRequest.getPage() - 1;
        String sortBy = dataTableRequest.getSort();
        String orderBy = dataTableRequest.getOrder();
        Sort sort = orderBy.equals("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        if (queryMap.get(WebRequestUtil.BRAND_PARAM) != null) {
            List<String> brandNames = (List<String>) (queryMap.get(WebRequestUtil.BRAND_PARAM));
            Page<Cube> cubes = cubeRepository.findByBrandNameIn(brandNames, pageRequest);
            return initDataTableResponseCube(cubes, dataTableRequest, sortBy, orderBy);
        }
        if (queryMap.get(WebRequestUtil.SEARCH_CUBE_PARAM) != null) {
            String searchCube = (String) queryMap.get(WebRequestUtil.SEARCH_CUBE_PARAM);
            Page<Cube> cubes = cubeRepository.findByNameContaining(searchCube, pageRequest);
            return initDataTableResponseCube(cubes, dataTableRequest, sortBy, orderBy);
        }
        return cubeCrudRepositoryHelper.findAll(cubeRepository, dataTableRequest);
    }

    private DataTableResponse<Cube> initDataTableResponseCube(Page<Cube> cubes, DataTableRequest dataTableRequest, String sortBy, String orderBy) {

        DataTableResponse<Cube> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setTotalPageSize(cubes.getTotalPages());
        dataTableResponse.setItemsSize(cubes.getTotalElements());
        dataTableResponse.setItems(cubes.getContent());
        dataTableResponse.setOrder(orderBy);
        dataTableResponse.setSort(sortBy);
        dataTableResponse.setCurrentPage(dataTableRequest.getPage());
        dataTableResponse.setPageSize(dataTableRequest.getSize());
        return dataTableResponse;
    }
}

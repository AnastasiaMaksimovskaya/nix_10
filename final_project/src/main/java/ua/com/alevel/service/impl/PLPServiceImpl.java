package ua.com.alevel.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        if (queryMap.get(WebRequestUtil.BRAND_PARAM) != null) {
            List<String> brandNames =(List<String>)(queryMap.get(WebRequestUtil.BRAND_PARAM));
            List<Cube> cubes = cubeRepository.findByBrandNames(brandNames);
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
        int size = dataTableRequest.getSize();
        int page = dataTableRequest.getPage() - 1;
        String sortBy = dataTableRequest.getSort();
        String orderBy = dataTableRequest.getOrder();
        Sort sort = orderBy.equals("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<Cube> pageCube = new PageImpl<>(cubes, pageRequest, cubes.size());
        DataTableResponse<Cube> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setTotalPageSize(pageCube.getTotalPages());
        dataTableResponse.setItemsSize(pageCube.getTotalElements());
        dataTableResponse.setItems(pageCube.getContent());
        dataTableResponse.setOrder(orderBy);
        dataTableResponse.setSort(sortBy);
        dataTableResponse.setCurrentPage(dataTableRequest.getPage());
        dataTableResponse.setPageSize(dataTableRequest.getSize());
        return dataTableResponse;
    }
}

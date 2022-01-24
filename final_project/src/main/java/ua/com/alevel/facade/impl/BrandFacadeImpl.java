package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.BrandFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.store.Brand;
import ua.com.alevel.persistence.entity.store.Cube;
import ua.com.alevel.service.store.BrandService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.BrandRequestDto;
import ua.com.alevel.view.dto.response.BrandResponseDto;
import ua.com.alevel.view.dto.response.CubeResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandFacadeImpl implements BrandFacade {

    private final BrandService brandService;

    public BrandFacadeImpl(BrandService brandService) {
        this.brandService = brandService;
    }

    @Override
    public List<Brand> findAll() {
        return brandService.findAll();
    }

    @Override
    public void create(BrandRequestDto brandRequestDto) {
        Brand brand = new Brand();
        brand.setCreated(new Date(System.currentTimeMillis()));
        brand.setUpdated(new Date(System.currentTimeMillis()));
        brand.setName(brandRequestDto.getName());
        brand.setCountry(brandRequestDto.getCountry());
        brandService.create(brand);
    }

    @Override
    public void update(BrandRequestDto brandRequestDto, Long id) {
        Brand brand = brandService.findById(id).get();
        String name = brandRequestDto.getName();
        brand.setUpdated(new Date(System.currentTimeMillis()));
        if (!name.isBlank()){
            brand.setName(name);
        }
        brand.setCountry(brandRequestDto.getCountry());
        brandService.update(brand);
    }

    @Override
    public void delete(Long id) {
        brandService.delete(id);
    }

    @Override
    public BrandResponseDto findById(Long id) {
        return new BrandResponseDto(brandService.findById(id).get());
    }

    @Override
    public PageData<BrandResponseDto> findAll(WebRequest request) {

        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Brand> tableResponse = brandService.findAll(dataTableRequest);

        List<BrandResponseDto> brands = tableResponse.getItems().stream().
                map(BrandResponseDto::new).
                collect(Collectors.toList());

        PageData<BrandResponseDto> pageData = (PageData<BrandResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(brands);
        return pageData;    }
}

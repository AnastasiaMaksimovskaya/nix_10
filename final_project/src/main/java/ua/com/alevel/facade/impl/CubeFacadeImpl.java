package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.CubeFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.store.Cube;
import ua.com.alevel.persistence.entity.store.Shop;
import ua.com.alevel.service.store.CubeService;
import ua.com.alevel.service.store.ShopService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.CubeRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.CubeResponseDto;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CubeFacadeImpl implements CubeFacade {

    private final CubeService cubeService;
    private final ShopService shopService;

    public CubeFacadeImpl(CubeService cubeService, ShopService shopService) {
        this.cubeService = cubeService;
        this.shopService = shopService;
    }

    @Override
    public void create(CubeRequestDto cubeRequestDto) {
        Cube cube = new Cube();
        cube.setName(cubeRequestDto.getProductName());
        cube.setPrice(cubeRequestDto.getPrice());
        try {
            Set<Long> shopsId = cubeRequestDto.getShopsId();
            cubeService.create(cube);
            for (Long id : shopsId) {
                Shop shop = shopService.findById(id).get();
                (shop).addProduct(cube);
                shopService.update(shop);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(CubeRequestDto cubeRequestDto, Long id) {
        Cube cube = cubeService.findById(id).get();
        cube.setPrice(cubeRequestDto.getPrice());
        cubeService.update(cube);
    }

    @Override
    public void delete(Long id) {
        cubeService.delete(id);
    }

    @Override
    public CubeResponseDto findById(Long id) {
        return new CubeResponseDto(cubeService.findById(id).get());
    }

    @Override
    public PageData<CubeResponseDto> findAll(WebRequest request) {

        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Cube> tableResponse = cubeService.findAll(dataTableRequest);

        List<CubeResponseDto> products = tableResponse.getItems().stream().
                map(CubeResponseDto::new).
                collect(Collectors.toList());

        PageData<CubeResponseDto> pageData = (PageData<CubeResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(products);
        return pageData;
    }

    @Override
    public Map<Long, String> findAllShopsByProductId(Long shopId) {
        return cubeService.findAllShopsByProductId(shopId);
    }
}

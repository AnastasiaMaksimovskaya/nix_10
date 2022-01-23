package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.CubeFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.store.Cube;
import ua.com.alevel.persistence.entity.store.Shop;
import ua.com.alevel.persistence.listener.ProductVisibleGenerationListener;
import ua.com.alevel.service.store.BrandService;
import ua.com.alevel.service.store.CubeService;
import ua.com.alevel.service.store.ShopService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.CubeRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.CubeResponseDto;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CubeFacadeImpl implements CubeFacade {

    private final CubeService cubeService;
    private final ShopService shopService;
    private final BrandService brandService;

    public CubeFacadeImpl(CubeService cubeService, ShopService shopService, BrandService brandService) {
        this.cubeService = cubeService;
        this.shopService = shopService;
        this.brandService = brandService;
    }

    @Override
    public void create(CubeRequestDto cubeRequestDto) {
        Cube cube = new Cube();
        cube.setBrand(brandService.findById(cubeRequestDto.getBrandId()).get());
        cube.setName(cubeRequestDto.getProductName());
        cube.setPrice(cubeRequestDto.getPrice());
        cube.setDescription(cubeRequestDto.getDescription());
        cube.setCubeCategory(cubeRequestDto.getCategory());
        cube.setCreated(new Date(System.currentTimeMillis()));
        cube.setUpdated(new Date(System.currentTimeMillis()));
        cube.setImage(cubeRequestDto.getImage());
        cube.setAmount(cubeRequestDto.getAmount());
        ProductVisibleGenerationListener.generateCubeVisible(cube);
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
        Integer price =cubeRequestDto.getPrice();
        String description = cubeRequestDto.getDescription();
        String name = cubeRequestDto.getProductName();
        String image = cubeRequestDto.getImage();
        cube.setUpdated(new Date(System.currentTimeMillis()));
        Integer amount = cubeRequestDto.getAmount();
        if (price!=null){
            cube.setPrice(price);
        }
        if (!description.isBlank()){
            cube.setDescription(description);
        }
        if (!name.isBlank()){
            cube.setName(name);
        }
        if (!image.isBlank()){
            cube.setImage(image);
        }
        if(amount!=null){
            cube.setAmount(amount);
        }
        ProductVisibleGenerationListener.generateCubeVisible(cube);
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

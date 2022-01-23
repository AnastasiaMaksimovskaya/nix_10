package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.ShopFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.store.Shop;
import ua.com.alevel.service.store.ShopService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.ShopRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.ShopResponseDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ShopFacadeImpl implements ShopFacade {

    private final ShopService shopService;

    public ShopFacadeImpl(ShopService shopService) {
        this.shopService = shopService;
    }

    @Override
    public void create(ShopRequestDto shopRequestDto) {
        Shop shop = new Shop();
        shop.setAddress(shopRequestDto.getAddress());
        shop.setName(shopRequestDto.getName());
        shopService.create(shop);
    }

    @Override
    public void update(ShopRequestDto shopRequestDto, Long id) {
        Shop shop = shopService.findById(id).get();
        shop.setName(shopRequestDto.getName());
        shopService.update(shop);
    }

    @Override
    public void delete(Long id) {
        shopService.delete(id);
    }

    @Override
    public ShopResponseDto findById(Long id) {
        return new ShopResponseDto(shopService.findById(id).get());
    }

    @Override
    public PageData<ShopResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Shop> tableResponse = shopService.findAll(dataTableRequest);

        List<ShopResponseDto> products = tableResponse.getItems().stream().
                map(ShopResponseDto::new).
                collect(Collectors.toList());

        PageData<ShopResponseDto> pageData = (PageData<ShopResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(products);

        return pageData;
    }

    @Override
    public Map<Long, String> findAllProductsByShopId(Long productId) {
        return shopService.findAllProductsByShopId(productId);
    }

    @Override
    public List<Shop> findAll() {
        return shopService.findAll();
    }
}

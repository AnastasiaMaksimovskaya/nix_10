package ua.com.alevel.facade;

import ua.com.alevel.persistence.entity.store.Brand;
import ua.com.alevel.persistence.entity.store.Shop;
import ua.com.alevel.view.dto.request.ShopRequestDto;
import ua.com.alevel.view.dto.response.ShopResponseDto;

import java.util.List;
import java.util.Map;

public interface ShopFacade extends BaseFacade<ShopRequestDto, ShopResponseDto> {
    Map<Long, String> findAllProductsByShopId(Long productId);
    List<Shop> findAll();
}

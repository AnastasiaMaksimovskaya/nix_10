package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.ShopRequestDto;
import ua.com.alevel.view.dto.response.ShopResponseDto;

import java.util.Map;

public interface ShopFacade extends BaseFacade<ShopRequestDto, ShopResponseDto> {
    Map<Long, String> findAllByProductId(Long productId);

}

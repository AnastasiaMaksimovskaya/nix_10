package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.CubeRequestDto;
import ua.com.alevel.view.dto.response.CubeResponseDto;

import java.util.Map;

public interface CubeFacade extends BaseFacade<CubeRequestDto, CubeResponseDto> {
    Map<Long, String> findAllShopsByProductId(Long shopId);
}

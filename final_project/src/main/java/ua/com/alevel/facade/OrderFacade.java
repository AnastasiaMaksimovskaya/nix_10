package ua.com.alevel.facade;

import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.view.dto.request.OrderRequestDto;
import ua.com.alevel.view.dto.response.OrderResponseDto;

public interface OrderFacade extends BaseFacade<OrderRequestDto, OrderResponseDto> {
    Personal findUserByEmail(String email);
}

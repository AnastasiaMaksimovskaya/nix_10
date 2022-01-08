package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.UserRequestDto;
import ua.com.alevel.view.dto.response.UserResponseDto;

import java.util.Map;

public interface UserFacade extends BaseFacade<UserRequestDto, UserResponseDto> {
    Map<Long, String> findAllAccountsByUserId(Long userId);
}

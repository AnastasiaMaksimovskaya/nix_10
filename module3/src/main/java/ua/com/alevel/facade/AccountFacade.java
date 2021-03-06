package ua.com.alevel.facade;

import ua.com.alevel.entity.User;
import ua.com.alevel.view.dto.request.AccountRequestDto;
import ua.com.alevel.view.dto.response.AccountResponseDto;

public interface AccountFacade extends  BaseFacade<AccountRequestDto, AccountResponseDto> {
    User findUserByAccountId(Long accountId);
}

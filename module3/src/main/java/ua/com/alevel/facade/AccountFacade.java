package ua.com.alevel.facade;

import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.User;
import ua.com.alevel.view.dto.request.AccountRequestDto;
import ua.com.alevel.view.dto.response.AccountResponseDto;

import java.sql.SQLException;

public interface AccountFacade extends  BaseFacade<AccountRequestDto, AccountResponseDto> {
    User findUserByAccountId(Long accountId);
}

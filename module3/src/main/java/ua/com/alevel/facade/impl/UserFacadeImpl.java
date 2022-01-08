package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.User;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.service.AccountService;
import ua.com.alevel.service.UserService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.UserRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.UserResponseDto;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserFacadeImpl implements UserFacade {

    private final AccountService accountService;
    private final UserService userService;

    public UserFacadeImpl(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @Override
    public void create(UserRequestDto userRequestDto) {
        User user = new User();
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        userService.create(user);
    }

    @Override
    public void update(UserRequestDto userRequestDto, Long id) {
        User user = userService.findById(id);
        user.setEmail(userRequestDto.getEmail());
        userService.update(user);
    }

    @Override
    public void delete(Long id) {
        userService.delete(id);
    }

    @Override
    public UserResponseDto findById(Long id) {
        return new UserResponseDto(userService.findById(id));
    }

    @Override
    public PageData<UserResponseDto> findAll(WebRequest request) {

        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<User> tableResponse = userService.findAll(dataTableRequest);

        List<UserResponseDto> users = tableResponse.getItems().stream().
                map(UserResponseDto::new).
                collect(Collectors.toList());
        PageData<UserResponseDto> pageData = (PageData<UserResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(users);
        return pageData;
    }

    @Override
    public Map<Long, String> findAllAccountsByUserId(Long userId) {
        return userService.findAllAccountsByUserId(userId);
    }
}

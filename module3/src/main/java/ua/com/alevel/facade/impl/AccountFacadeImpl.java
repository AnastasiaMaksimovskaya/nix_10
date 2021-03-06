package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.User;
import ua.com.alevel.facade.AccountFacade;
import ua.com.alevel.service.AccountService;
import ua.com.alevel.service.UserService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.AccountRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.AccountResponseDto;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountFacadeImpl implements AccountFacade {

    private final AccountService accountService;
    private final UserService userService;

    public AccountFacadeImpl(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @Override
    public void create(AccountRequestDto accountRequestDto) {
        Account account = new Account();
        account.setBalance(10000L);
        account.setUser(userService.findById(accountRequestDto.getUserId()));
        account.setName(accountRequestDto.getName());
        account.setCreated(new Timestamp(System.currentTimeMillis()));
        accountService.create(account);
    }

    @Override
    public void update(AccountRequestDto accountRequestDto, Long id) {
        Account account = accountService.findById(id);
        account.setName(accountRequestDto.getName());
        accountService.update(account);
    }

    @Override
    public void delete(Long id) {
        accountService.delete(id);
    }

    @Override
    public AccountResponseDto findById(Long id) {
        return new AccountResponseDto(accountService.findById(id));
    }

    @Override
    public PageData<AccountResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Account> tableResponse = accountService.findAll(dataTableRequest);

        List<AccountResponseDto> accounts = tableResponse.getItems().stream().
                map(AccountResponseDto::new).
                collect(Collectors.toList());
        PageData<AccountResponseDto> pageData = (PageData<AccountResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(accounts);
        return pageData;
    }

    @Override
    public User findUserByAccountId(Long accountId) {
        return accountService.findUserByAccountId(accountId);
    }

}

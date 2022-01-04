package ua.com.alevel.service;

import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.User;

import java.sql.SQLException;

public interface AccountService extends BaseService<Account> {
    User findUserByAccountId(Long accountId);
}

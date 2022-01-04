package ua.com.alevel.dao;

import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.User;

import java.sql.SQLException;

public interface AccountDao extends BaseDao<Account> {
    User findUserByAccountId(Long accountId);
}

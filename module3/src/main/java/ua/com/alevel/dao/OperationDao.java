package ua.com.alevel.dao;

import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.Operation;

import java.sql.SQLException;
import java.util.List;

public interface OperationDao extends BaseDao<Operation>{
    Category findCategoryByName(String Name) throws SQLException;
    Account findAccountById(Long id);
    List<Operation> findOperationsByAccountId(Long id);
    List<Operation> findOperationsByUserId(Long id);
}

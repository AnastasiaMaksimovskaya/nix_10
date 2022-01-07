package ua.com.alevel.service;

import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.Operation;

import java.sql.SQLException;

public interface OperationService extends BaseService<Operation> {
    Category findCategoryByName(String name) throws SQLException;

    void changeAccBalance(Integer sum,Long operationId,Boolean isIncome);

    void writeOutByAccId(Long id);
    void writeOutByUserId(Long id);
}

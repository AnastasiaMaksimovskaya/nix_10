package ua.com.alevel.dao;

import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.Operation;

import java.sql.SQLException;

public interface OperationDao extends BaseDao<Operation>{
    Category findCategoryByName(String Name) throws SQLException;

}

package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dao.OperationDao;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.Operation;
import ua.com.alevel.service.OperationService;
import ua.com.alevel.util.WebResponseUtil;

import java.sql.SQLException;

@Service
public class OperationServiceImpl implements OperationService {

    private final OperationDao operationDao;

    public OperationServiceImpl(OperationDao operationDao) {
        this.operationDao = operationDao;
    }

    @Override
    public void create(Operation entity) {
        operationDao.create(entity);
    }

    @Override
    public void update(Operation entity) {
        operationDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        operationDao.delete(id);
    }

    @Override
    public Operation findById(Long id) {
        return operationDao.findById(id);
    }

    @Override
    public DataTableResponse<Operation> findAll(DataTableRequest request) {
        DataTableResponse<Operation> dataTableResponse = operationDao.findAll(request);
        long count = operationDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;    }
    @Override
    public Category findCategoryByName(String name) throws SQLException {
        return operationDao.findCategoryByName(name);
    }
}

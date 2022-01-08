package ua.com.alevel.service.impl;

import com.opencsv.CSVWriter;
import org.springframework.stereotype.Service;
import ua.com.alevel.dao.AccountDao;
import ua.com.alevel.dao.OperationDao;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.Operation;
import ua.com.alevel.exception.NotEnoughMoneyException;
import ua.com.alevel.service.OperationService;
import ua.com.alevel.util.Parser;
import ua.com.alevel.util.WebResponseUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    private final OperationDao operationDao;
    private final AccountDao accountDao;

    public OperationServiceImpl(OperationDao operationDao, AccountDao accountDao) {
        this.operationDao = operationDao;
        this.accountDao = accountDao;
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
        return dataTableResponse;
    }

    @Override
    public Category findCategoryByName(String name) throws SQLException {
        return operationDao.findCategoryByName(name);
    }

    @Override
    public void changeAccBalance(Long sum, Long operationId, Boolean isIncome) {
        Account account = operationDao.findAccountById(operationId);
        long balance = account.getBalance();
        if (!isIncome && balance - sum < 0) {
            System.out.println("balance = " + balance);
            System.out.println("sum = " + sum);
            throw new NotEnoughMoneyException("на Вашем счету недостаточно средств");
        } else {
            System.out.println("balance = " + (isIncome ? balance + sum : balance - sum));
            account.setBalance(isIncome ? balance + sum : balance - sum);
            accountDao.update(account);
        }
    }

    @Override
    public void writeOutByAccId(Long id) {
        List<Operation> operations = operationDao.findOperationsByAccountId(id);
        String csv = "acc" + id + ".csv";
        File file = new File(csv);
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            for (Operation operation : operations) {
                String values[] = {operation.getAccount().getUser().getEmail(), operation.getCategory().getName().name(), operation.getCategory().getIncome() ? "+" : "-", String.valueOf(Parser.convertFromKopeyka(operation.getSum()))};
                writer.writeNext(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeOutByUserId(Long id) {
        List<Operation> operations = operationDao.findOperationsByUserId(id);
        String csv = "user" + id + ".csv";
        File file = new File( csv);
        System.out.println("OperationServiceImpl.writeOutByUserId");
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            for (Operation operation : operations) {
                String values[] = {operation.getAccount().getName(), operation.getAccount().getUser().getEmail(), operation.getCategory().getName().name(), operation.getCategory().getIncome() ? "+" : "-",String.valueOf(Parser.convertFromKopeyka(operation.getSum()))};
                writer.writeNext(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

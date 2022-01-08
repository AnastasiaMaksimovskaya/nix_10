package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.dao.AccountDao;
import ua.com.alevel.dao.OperationDao;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.Operation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OperationDaoImpl implements OperationDao {

    private static final String OUT_ACC_ID = "select * from operations where account_id = ";
    private static final String OUT_US_ID = "select o.id,o.account_id,o.category_id,o.sum,o.created from operations as o inner join accounts as a on o.account_id = a.id where a.user_id = ";

    private final JpaConfig jpaConfig;
    private final AccountDao accountDao;

    @PersistenceContext
    EntityManager entityManager;

    public OperationDaoImpl(JpaConfig jpaConfig, AccountDao accountDao) {
        this.jpaConfig = jpaConfig;
        this.accountDao = accountDao;
    }

    @Override
    public void create(Operation entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Operation entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public boolean existById(Long id) {
        return entityManager.contains(findById(id));
    }

    @Override
    public Operation findById(Long id) {
        return entityManager.find(Operation.class, id);
    }

    @Override
    public DataTableResponse<Operation> findAll(DataTableRequest request) {
        int fr = (request.getCurrentPage() - 1) * request.getPageSize();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Operation> criteriaQuery = criteriaBuilder.createQuery(Operation.class);
        Root<Operation> from = criteriaQuery.from(Operation.class);
        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }
        List<Operation> operations = entityManager.createQuery(criteriaQuery)
                .setFirstResult(fr)
                .setMaxResults(request.getPageSize())
                .getResultList();
        DataTableResponse<Operation> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(operations);
        return dataTableResponse;
    }

    @Override
    public long count() {
        Query query = entityManager.createQuery("select count(id) from Operation ");
        return (Long) query.getSingleResult();
    }

    @Override
    public Category findCategoryByName(String name) throws SQLException {
        Query query = entityManager.createNativeQuery("select id,is_income,name,created from categories where name = '" + name + "'", Category.class);
        return ((Category) query.getSingleResult());
    }

    public Category findCategoryById(Long id) throws SQLException {
        Query query = entityManager.createNativeQuery("select id,is_income,name,created from categories where id = " + id, Category.class);
        return ((Category) query.getSingleResult());
    }

    @Override
    public Account findAccountById(Long id) {
        Query query = entityManager.createNativeQuery("select a.id,balance,user_id,a.name,a.created from accounts a left join operations o on a.id = o.account_id where o.id = " + id, Account.class);
        return ((Account) query.getSingleResult());
    }

    @Override
    public List<Operation> findOperationsByAccountId(Long id) {
        List<Operation> operations = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(OUT_ACC_ID + id)) {
            while (resultSet.next()) {
                operations.add(convertResultSetToOperation(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return operations;
    }

    @Override
    public List<Operation> findOperationsByUserId(Long id) {
        List<Operation> operations = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(OUT_US_ID + id)) {
            while (resultSet.next()) {
                operations.add(convertResultSetToOperation(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return operations;
    }

    private Operation convertResultSetToOperation(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Long sum = resultSet.getLong("sum");
        Long accId = resultSet.getLong("account_id");
        Long catId = resultSet.getLong("category_id");
        Timestamp created = resultSet.getTimestamp("created");
        Operation operation = new Operation();
        operation.setSum(sum);
        operation.setId(id);
        operation.setAccount(accountDao.findById(accId));
        operation.setCategory(findCategoryById(catId));
        operation.setCreated(new Date(created.getTime()));
        return operation;
    }
}

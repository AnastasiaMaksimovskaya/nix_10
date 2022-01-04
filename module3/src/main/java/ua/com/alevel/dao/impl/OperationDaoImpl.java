package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Service;
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
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OperationDaoImpl implements OperationDao {

    @PersistenceContext
    EntityManager entityManager;

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
        return dataTableResponse;    }

    @Override
    public long count() {
        Query query = entityManager.createQuery("select count(id) from Operation ");
        return (Long) query.getSingleResult();    }
    @Override
    public Category findCategoryByName(String name) throws SQLException {
        Query query = entityManager.createNativeQuery("select id,is_income,name,created from categories where name = '" +name+"'",Category.class);
        return ((Category) query.getSingleResult());
    }
}

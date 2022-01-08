package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.dao.UserDao;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

@Service
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(User entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(User entity) {
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
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public DataTableResponse<User> findAll(DataTableRequest request) {
        Long count = (Long) entityManager.createQuery("select count(id) from Category").getSingleResult();
        if (count == 0) {
            initCategories();
        }
        int fr = (request.getCurrentPage() - 1) * request.getPageSize();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> from = criteriaQuery.from(User.class);
        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }
        List<User> users = entityManager.createQuery(criteriaQuery)
                .setFirstResult(fr)
                .setMaxResults(request.getPageSize())
                .getResultList();
        DataTableResponse<User> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(users);
        return dataTableResponse;
    }

    @Override
    public long count() {
        Query query = entityManager.createQuery("select count(id) from User");
        return (Long) query.getSingleResult();
    }

    @Override
    public Map<Long, String> findAllAccountsByUserId(Long userId) {
        Map<Long, String> map = new HashMap<>();
        Set<Account> accounts = findById(userId).getAccounts();
        for (Account account : accounts) {
            map.put(account.getId(), account.getName());
        }
        return map;
    }

    private void initCategories() {
        for (int i = 0; i < Category.Name.values().length; i++) {
            Category category = new Category();
            category.setName(Category.Name.values()[i]);
            if (Category.Name.values()[i].getIsIncome().equals("+")) {
                category.setIncome(true);
            } else category.setIncome(false);
            category.setCreated(new Date(System.currentTimeMillis()));
            entityManager.persist(category);
        }
    }
}

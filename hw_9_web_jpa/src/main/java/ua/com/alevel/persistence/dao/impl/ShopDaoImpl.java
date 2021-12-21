package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.ShopDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Product;
import ua.com.alevel.persistence.entity.Shop;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
@Transactional
public class ShopDaoImpl implements ShopDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Shop entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Shop entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        entityManager.createQuery("delete from Shop s where s.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public boolean existById(Long id) {
        Query query = entityManager.createQuery("select count(s.id) from Shop s where s.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;    }

    @Override
    public Shop findById(Long id) {
        return entityManager.find(Shop.class, id);    }

    @Override
    public DataTableResponse<Shop> findAll(DataTableRequest request) {

        int fr = (request.getCurrentPage() - 1) * request.getPageSize();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Shop> criteriaQuery = criteriaBuilder.createQuery(Shop.class);
        Root<Shop> from = criteriaQuery.from(Shop.class);
        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }
        List<Shop> shops = entityManager.createQuery(criteriaQuery)
                .setFirstResult(fr)
                .setMaxResults(request.getPageSize())
                .getResultList();
        DataTableResponse<Shop> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(shops);
        return dataTableResponse;
    }


    @Override
    public long count() {
        Query query = entityManager.createQuery("select count(id) from Shop");
        return (Long) query.getSingleResult();
    }

    @Override
    public Map<Long, String> findAllProductsByShopId(Long shopId) {
        Map<Long, String> map = new HashMap<>();
        Set<Product> products = findById(shopId).getProducts();
        for (Product product : products) {
            map.put(product.getId(),product.getName());
        }
        return map;
    }
}

package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.ProductDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
public class ProductDaoImpl implements ProductDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Product entity) {

    }

    @Override
    public void update(Product entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<Product> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Map<Long, String> findAllByShopId(Long shopId) {
        return null;
    }

    @Override
    public void createRelationship(Product p, List<Integer> shopsId) {

    }
}

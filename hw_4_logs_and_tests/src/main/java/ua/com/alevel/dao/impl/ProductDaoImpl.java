package ua.com.alevel.dao.impl;

import ua.com.alevel.config.ActiveClass;
import ua.com.alevel.dao.ProductDao;
import ua.com.alevel.db.ProductDB;
import ua.com.alevel.db.impl.ProductDb;
import ua.com.alevel.entity.Product;

@ActiveClass
public class ProductDaoImpl implements ProductDao {

    protected final ProductDB db = ProductDb.getInstance();

    @Override
    public void create(Product product) {
        db.create(product);
    }

    @Override
    public void update(Product product) {
        db.update(product);
    }

    @Override
    public void delete(String id) {
        db.delete(id);
    }

    @Override
    public Product findById(String id) {
        return db.findById(id);
    }

    @Override
    public Product[] findAll() {
        return db.findAll();
    }

    @Override
    public String findShop(String id) {
        return db.findShop(id);
    }
}
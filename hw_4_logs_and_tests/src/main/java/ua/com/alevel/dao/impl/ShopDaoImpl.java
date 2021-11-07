package ua.com.alevel.dao.impl;

import ua.com.alevel.config.ActiveClass;
import ua.com.alevel.dao.ShopDao;
import ua.com.alevel.db.impl.ShopDb;
import ua.com.alevel.entity.Product;
import ua.com.alevel.entity.Shop;

@ActiveClass
public class ShopDaoImpl implements ShopDao {

    private final ShopDb db = ShopDb.getInstance();

    @Override
    public void create(Shop shop) {
        db.create(shop);
    }

    @Override
    public void update(Shop shop) {
        db.update(shop);
    }

    @Override
    public void delete(String id) {
        db.delete(id);
    }

    @Override
    public Shop findById(String id) {
        return db.findById(id);
    }

    @Override
    public Shop[] findAll() {
        return db.findAll();
    }

    @Override
    public boolean existByAddress(String address) {
        return db.existByAddress(address);
    }

    @Override
    public Product[] findAllProducts(Shop shop) {
        return db.findAllProducts(shop);
    }
}
package ua.com.alevel.dao.impl;

import ua.com.alevel.exception.ShopNotFoundException;
import ua.com.alevel.config.ActiveClass;
import ua.com.alevel.dao.ShopDao;
import ua.com.alevel.db.impl.ShopDb;
import ua.com.alevel.entity.Product;
import ua.com.alevel.entity.Shop;

import java.util.List;

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
    public Shop findById(String id) throws ShopNotFoundException {
        return db.findById(id);
    }

    @Override
    public List<Shop> findAll() {
        return db.findAll();
    }

    @Override
    public boolean existByAddress(String address) {
        return db.existByAddress(address);
    }

    @Override
    public List<Product> findAllProducts(String shopId) {
        return db.findAllProducts(shopId);
    }
}
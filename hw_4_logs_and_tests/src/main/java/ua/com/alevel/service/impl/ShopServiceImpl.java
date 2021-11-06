package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.config.ActiveClass;
import ua.com.alevel.config.GenerateImplementationFromInterfaceFactory;
import ua.com.alevel.dao.ShopDao;
import ua.com.alevel.entity.Product;
import ua.com.alevel.entity.Shop;
import ua.com.alevel.service.ShopService;

@ActiveClass
public class ShopServiceImpl implements ShopService {
    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");

    private final ShopDao shopDao = GenerateImplementationFromInterfaceFactory
            .generateImplementation(ShopDao.class);

    @Override
    public void create(Shop entity) {
        LOGGER_INFO.info("start create " + entity);
        if (!shopDao.existByAddress(entity.getAdress())) {
            shopDao.create(entity);
        } else {
            System.out.println("shop exist by address");
            LOGGER_WARN.warn("shop exist by address" + entity.getAdress());
        }
        LOGGER_INFO.info("finish create " + entity);

    }

    @Override
    public void update(Shop entity) {
        LOGGER_INFO.info("start update " + entity);
        shopDao.update(entity);
        LOGGER_INFO.info("finish update " + entity);

    }

    @Override
    public void delete(String id) {
        LOGGER_INFO.info("start delete shop by id  " + id);
        shopDao.delete(id);
        LOGGER_INFO.info("finish delete shop by id  " + id);
    }

    @Override
    public Shop findById(String id) {
        LOGGER_INFO.info("finding shop by id  " + id);
        return shopDao.findById(id);
    }

    @Override
    public Shop[] findAll() {
        LOGGER_INFO.info("finding all shops");
        return shopDao.findAll();
    }

    @Override
    public boolean existByAddress(String address) {
        return shopDao.existByAddress(address);
    }

    @Override
    public Product[] findAllProducts(Shop shop) {
        LOGGER_INFO.info("finding all products in " + shop);
        return shopDao.findAllProducts(shop);
    }

}

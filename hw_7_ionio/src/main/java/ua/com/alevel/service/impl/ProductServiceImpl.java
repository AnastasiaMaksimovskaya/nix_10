package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.exception.ProductNotFoundException;
import ua.com.alevel.exception.ShopNotFoundException;
import ua.com.alevel.config.ActiveClass;
import ua.com.alevel.config.GenerateImplementationFromInterfaceFactory;
import ua.com.alevel.dao.ProductDao;
import ua.com.alevel.entity.Product;
import ua.com.alevel.service.ProductService;

import java.util.List;

@ActiveClass
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");

    private final ProductDao productDao = GenerateImplementationFromInterfaceFactory
            .generateImplementation(ProductDao.class);

    @Override
    public void create(Product product) {
        LOGGER_INFO.info("start create " + product);
        productDao.create(product);
        LOGGER_INFO.info("finish create " + product);
    }

    @Override
    public void update(Product product) {
        LOGGER_INFO.info("start update " + product.getId());
        productDao.update(product);
        LOGGER_INFO.info("finish update " + product.getId());

    }

    @Override
    public void delete(String id) {
        LOGGER_INFO.info("start delete product by id " + id);
        productDao.delete(id);
        LOGGER_INFO.info("finish delete product by id " + id);
    }

    @Override
    public Product findById(String id) throws ShopNotFoundException, ProductNotFoundException {
        LOGGER_INFO.info("finding product by id " + id);
        return productDao.findById(id);
    }

    @Override
    public List<Product> findAll() {
        LOGGER_INFO.info("finding all products");
        return productDao.findAll();
    }

    @Override
    public String findShop(String id) {
        LOGGER_INFO.info("finding shop address byproduct id " + id);
        return productDao.findShop(id);
    }
}

package ua.com.alevel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.persistence.dao.ProductDao;
import ua.com.alevel.persistence.dao.ShopDao;
import ua.com.alevel.persistence.entity.Product;
import ua.com.alevel.persistence.entity.Shop;
import ua.com.alevel.service.ProductService;
import ua.com.alevel.service.ShopService;

import java.util.Date;

@SpringBootTest
class Hw9WebJpaApplicationTests {

    @Autowired
    ShopService shopService;
    @Autowired
    ProductService productService;

    @Test
    void contextLoads() {

    }

    @Test
    void init(){
        for (int i = 0; i < 5; i++) {
            Shop shop = new Shop();
            shop.setUpdated(new Date(System.currentTimeMillis()));
            shop.setCreated(new Date(System.currentTimeMillis()));
            shop.setName("shop" + (i + 1));
            shop.setAddress("address" + (i + 1));
            shopService.create(shop);
            System.out.println("shop = " + shop.getId());
        }
        for (long i = 0; i < 5; i++) {
            Product product = new Product();
            product.setUpdated(new Date(System.currentTimeMillis()));
            product.setCreated(new Date(System.currentTimeMillis()));
            product.setName("product" + (i + 1));
            product.setBrand("brand" + (i + 1));
            product.setPrice ((int) (i + 100));
            productService.create(product);
            Shop shop = shopService.findById(i+1);
            (shop).addProduct(product);
            shopService.update(shop);
        }

    }

}

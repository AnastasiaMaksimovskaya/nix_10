package ua.com.alevel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.persistence.dao.ShopDao;
import ua.com.alevel.persistence.entity.Shop;

import java.util.Date;

@SpringBootTest
class Hw9WebJpaApplicationTests {

    @Autowired
    ShopDao shopDao;

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
            shopDao.create(shop);
        }
    }

}

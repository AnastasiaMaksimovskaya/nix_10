package ua.com.alevel;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.persistence.entity.Product;
import ua.com.alevel.persistence.entity.Shop;
import ua.com.alevel.service.ProductService;
import ua.com.alevel.service.ShopService;

import java.util.Date;
import java.util.Random;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RepositoryApplicationTests {

    private static final Integer DEFAULT_SIZE_OF_ITEMS = 10;

    @Value("${init}")
    private Boolean init;

    @Autowired
    ShopService shopService;
    @Autowired
    ProductService productService;

    @Test
    void contextLoads() {

    }

    @Test
    @Order(1)
    void init() {
        if (init) {
            for (int i = 0; i < DEFAULT_SIZE_OF_ITEMS; i++) {
                Shop shop = new Shop();
                shop.setUpdated(new Date(System.currentTimeMillis()));
                shop.setCreated(new Date(System.currentTimeMillis()));
                shop.setName("shop" + (i + 1));
                shop.setAddress("address" + (i + 1));
                shopService.create(shop);
            }
            for (long i = 0; i < DEFAULT_SIZE_OF_ITEMS; i++) {
                Product product = new Product();
                product.setUpdated(new Date(System.currentTimeMillis()));
                product.setCreated(new Date(System.currentTimeMillis()));
                product.setName("product" + (i + 1));
                product.setBrand("brand" + (i + 1));
                product.setPrice((int) (i + 100));
                productService.create(product);
                Shop shop = shopService.findById(i + 1);
                (shop).addProduct(product);
                shopService.update(shop);
                Assertions.assertEquals(shopService.findAllProductsByShopId(i + 1).size(), 1);
            }
        }
    }

    @Test
    @Order(2)
    void shouldBeUpdatedShop() {
        Random random = new Random();
        long id = random.nextInt(DEFAULT_SIZE_OF_ITEMS);
        Shop shop = shopService.findById(id + 1);
        shop.setName("New");
        shopService.update(shop);
        Assertions.assertEquals(shop.getName(), "New");
    }

    @Test
    @Order(3)
    void shouldBeUpdatedProduct() {
        Random random = new Random();
        long id = random.nextInt(DEFAULT_SIZE_OF_ITEMS);
        Product product = productService.findById(id + DEFAULT_SIZE_OF_ITEMS + 1);
        product.setPrice(666);
        productService.update(product);
        Assertions.assertEquals(product.getPrice(), 666);
    }
}

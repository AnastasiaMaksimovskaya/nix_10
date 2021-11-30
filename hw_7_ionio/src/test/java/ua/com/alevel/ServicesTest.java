package ua.com.alevel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import ua.com.alevel.config.ConfigFiles;
import ua.com.alevel.entity.Product;
import ua.com.alevel.entity.Shop;
import ua.com.alevel.exception.ProductNotFoundException;
import ua.com.alevel.exception.ShopNotFoundException;
import ua.com.alevel.service.impl.ProductServiceImpl;
import ua.com.alevel.service.impl.ShopServiceImpl;

import java.util.List;

public class ServicesTest {
    public static final ProductServiceImpl productService = new ProductServiceImpl();
    public static final ShopServiceImpl shopService = new ShopServiceImpl();

    private static final int DEFAULT_SIZE_PRODUCTS = 5;
    private static final int PRICE = 100;
    private static final String ID = "100";
    private static final String SHOP_ID = "test shop id";
    private static final int PRICE_UPDATE = 10;
    private static final String NAME_PRODUCTS = "Product";
    private static final String BRAND = "Brand";
    private static final Shop SHOP = generateRandomShop();


    private static final int DEFAULT_SIZE = 9;
    private static final String NAME = "Shop";
    private static final String ADDRESS = "Address";
    private static final String NAME_UPDATE = "ShopUpdate";

    @BeforeAll
    @Order(1)
    public static void setUpShops() {
        new ConfigFiles().configFiles();
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            Shop shop = generateShop(NAME + i, i + ADDRESS);
            shopService.create(shop);
        }
        Assertions.assertEquals(shopService.findAll().size(), DEFAULT_SIZE);
    }

    @BeforeAll
    @Order(2)
    public static void setUpProducts() {
        for (int i = 0; i < DEFAULT_SIZE_PRODUCTS; i++) {
            Product product = generateProduct(NAME_PRODUCTS + i, i + BRAND, PRICE + i, shopService.findAll().get(0).getId());
            productService.create(product);
        }
        Assertions.assertEquals(productService.findAll().size(), DEFAULT_SIZE_PRODUCTS);
    }


    @Test
    @Order(1)
    public void shouldBeCreateShopWhenAddressIsNotDuplicate() {
        Shop shop = generateRandomShop();
        shopService.create(shop);
        List<Shop> shops = shopService.findAll();
        Assertions.assertEquals(shops.size(), DEFAULT_SIZE + 1);
    }

    @Test
    @Order(2)
    public void shouldBeCreateShopWhenAddressIsDuplicate() {
        Shop shop = generateRandomShop();
        shopService.create(shop);
        List<Shop> shops = shopService.findAll();
        Assertions.assertEquals(shops.size(), DEFAULT_SIZE + 1);
    }

    @Test
    @Order(3)
    public void shouldBeUpdateShopById(){
        Shop shop = getRandomShop();
        shop.setName(NAME_UPDATE);
        shopService.update(shop);
        try {
            shop = shopService.findById(shop.getId());
        } catch (ShopNotFoundException | ProductNotFoundException e) {
            System.out.println(e);
        }
        Assertions.assertEquals(NAME_UPDATE, shop.getName());
    }

    @Test
    @Order(4)
    public void shouldBeFindAllProducts() {
        List<Product> products = productService.findAll();
        Assertions.assertEquals(productService.findAll().size(), DEFAULT_SIZE_PRODUCTS);
    }

    @Test
    @Order(5)
    public void shouldBeFindShop() {
        Product product = getRandomProduct();
        Assertions.assertEquals(productService.findShop(product.getId()), product.getShopId());
    }

    @Test
    @Order(6)
    public void shouldBeCreateProduct() {
        Product product = generateRandomProduct();
        productService.create(product);
        List<Product> products = productService.findAll();
        Assertions.assertEquals(products.size(), DEFAULT_SIZE_PRODUCTS + 1);
    }

    @Test
    @Order(7)
    public void shouldBeUpdateProductById() {
        Product product = getRandomProduct();
        product.setPrice(PRICE_UPDATE);
        productService.update(product);
        try {
            product = productService.findById(product.getId());
        } catch (ShopNotFoundException | ProductNotFoundException e) {
            System.out.println(e);
        }
        Assertions.assertEquals(PRICE_UPDATE, product.getPrice());
    }

    @Test
    @Order(8)
    public void shouldBeDeleteProductById() {
        Product product = getRandomProduct();
        Shop shop = null;
        try {
            shop = shopService.findById(product.getShopId());
        } catch (ShopNotFoundException | ProductNotFoundException e) {
            System.out.println(e);
        }
        productService.delete(product.getId());
        Assertions.assertEquals(productService.findAll().size(), DEFAULT_SIZE_PRODUCTS);
        List<Product> products = shopService.findAllProducts(shop.getId());
        Assertions.assertEquals(products.size(), DEFAULT_SIZE_PRODUCTS - 1);
    }

    @Test
    @Order(9)
    public void shouldBeDeleteShopById() {
        Shop shop = generateRandomShop();
        shopService.delete(shop.getId());
        Assertions.assertEquals(shopService.findAll().size(), DEFAULT_SIZE + 1);
    }

    private static Shop generateRandomShop() {
        Shop shop = new Shop();
        shop.setId(ID);
        shop.setName(NAME);
        shop.setAddress(ADDRESS);
        return shop;
    }

    private static Shop generateShop(String name, String address) {
        Shop shop = new Shop();
        shop.setName(name);
        shop.setAddress(address);
        return shop;
    }

    private static Product generateRandomProduct() {
        Product product = new Product();
        product.setName(NAME_PRODUCTS);
        product.setBrand(BRAND);
        product.setPrice(PRICE);
        product.setShopId(SHOP_ID);
        return product;
    }

    private static Product generateProduct(String name, String brand, int price, String shopId) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setBrand(brand);
        product.setShopId(shopId);
        return product;
    }

    private static Product getRandomProduct() {
        return productService.findAll().stream().findFirst().get();
    }

    private static Shop getRandomShop() {
        return shopService.findAll().stream().findFirst().get();
    }
}
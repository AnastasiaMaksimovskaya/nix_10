package ua.com.alevel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import ua.com.alevel.entity.Product;
import ua.com.alevel.entity.Shop;
import ua.com.alevel.service.impl.ProductServiceImpl;
import ua.com.alevel.service.impl.ShopServiceImpl;

public class ServicesTest {
    public static final ProductServiceImpl productService = new ProductServiceImpl();
    public static final ShopServiceImpl shopService = new ShopServiceImpl();

    private static final int DEFAULT_SIZE_PRODUCTS = 100;
    private static final int PRICE = 100;
    private static final String ID = "100";
    private static final int PRICE_UPDATE = 10;
    private static final String NAME_PRODUCTS = "Product";
    private static final String BRAND = "Brand";
    private static final Shop SHOP = generateRandomShop();


    private static final int DEFAULT_SIZE = 100;
    private static final String NAME = "Shop";
    private static final String ADDRESS = "Address";
    private static final String NAME_UPDATE = "ShopUpdate";

    @BeforeAll
    @Order(1)
    public static void setUpShops() {
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            Shop shop = generateShop(NAME + i, i + ADDRESS);
            shopService.create(shop);
        }
        Assertions.assertEquals(shopService.findAll().length, DEFAULT_SIZE);
    }

    @BeforeAll
    @Order(2)
    public static void setUpProducts() {
        for (int i = 0; i < DEFAULT_SIZE_PRODUCTS; i++) {
            Product product = generateProduct(NAME_PRODUCTS + i, i + BRAND, PRICE + i, SHOP);
            productService.create(product);
            product.getShop().setProducts(product);
        }
        Assertions.assertEquals(productService.findAll().length, DEFAULT_SIZE_PRODUCTS);
    }

    @Test
    @Order(1)
    public void shouldBeCreateShopWhenAddressIsNotDuplicate() {
        Shop shop = generateRandomShop();
        shopService.create(shop);
        Shop shops[] = shopService.findAll();
        Assertions.assertEquals(shops.length, DEFAULT_SIZE + 1);
    }

    @Test
    @Order(2)
    public void shouldBeCreateShopWhenAddressIsDuplicate() {
        Shop shop = generateRandomShop();
        shopService.create(shop);
        Shop shops[] = shopService.findAll();
        Assertions.assertEquals(shops.length, DEFAULT_SIZE + 1);
    }

    @Test
    @Order(3)
    public void shouldBeUpdateShopById() {
        Shop shop = getRandomShop();
        shop.setName(NAME_UPDATE);
        shopService.update(shop);
        shop = shopService.findById(shop.getId());
        Assertions.assertEquals(NAME_UPDATE, shop.getName());
    }

    @Test
    @Order(4)
    public void shouldBeFindAllProducts() {
        int numberOfNullElements = 0;
        Product products[] = productService.findAll();
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {
                numberOfNullElements++;
            }
        }
        Assertions.assertEquals(productService.findAll().length - numberOfNullElements, DEFAULT_SIZE_PRODUCTS);
    }

    @Test
    @Order(5)
    public void shouldBeFindShop() {
        Product product = getRandomProduct();
        Assertions.assertEquals(productService.findShop(product.getId()), ADDRESS);
    }

    @Test
    @Order(6)
    public void shouldBeCreateProduct() {
        Product product = generateRandomProduct();
        productService.create(product);
        Product products[] = productService.findAll();
        Assertions.assertEquals(products.length, DEFAULT_SIZE_PRODUCTS + 1);
    }

    @Test
    @Order(7)
    public void shouldBeUpdateProductById() {
        Product product = getRandomProduct();
        product.setPrice(PRICE_UPDATE);
        productService.update(product);
        product = productService.findById(product.getId());
        Assertions.assertEquals(PRICE_UPDATE, product.getPrice());
    }

    @Test
    @Order(8)
    public void shouldBeDeleteProductById() {
        int notNullElement = 0;
        Product product = getRandomProduct();
        Shop shop = product.getShop();
        productService.delete(product.getId());
        Assertions.assertEquals(productService.findAll().length, DEFAULT_SIZE_PRODUCTS);
        Product products[] = shopService.findAllProducts(shop);
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null) {
                notNullElement++;
            }
        }
        Assertions.assertEquals(notNullElement, DEFAULT_SIZE_PRODUCTS - 1);
    }

    @Test
    @Order(9)
    public void shouldBeDeleteShopById() {
        int numberOfNotNullElements = 0;
        Shop shop = generateRandomShop();
        Product products[] = shopService.findAllProducts(shop);
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {
                numberOfNotNullElements++;
            }
        }
        shopService.delete(shop.getId());
        Assertions.assertEquals(shopService.findAll().length, DEFAULT_SIZE + 1);
    }

    private static Shop generateRandomShop() {
        Shop shop = new Shop();
        shop.setId(ID);
        shop.setName(NAME);
        shop.setAdress(ADDRESS);
        return shop;
    }

    private static Shop generateShop(String name, String address) {
        Shop shop = new Shop();
        shop.setName(name);
        shop.setAdress(address);
        return shop;
    }

    private static Product generateRandomProduct() {
        Product product = new Product();
        product.setName(NAME_PRODUCTS);
        product.setBrand(BRAND);
        product.setPrice(PRICE);
        product.setShop(SHOP);
        return product;
    }

    private static Product generateProduct(String name, String brand, int price, Shop shop) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setBrand(brand);
        product.setShop(shop);
        return product;
    }

    private static Product getRandomProduct() {
        return productService.findAll()[0];
    }

    private static Shop getRandomShop() {
        return shopService.findAll()[0];
    }
}
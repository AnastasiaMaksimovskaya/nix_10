package ua.com.alevel.db.impl;

import ua.com.alevel.db.ShopDB;
import ua.com.alevel.entity.Product;
import ua.com.alevel.entity.Shop;

import java.util.Arrays;
import java.util.UUID;

public class ShopDb implements ShopDB {

    private Shop[] shops;
    protected Product[] products;

    public static ShopDb instance;

    public ShopDb() {
        shops = new Shop[10];
        products = new Product[10];
    }

    public static ShopDb getInstance() {
        if (instance == null) {
            instance = new ShopDb();
        }
        return instance;
    }

    @Override
    public void create(Shop shop) {
        shop.setId(generateId(shop));
        for (int i = 0; i < shops.length; i++) {
            if (shops[i] == null) {
                shops[i] = shop;
                return;
            }
        }
        changeSize();
        create(shop);
    }

    @Override
    public void update(Shop shop) {
        Shop current = findById(shop.getId());
        if (current != null) {
            current.setName(shop.getName());
        } else {
            System.out.println("there is no shop with id" + shop.getId());
            return;
        }
    }

    @Override
    public void delete(String id) {
        if (findById(id) != null) {
            Product products[] = findAllProducts(findById(id));
            for (int i = 0; i < products.length; i++) {
                if (products[0] != null) {
                    ProductDb.getInstance().delete(products[0].getId());
                }
            }
        }
        int position = -1;
        for (int i = 0; i < shops.length; i++) {
            if (shops[i] != null) {
                if (shops[i].getId().equals(id)) {
                    shops[i] = null;
                    position = i;
                }
            }
        }
        if (position != -1) {
            for (int i = position; i < shops.length - 1; i++) {
                shops[i] = shops[i + 1];
            }
            shops[shops.length - 1] = null;
        } else {
            System.out.println("there is no shop with id = " + id);
            return;
        }
    }

    @Override
    public Shop findById(String id) {
        for (int i = 0; i < shops.length; i++) {
            if (shops[i] != null) {
                if (shops[i].getId().equals(id)) {
                    return shops[i];
                }
            }
        }
        return null;
    }

    @Override
    public Shop[] findAll() {
        int counter = 0;
        for (int i = 0; i < shops.length; i++) {
            if (shops[i] != null) {
                counter++;
            }
        }
        Shop newShops[] = Arrays.copyOf(shops, counter);
        return newShops;
    }

    @Override
    public boolean existByAddress(String address) {

        for (int i = 0; i < shops.length; i++) {
            if (shops[i] == null) {
                return false;
            }
            if (shops[i].getAdress().equals(address)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Product[] findAllProducts(Shop shop) {
        return shop.getProducts();
    }

    public void changeSize() {
        Shop newShops[] = new Shop[shops.length * 2];
        for (int i = 0; i < shops.length; i++) {
            newShops[i] = shops[i];
        }
        shops = newShops;
    }

    private String generateId(Shop shop) {
        String id = UUID.randomUUID().toString();
        if (findById(id) == null) {
            return id;
        }
        return generateId(shop);
    }
}
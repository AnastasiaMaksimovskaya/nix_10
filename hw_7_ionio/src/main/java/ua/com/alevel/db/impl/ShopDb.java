package ua.com.alevel.db.impl;

import ua.com.alevel.MyCsvReader;
import ua.com.alevel.MyCsvWriter;
import ua.com.alevel.exception.ShopNotFoundException;
import ua.com.alevel.db.ShopDB;
import ua.com.alevel.entity.Product;
import ua.com.alevel.entity.Shop;
import ua.com.alevel.util.UpdateAndDeleteByRewriting;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopDb implements ShopDB {
    private final String FILE_PATH = ".\\src\\main\\java\\ua\\com\\alevel\\files\\";

    File products = new File(FILE_PATH + "product.csv");
    MyCsvReader myCsvReaderPd = new MyCsvReader(products);
    File shops = new File(FILE_PATH + "shop.csv");
    MyCsvWriter myCsvWriterSh = new MyCsvWriter(shops);
    MyCsvReader myCsvReaderSh = new MyCsvReader(shops);

    public static ShopDb instance;

    public static ShopDb getInstance() {
        if (instance == null) {
            instance = new ShopDb();
        }
        return instance;
    }

    @Override
    public void create(Shop shop) {
        shop.setId(generateId(shop));
        myCsvWriterSh.write(shop.getId(), shop.getAddress(), shop.getName());
    }

    @Override
    public void update(Shop shop) {
        List<String[]> allInCsv = myCsvReaderSh.read();
        List<String> shopLines = new ArrayList<>();
        for (int i = 0; i < allInCsv.size(); i++) {
            if (allInCsv.get(i)[0].equals(shop.getId())) {
                shopLines.add(allInCsv.get(i)[0] + " " + allInCsv.get(i)[1] + " " + allInCsv.get(i)[2] + " ");
            }
        }
        String id = shop.getId();
        Shop current = null;
        try {
            current = findById(id);
        } catch (ShopNotFoundException e) {
            System.out.println(e);
            return;
        }
        current.setName(shop.getName());
        try {
            new UpdateAndDeleteByRewriting().updateEntity(shopLines, shops, current.getId() + " " + current.getAddress() + " " + current.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        List<String> productStrings = new ArrayList<>();
        List<Product> productsByShop = findAllProducts(id);
        for (int i = 0; i < productsByShop.size(); i++) {
            productStrings.add(productsByShop.get(i).getId());
        }
        List<String[]> allInCsv = myCsvReaderSh.read();
        List<String[]> allInProducts = myCsvReaderPd.read();
        List<String> shopLines = new ArrayList<>();
        List<String> productLines = new ArrayList<>();
        for (int i = 0; i < allInCsv.size(); i++) {
            if (allInCsv.get(i)[0].equals(id)) {
                shopLines.add(allInCsv.get(i)[0] + " " + allInCsv.get(i)[1] + " " + allInCsv.get(i)[2] + " ");
            }
        }
        try {
            new UpdateAndDeleteByRewriting().deleteEntity(shopLines, shops);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < allInProducts.size(); i++) {
            if (allInProducts.get(i)[4].equals(id)) {
                productLines.add(allInProducts.get(i)[0] + " " + allInProducts.get(i)[1] + " " + allInProducts.get(i)[2] + " " + allInProducts.get(i)[3] + " " + allInProducts.get(i)[4]);
            }
        }
        try {
            new UpdateAndDeleteByRewriting().deleteEntity(productLines, products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Shop findById(String id) throws ShopNotFoundException {
        List<String[]> allInCsv = myCsvReaderSh.read();
        for (int i = 0; i < allInCsv.size(); i++) {
            if (allInCsv.get(i)[0].equals(id)) {
                Shop shop = new Shop();
                shop.setId(id);
                shop.setAddress(allInCsv.get(i)[1]);
                shop.setName(allInCsv.get(i)[2]);
                return shop;
            }
        }
        throw new ShopNotFoundException();
    }

    @Override
    public List<Shop> findAll() {
        List<Shop> shops = new ArrayList<>();
        List<String[]> allInCsv = myCsvReaderSh.read();
        for (int i = 0; i < allInCsv.size(); i++) {
            Shop shop = new Shop();
            shop.setId(allInCsv.get(i)[0]);
            shop.setAddress(allInCsv.get(i)[1]);
            shop.setName(allInCsv.get(i)[2]);
            shops.add(shop);
        }
        return shops;
    }

    @Override
    public boolean existByAddress(String address) {
        List<String[]> allInCsv = myCsvReaderSh.read();
        for (int i = 0; i < allInCsv.size(); i++) {
            if (allInCsv.get(i)[1].equals(address)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Product> findAllProducts(String shopId) {
        List<String[]> allInProducts = myCsvReaderPd.read();
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < allInProducts.size(); i++) {
            if (allInProducts.get(i)[4].equals(shopId)) {
                Product product = new Product();
                product.setId(allInProducts.get(i)[0]);
                product.setName(allInProducts.get(i)[1]);
                product.setBrand(allInProducts.get(i)[2]);
                product.setPrice(Integer.parseInt(allInProducts.get(i)[3]));
                product.setShopId(allInProducts.get(i)[4]);
                products.add(product);
            }
        }
        return products;
    }

    private String generateId(Shop shop) {
        String id = UUID.randomUUID().toString();
        try {
            findById(id);
        } catch (ShopNotFoundException e) {
            return id;
        }
        return generateId(shop);
    }
}
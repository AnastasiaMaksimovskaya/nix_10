package ua.com.alevel.db.impl;

import ua.com.alevel.MyCsvReader;
import ua.com.alevel.MyCsvWriter;
import ua.com.alevel.ShopNotFoundException;
import ua.com.alevel.db.ShopDB;
import ua.com.alevel.entity.Product;
import ua.com.alevel.entity.Shop;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ShopDb implements ShopDB {

    File shops = new File("shop.csv");
    File productsAndShops = new File("products_and_shops.csv");
    MyCsvWriter myCsvWriterSh = new MyCsvWriter(shops);
    MyCsvReader myCsvReaderSh = new MyCsvReader(shops);
    MyCsvWriter myCsvWriterPdAndSh = new MyCsvWriter(productsAndShops);
    MyCsvReader myCsvReaderPdAndSh = new MyCsvReader(productsAndShops);

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
        try {
            String id = shop.getId();
            Shop current = findById(id);
            delete(shop.getId());
            current.setId(shop.getId());
            current.setName(shop.getName());
            myCsvWriterSh.write(current.getId(),current.getAddress(), current.getName());
        } catch(ShopNotFoundException e){
        System.out.println(e);
    }
}

    @Override
    public void delete(String id) {
        File buffer = new File("buffer.csv");
        List<String[]> allInCsv = myCsvReaderSh.read();
        String shopLine = " ";
        for (int i = 0; i < allInCsv.size(); i++) {
            if (allInCsv.get(i)[0].equals(id)) {
                shopLine = allInCsv.get(i)[0] + " " + allInCsv.get(i)[1] + " " + allInCsv.get(i)[2]+" ";
            }
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(shops));
            BufferedWriter writer = new BufferedWriter(new FileWriter(buffer));
            while (reader.ready()){
                String current = reader.readLine();
                if (!(current.equals(shopLine)||shopLine.equals(current+" ")||current.equals(shopLine+" "))){
                    writer.write(current+"\n");
                }
            }
            writer.flush();
            reader.close();
            writer.close();
            shops.delete();
            buffer.renameTo(shops);
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
    public List<Product> findAllProducts(Shop shop) {
        return Collections.emptyList();
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
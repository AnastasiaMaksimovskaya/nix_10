package ua.com.alevel.db.impl;

import ua.com.alevel.MyCsvReader;
import ua.com.alevel.MyCsvWriter;
import ua.com.alevel.service.ShopNotFoundException;
import ua.com.alevel.db.ShopDB;
import ua.com.alevel.entity.Product;
import ua.com.alevel.entity.Shop;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopDb implements ShopDB {

    File products = new File("product.csv");
    MyCsvWriter myCsvWriterPd = new MyCsvWriter(products);
    MyCsvReader myCsvReaderPd = new MyCsvReader(products);
    File shops = new File("shop.csv");
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

        File buffer = new File("buffer.csv");
        List<String[]> allInCsv = myCsvReaderSh.read();
        String shopLine = " ";
        for (int i = 0; i < allInCsv.size(); i++) {
            if (allInCsv.get(i)[0].equals(shop.getId())) {
                shopLine = allInCsv.get(i)[0] + " " + allInCsv.get(i)[1] + " " + allInCsv.get(i)[2] + " ";
            }
        }
        try {
            String id = shop.getId();
            Shop current = findById(id);
            current.setId(shop.getId());
            current.setName(shop.getName());
            BufferedReader reader = new BufferedReader(new FileReader(shops));
            BufferedWriter writer = new BufferedWriter(new FileWriter(buffer));
            while (reader.ready()) {
                String currentLine = reader.readLine();
                if (!(currentLine.equals(shopLine) || shopLine.equals(currentLine + " ") || currentLine.equals(shopLine + " "))) {
                    writer.write(currentLine + "\n");
                }
            }
            writer.write(current.getId()+" "+ current.getAddress()+" "+ current.getName());
            writer.flush();
            reader.close();
            writer.close();
            shops.delete();
            buffer.renameTo(shops);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (ShopNotFoundException e) {
            System.out.println(e);
        }
    }

    @Override
    public void delete(String id) {
        File buffer = new File("buffer.csv");
        List<String[]> allInCsv = myCsvReaderSh.read();
        List<String[]> allInProducts = myCsvReaderPd.read();
        String shopLine = " ";
        List<String> productLines = new ArrayList<>();
        for (int i = 0; i < allInCsv.size(); i++) {
            if (allInCsv.get(i)[0].equals(id)) {
                shopLine = allInCsv.get(i)[0] + " " + allInCsv.get(i)[1] + " " + allInCsv.get(i)[2] + " ";
            }
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(shops));
            BufferedWriter writer = new BufferedWriter(new FileWriter(buffer));
            while (reader.ready()) {
                String current = reader.readLine();
                if (!(current.equals(shopLine) || shopLine.equals(current + " ") || current.equals(shopLine + " "))) {
                    writer.write(current + "\n");
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
        for (int i = 0; i < allInProducts.size(); i++) {
            if (allInProducts.get(i)[4].equals(id)) {
                productLines.add(allInProducts.get(i)[0] + " " + allInProducts.get(i)[1] + " " + allInProducts.get(i)[2] + " " + allInProducts.get(i)[3] + " " + allInProducts.get(i)[4]);
            }
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(products));
            BufferedWriter writer = new BufferedWriter(new FileWriter(buffer));
            while (reader.ready()) {
                boolean flag = true;
                String current = reader.readLine();
                for (int i = 0; i < productLines.size(); i++) {
                    if ((current.equals(productLines.get(i)) || productLines.get(i).equals(current + " ") || current.equals(productLines.get(i) + " "))) {
                        flag = false;
                    }
                }
                if (flag) {
                    writer.write(current + "\n");
                }

            }
            writer.flush();
            reader.close();
            writer.close();
            products.delete();
            buffer.renameTo(products);
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
        List<String[]> allInProducts = myCsvReaderPd.read();
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < allInProducts.size(); i++) {
            if (allInProducts.get(i)[4].equals(shop.getId())) {
                Product product = new Product();
                product.setId(allInProducts.get(i)[0]);
                product.setName(allInProducts.get(i)[1]);
                product.setBrand(allInProducts.get(i)[2]);
                product.setPrice(Integer.parseInt(allInProducts.get(i)[3]));
                product.setShopId(allInProducts.get(i)[4]);
                products.add(product);            }
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
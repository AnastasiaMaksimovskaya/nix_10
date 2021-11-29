package ua.com.alevel.db.impl;

import ua.com.alevel.MyCsvReader;
import ua.com.alevel.MyCsvWriter;
import ua.com.alevel.ProductNotFoundException;
import ua.com.alevel.db.ProductDB;
import ua.com.alevel.entity.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ProductDb implements ProductDB {

    protected static ProductDb instance;
    File products = new File("product.csv");
    MyCsvWriter myCsvWriterPd = new MyCsvWriter(products);
    MyCsvReader myCsvReaderPd = new MyCsvReader(products);

    public static ProductDb getInstance() {
        if (instance == null) {
            instance = new ProductDb();
        }
        return instance;
    }

    public void create(Product product) {
        product.setId(generateId(product));
        myCsvWriterPd.write(product.getId(), product.getName(), product.getBrand(), String.valueOf(product.getPrice()), product.getShopId());
    }

    public void update(Product product) {
        try {
            Product current = findById(product.getId());
            delete(product.getId());
            current.setId(product.getId());
            current.setPrice(product.getPrice());
            myCsvWriterPd.write(current.getId(), current.getName(), current.getBrand(), String.valueOf(current.getPrice()), current.getShopId());
        } catch (ProductNotFoundException e) {
            System.out.println(e);
        }
    }

    public void delete(String id) {
        File buffer = new File("buffer.csv");
        List<String[]> allInCsv = myCsvReaderPd.read();
        String productLine = " ";
        for (int i = 0; i < allInCsv.size(); i++) {
            if (allInCsv.get(i)[0].equals(id)) {
                productLine = allInCsv.get(i)[0] + " " + allInCsv.get(i)[1] + " " + allInCsv.get(i)[2] + " " + allInCsv.get(i)[3] + " " + allInCsv.get(i)[4] + " ";
            }
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(products));
            BufferedWriter writer = new BufferedWriter(new FileWriter(buffer));
            while (reader.ready()) {
                String current = reader.readLine();
                if (!(current.equals(productLine) || productLine.equals(current + " ") || current.equals(productLine + " "))) {
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


    public Product findById(String id) throws ProductNotFoundException {
        List<String[]> allInCsv = myCsvReaderPd.read();
        for (int i = 0; i < allInCsv.size(); i++) {
            if (allInCsv.get(i)[0].equals(id)) {
                Product product = new Product();
                product.setId(id);
                product.setName(allInCsv.get(i)[1]);
                product.setBrand(allInCsv.get(i)[2]);
                product.setPrice(Integer.parseInt(allInCsv.get(i)[3]));
                product.setShopId(allInCsv.get(i)[4]);
                return product;
            }
        }
        throw new ProductNotFoundException();
    }

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        List<String[]> allInCsv = myCsvReaderPd.read();
        for (int i = 0; i < allInCsv.size(); i++) {
            Product product = new Product();
            product.setId(allInCsv.get(i)[0]);
            product.setName(allInCsv.get(i)[1]);
            product.setBrand(allInCsv.get(i)[2]);
            product.setPrice(Integer.parseInt(allInCsv.get(i)[3]));
            product.setShopId(allInCsv.get(i)[4]);
            products.add(product);
        }
        return products;
    }

    private String generateId(Product product) {
        String id = UUID.randomUUID().toString();
        try {
            findById(id);
        } catch (ProductNotFoundException e) {
            return id;
        }
        return generateId(product);
    }

    public String findShop(String id) {
        try {
            return findById(id).getShopId();
        } catch (ProductNotFoundException e) {
            return ("does not exist");
        }

    }
}
package ua.com.alevel.db.impl;

import ua.com.alevel.MyCsvReader;
import ua.com.alevel.MyCsvWriter;
import ua.com.alevel.exception.ProductNotFoundException;
import ua.com.alevel.db.ProductDB;
import ua.com.alevel.entity.Product;
import ua.com.alevel.util.UpdateAndDeleteByRewriting;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductDb implements ProductDB {
    private final String FILE_PATH = ".\\src\\main\\java\\ua\\com\\alevel\\files\\";

    protected static ProductDb instance;
    File products = new File(FILE_PATH + "product.csv");
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
        List<String[]> allInCsv = myCsvReaderPd.read();
        List<String> productLines = new ArrayList<>();
        Product current = null;
        try {
            current = findById(product.getId());
            if (current==null){
                throw new ProductNotFoundException();
            }
        } catch (ProductNotFoundException e) {
            System.out.println(e);
        }
        delete(product.getId());
        if (current != null) {
            current.setId(product.getId());
        }
        else return;
        current.setPrice(product.getPrice());
        for (int i = 0; i < allInCsv.size(); i++) {
            if (allInCsv.get(i)[0].equals(product.getId())) {
                productLines.add(allInCsv.get(i)[0] + " " + allInCsv.get(i)[1] + " " + allInCsv.get(i)[2] + " " + allInCsv.get(i)[3] + " " + allInCsv.get(i)[4] + " ");
            }
        }
        String updated = current.getId() +" "+ current.getName()+" " + current.getBrand()+" " + current.getPrice()+" " + current.getShopId();
        try {
            new UpdateAndDeleteByRewriting().updateEntity(productLines, products, updated);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        List<String[]> allInCsv = myCsvReaderPd.read();
        List<String> productLines = new ArrayList<>();
        for (int i = 0; i < allInCsv.size(); i++) {
            if (allInCsv.get(i)[0].equals(id)) {
                productLines.add(allInCsv.get(i)[0] + " " + allInCsv.get(i)[1] + " " + allInCsv.get(i)[2] + " " + allInCsv.get(i)[3] + " " + allInCsv.get(i)[4] + " ");
            }
        }
        try {
            new UpdateAndDeleteByRewriting().deleteEntity(productLines, products);
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
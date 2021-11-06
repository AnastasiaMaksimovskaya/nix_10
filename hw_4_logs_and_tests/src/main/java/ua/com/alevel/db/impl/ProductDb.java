package ua.com.alevel.db.impl;

import ua.com.alevel.db.ProductDB;
import ua.com.alevel.entity.Product;

import java.util.Arrays;
import java.util.UUID;

public class ProductDb implements ProductDB {

    private Product[] products;

    protected static ProductDb instance;

    public ProductDb() {
        products = new Product[10];
    }

    public static ProductDb getInstance() {
        if (instance == null) {
            instance = new ProductDb();
        }
        return instance;
    }

    public void create(Product product) {
        product.setId(generateId(product));
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {
                products[i] = product;
                return;
            }
        }
        changeSize();
        create(product);
    }

    public void update(Product product) {
        Product current = findById(product.getId());
        if (current != null) {
            current.setPrice(product.getPrice());
        } else {
            System.out.println("there is no product with id" + product.getId());
            return;
        }
    }

    public void delete(String id) {
        Product productsList[];
        try {
            productsList = findById(id).getShop().getProducts();
        } catch (NullPointerException e) {
            System.out.println("product not found");
            return;
        }
        int position = -1;
        for (int i = 0; i < productsList.length; i++) {
            if (productsList[i] != null) {
                if (productsList[i].getId().equals(id)) {
                    productsList[i] = null;
                    position = i;
                }
            }
        }
        if (position != -1) {
            for (int i = position; i < productsList.length - 1; i++) {
                productsList[i] = productsList[i + 1];
            }
            productsList[productsList.length - 1] = null;
        }
        if (findById(id) != null) {
            findById(id).getShop().setProducts(productsList);
        }
        position = -1;
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null) {
                if (products[i].getId().equals(id)) {
                    products[i] = null;
                    position = i;
                }
            }
        }
        if (position != -1) {
            for (int i = position; i < products.length - 1; i++) {
                products[i] = products[i + 1];
            }
            products[products.length - 1] = null;
        } else System.out.println("there is no product with id = " + id);
    }

    public Product findById(String id) {
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null) {
                if (products[i].getId().equals(id)) {
                    return products[i];
                }
            }
        }
        return null;
    }

    public Product[] findAll() {
        int counter = 0;
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null) {
                counter++;
            }
        }
        Product newProducts[] = Arrays.copyOf(products, counter);
        return newProducts;
    }

    private String generateId(Product product) {
        String id = UUID.randomUUID().toString();
        if (findById(id) == null) {
            return id;
        }
        return generateId(product);
    }

    public void changeSize() {
        Product newProducts[] = new Product[products.length * 2];
        for (int i = 0; i < products.length; i++) {
            newProducts[i] = products[i];
        }
        products = newProducts;
    }

    public String findShop(String id) {
        if (findById(id) == null) {
            return ("does not exist");
        }
        return findById(id).getShop().getAdress();
    }
}
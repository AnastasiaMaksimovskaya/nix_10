package ua.com.alevel.controller;

import ua.com.alevel.exception.ProductNotFoundException;
import ua.com.alevel.exception.ShopNotFoundException;
import ua.com.alevel.config.GenerateImplementationFromInterfaceFactory;
import ua.com.alevel.entity.Product;
import ua.com.alevel.entity.Shop;
import ua.com.alevel.service.ProductService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductController {

    final ProductService productService = GenerateImplementationFromInterfaceFactory.generateImplementation(ProductService.class);

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("select your option");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                crud(position, reader);
                position = reader.readLine();
                if (position.equals("0")) {
                    System.exit(0);
                }
                crud(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        System.out.println();
        System.out.println("if you want add product, please enter 1");
        System.out.println("if you want update product, please enter 2");
        System.out.println("if you want delete product, please enter 3");
        System.out.println("if you want product by id, please enter 4");
        System.out.println("if you want find all products, please enter 5");
        System.out.println("if you want find shop by product, please enter 6");
        System.out.println("if you want back, please enter 7");
        System.out.println("if you want exit, please enter 0");
        System.out.println();
    }

    private void crud(String position, BufferedReader reader) throws IOException {
        switch (position) {
            case "1":
                create(reader);
                break;
            case "2":
                update(reader);
                break;
            case "3":
                delete(reader);
                break;
            case "4":
                findById(reader);
                break;
            case "5":
                findAll(reader);
                break;
            case "6":
                findShop(reader);
                break;
            case "7":
                new MainController().run();
                break;
            case "0":
                System.exit(0);
                break;
        }
        runNavigation();
    }

    private void create(BufferedReader reader) {
        try {
            System.out.println("Please, enter product name");
            String name = reader.readLine();
            System.out.println("Please, enter product brand");
            String brand = reader.readLine();
            System.out.println("Please, enter product price");
            String pr = reader.readLine();
            int price = 0;
            try {
                price = Integer.parseInt(pr);

            } catch (Exception e) {
                System.out.println("invalid data format");
                return;
            }
            System.out.println("Please, enter shop id to put product");
            String idfShop = reader.readLine();
            Product product = new Product();
            Shop shopName = ShopController.shopService.findById(idfShop);
            if (shopName == null) {
                System.out.println("Shop doesn't exist");
                return;
            }
            product.setPrice(price);
            product.setName(name);
            product.setBrand(brand);
            product.setShopId(idfShop);
            productService.create(product);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        } catch (ShopNotFoundException | ProductNotFoundException e) {
            System.out.println(e);
        }
    }

    private void update(BufferedReader reader) {
        int price = 0;
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            System.out.println("Please, enter product price");
            String pr = reader.readLine();
            try {
                price = Integer.parseInt(pr);
            } catch (NumberFormatException e) {
                System.out.println("invalid data format");
                return;
            }
            Product product = new Product();
            product.setId(id);
            product.setPrice(price);
            if (productService != null) {
                productService.update(product);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            productService.delete(id);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            Product product = productService.findById(id);
            if (product != null) {
                System.out.println("product = " + product);
            } else {
                System.out.println("Product not found");
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        } catch (ShopNotFoundException | ProductNotFoundException e) {
            System.out.println(e);
        }
    }

    private void findAll(BufferedReader reader) {
        List<Product> products = productService.findAll();
        if (products != null && products.size() != 0) {
            for (Product product : products) {
                System.out.println("product = " + product);
            }
        } else {
            System.out.println("product list empty");
        }
    }

    private void findShop(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            if (productService.findShop(id) != null) {
                System.out.println("shop id = " + productService.findShop(id));
            } else {
                System.out.println("no shop with id " + id);
                return;
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }
}
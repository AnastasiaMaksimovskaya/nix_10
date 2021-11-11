package ua.com.alevel.controller;

import ua.com.alevel.config.GenerateImplementationFromInterfaceFactory;
import ua.com.alevel.entity.Shop;
import ua.com.alevel.service.ShopService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShopController {
    static final ShopService shopService = GenerateImplementationFromInterfaceFactory.generateImplementation(ShopService.class);

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
        System.out.println("if you want add shop, please enter 1");
        System.out.println("if you want update shop information, please enter 2");
        System.out.println("if you want delete shop, please enter 3");
        System.out.println("if you want find shop by id, please enter 4");
        System.out.println("if you want find all shops, please enter 5");
        System.out.println("if you want find all products in shop, please enter 6");
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
                findAllProducts(reader);
                break;
            case "7":
                new MainController().run();
                break;
            case "0":
                System.exit(0);
        }
        runNavigation();
    }

    private void create(BufferedReader reader) {
        try {
            System.out.println("Please, enter address");
            String address = reader.readLine();
            System.out.println("Please, enter name");
            String name = reader.readLine();
            Shop shop = new Shop();
            shop.setAddress(address);
            shop.setName(name);
            shopService.create(shop);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            System.out.println("Please, enter shop name");
            String name = reader.readLine();
            Shop shop = new Shop();
            shop.setId(id);
            shop.setName(name);
            shopService.update(shop);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            shopService.delete(id);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            Shop shop = shopService.findById(id);
            if (shop != null) {
                System.out.println("shop = " + shop);
            } else {
                System.out.println("shop with id '" + id + "' not found");
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findAll(BufferedReader reader) {
        Shop[] shops = shopService.findAll();
        if (shops != null && shops.length != 0) {
            for (Shop shop : shops) {
                System.out.println("shop = " + shop);
            }
        } else {
            System.out.println("shop list empty");
        }
    }

    private void findAllProducts(BufferedReader reader) {
        int count = 0;
        try {
            System.out.println("Please, enter shop id");
            String id = reader.readLine();
            if (shopService.findById(id) == null) {
                System.out.println("shop with id '" + id + "' not found");
                return;
            }
            if (shopService.findAllProducts(shopService.findById(id)) == null) {
                System.out.println("there is no products in shop " + shopService.findById(id).getName());
                return;
            }
            for (int i = 0; i < shopService.findAllProducts(shopService.findById(id)).length; i++) {
                if (shopService.findAllProducts(shopService.findById(id))[i] != null) {
                    System.out.print(shopService.findAllProducts(shopService.findById(id))[i] + "\n");
                } else {
                    count++;
                }
            }
            if (count == shopService.findAllProducts(shopService.findById(id)).length) {
                System.out.println("product list is empty");
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }
}
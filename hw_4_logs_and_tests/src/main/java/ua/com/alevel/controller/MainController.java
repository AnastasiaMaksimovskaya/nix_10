package ua.com.alevel.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainController {
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("select your option");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                go(position, reader);
                position = reader.readLine();
                if (position.equals("0")) {
                    System.exit(0);
                }
                go(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        System.out.println();
        System.out.println("if you want to do operations with products, please enter 1");
        System.out.println("if you want to do operations with shops, please enter 2");
        System.out.println("if you want exit, please enter 0");
        System.out.println();
    }

    private void go(String position, BufferedReader reader) {
        switch (position) {
            case "2":
                new ShopController().run();
                break;
            case "1":
                new ProductController().run();
                break;
            case "0":
                System.exit(0);
        }
        runNavigation();
    }
}
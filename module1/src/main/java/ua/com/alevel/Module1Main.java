package ua.com.alevel;

import ua.com.alevel.layer1.KnightMove;
import ua.com.alevel.layer1.NumberOfUniqueNumeric;
import ua.com.alevel.layer1.TriangleArea;
import ua.com.alevel.layer2.BinaryTree;
import ua.com.alevel.layer2.Layer2Run;
import ua.com.alevel.layer2.StringValidation;

import java.util.Scanner;

public class Module1Main {
    public static void print() {
        System.out.println("Press 1 to reverse string \nPress 2 to reverse substring \nPress 3 to reverse substring by index\nPress 0 to quit");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number of layer to test, press 0 to exit");
        while (true) {
            String layer = scanner.nextLine();
            try {
                switch (Integer.parseInt(layer)) {
                    case 0:
                        System.exit(0);
                    case 2:
                        Layer2Run.run();
                }
            } catch (Exception e) {
                System.out.println("Invalid data format");
            }
        }
    }

}

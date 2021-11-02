package ua.com.alevel;

import ua.com.alevel.layer1.KnightMove;
import ua.com.alevel.layer1.NumberOfUniqueNumeric;
import ua.com.alevel.layer1.TriangleArea;
import ua.com.alevel.layer2.BinaryTree;
import ua.com.alevel.layer2.StringValidation;
import ua.com.alevel.layer3.GameOfLife;

import java.util.Scanner;

public class Module1Main {
    static final String LAYER_2 = "Enter a number of task: " +
            "\n press 1 to validate a string " +
            "\n press 2 to calculate max depth of binary tree " +
            "\n press 0 to exit from app " +
            "\n press 9 to back";
    static final String LAYER_1 = "Enter a number of task: " +
            "\n press 1 to move knight " +
            "\n press 2 to calculate number of unique numeric " +
            "\n press 3 to calculate triangle area " +
            "\n press 0 to exit from app " +
            "\n press 9 to back";
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        appRun();
    }

    public static void layer2Run() {
        System.out.println(LAYER_2);
        String task1;
        while (scanner.hasNext()) {
            task1 = scanner.nextLine();
            if (task1.equals("0")) {
                System.exit(0);
            } else if (task1.equals("1")) {
                StringValidation.run(scanner);
            } else if (task1.equals("2")) {
                BinaryTree.run(scanner);
            } else if (task1.equals("9")) {
                appRun();
            }
            System.out.println(LAYER_2);
        }
    }

    public static void appRun() {
        System.out.println("Enter a number of layer to test, press 0 to exit");
        while (true) {
            String layer = scanner.nextLine();
            if (layer.equals("1")) {
                layer1Run();
            } else if (layer.equals("2")) {
                layer2Run();
            } else if (layer.equals("0")) {
                System.exit(0);
            }
            else if (layer.equals("3")){
                GameOfLife.run(scanner);
                Module1Main.appRun();
            }
        }
    }

    public static void layer1Run() {
        System.out.println(LAYER_1);
        String task;
        while (scanner.hasNext()) {
            task = scanner.nextLine();
            if (task.equals("0")) {
                System.exit(0);
            } else if (task.equals("1")) {
                KnightMove.run(scanner);
            } else if (task.equals("2")) {
                NumberOfUniqueNumeric.run(scanner);
            } else if (task.equals("3")) {
                TriangleArea.run(scanner);
            } else if (task.equals("9")) {
                appRun();
            }
            System.out.println(LAYER_1);
        }
    }
}

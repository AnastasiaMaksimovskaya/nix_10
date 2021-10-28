package ua.com.alevel.layer2;

import java.util.Scanner;

public class Layer2Run {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number of task: " +
                "\n press 1 to validate a string " +
                "\n press 2 to calculate max depth of binary tree " +
                "\n press 0 to exit from app " +
                "\n press 9 to back");
        while (true) {
            String task = scanner.nextLine();
            try {
                switch (Integer.parseInt(task)) {
                    case 0:
                        System.exit(0);
                    case 1:
                        StringValidation.run();
                    case 2:
                        BinaryTree.run();
                    case 9: break;
                }
            } catch (Exception e) {
                System.out.println();
            }
        }
    }
}

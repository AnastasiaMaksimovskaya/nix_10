package ua.com.alevel.layer1;

import java.util.Scanner;

public class TriangleArea {
    public static double lineLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public static double calculateArea(double l1, double l2, double l3) {
        double p = (double) (l1 + l2 + l3) / 2;
        return Math.sqrt(p * (p - l1) * (p - l2) * (p - l3));
    }

    public static double area(double x1, double y1, double x2, double y2, double x3, double y3){
       return calculateArea(lineLength(x1, y1, x2, y2),lineLength(x2,y2,x3,y3),lineLength(x1, y1, x3, y3));
    }

    public static void run(Scanner scanner){
        String x1,x2,x3,y1,y2,y3;
        System.out.println("input abscissa of the first point");
        x1 = scanner.nextLine();
        System.out.println("input ordinate of the first point");
        y1 = scanner.nextLine();
        System.out.println("input abscissa of the second point");
        x2 = scanner.nextLine();
        System.out.println("input ordinate of the second point");
        y2 = scanner.nextLine();
        System.out.println("input abscissa of the third point");
        x3 = scanner.nextLine();
        System.out.println("input ordinate of the third point");
        y3 = scanner.nextLine();
        try {
            System.out.printf("%.3f",area(Double.parseDouble(x1),Double.parseDouble(y1),Double.parseDouble(x2),Double.parseDouble(y2),Double.parseDouble(x3),Double.parseDouble(y3)));
            System.out.println();
        }catch (Exception e){
            System.out.println("one of the input is not a number");
        }
    }
}

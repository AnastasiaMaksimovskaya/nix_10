package ua.com.alevel.layer1;

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
}

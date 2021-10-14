package ua.com.alevel;

import java.util.Scanner;

public class SumOfDigits {

    public static void getSumOfDigits() {
        System.out.println("Enter the string to sum up the numbers");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        int sum = 0;
        for (int i = 0; i < input.length(); i++) {
            if ((int) input.charAt(i) > 47 && (int) input.charAt(i) < 58) {
                sum += Character.getNumericValue(input.charAt(i));
            }
        }
        System.out.println("Sum =" + sum);
    }
}
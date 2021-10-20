package ua.com.alevel;

import java.util.Scanner;

public class StringMain {
    public static void print() {
        System.out.println("Press 1 to reverse string \nPress 2 to reverse substring \nPress 3 to reverse substring by index\nPress 0 to quit");
    }

    public static void main(String[] args) {
        print();
        String input;
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {

            switch (scanner.nextLine()) {
                case "1":
                    System.out.println("enter a string");
                    input = scanner.nextLine();
                    System.out.println("Select 1 if you want to reverse full string or 2 if you want to reverse each word");
                    if (scanner.nextLine().equals("1")) {
                        System.out.println(Reverse.reverse(input, true));
                    } else System.out.println(Reverse.reverse(input, false));
                    print();
                    break;
                case "2":
                    System.out.println("enter a string");
                    input = scanner.nextLine();
                    System.out.println("enter a substring");
                    System.out.println(Reverse.reverse(input, scanner.next()));
                    print();
                    break;
                case "3":
                    System.out.println("enter a string");
                    input = scanner.nextLine();
                    System.out.println("enter first index");
                    int firstIndex = scanner.nextInt();
                    System.out.println("enter last index");
                    int lastIndex = scanner.nextInt();
                    System.out.println(Reverse.reverse(input, firstIndex, lastIndex));
                    print();
                    break;
                case "0":
                    System.exit(0);
            }
        }
    }

}

package ua.com.alevel;

import java.util.Scanner;

public class Main {
    public static void print(){
    System.out.println("Press 1 to sum digits of string \nPress 2 to print numbers of letters in string \nPress 3 to count " +
            "the end time of lessons \nPress 0 to quit");
}
    public static void main(String[] args) {
        print();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            switch (scanner.nextInt()) {
                case 2:
                    NumberOfLetters.getNumber();
                    print();
                    break;
                case 3:
                    EndOfLessons.getTimeOfEnd();
                    print();
                    break;
                case 1:
                    SumOfDigits.getSumOfDigits();
                    print();
                    break;
                case 0: System.exit(0);
            }
        }
    }

}

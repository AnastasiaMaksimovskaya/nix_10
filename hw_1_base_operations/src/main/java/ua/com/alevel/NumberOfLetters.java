package ua.com.alevel;

import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class NumberOfLetters {
    public static void getNumber() {
        Set<Character> symbols = new HashSet<>();
        System.out.println("Enter the string to get symbols");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next().toLowerCase(Locale.ROOT);
        int numberOfLetters;
        for (int i = 0; i < input.length(); i++) {
            if (((int)input.charAt(i)>1071&&(int)input.charAt(i)<1104)||((int)input.charAt(i)>96&&(int)input.charAt(i)<123)) {
                symbols.add(input.charAt(i));
            }
        }
        for (char symb : symbols) {
            numberOfLetters = 0;
            for (int i = 0; i < input.length(); i++) {
                if (symb == input.charAt(i)) {
                    numberOfLetters++;
                }
            }
            System.out.println(symb + " - " + numberOfLetters);
        }
    }
}

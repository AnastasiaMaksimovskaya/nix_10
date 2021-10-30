package ua.com.alevel.layer1;

import java.util.*;

public class NumberOfUniqueNumeric {

    static String regex = "^(\\-|\\+)?\\d+";

    private static List createNumberArray(String input) {
        String inputArray[] = input.split(" ");
        List<Integer> numberArray = new ArrayList<Integer>();
        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i].matches(regex)) {
                numberArray.add(Integer.parseInt(inputArray[i]));
            }
        }
        return numberArray;
    }

    private static int calculateUnique(List<Integer> list) {
        Set<Integer> unique = new HashSet<>();
        for (int numbers : list) {
            unique.add(numbers);
        }
        return unique.size();
    }

    public static void run(Scanner scanner) {
        System.out.println("Enter a string to get number of unique numeric, press S to stop");
        while (true) {
            String input = scanner.nextLine();
            List<Integer> list = createNumberArray(input);
            if (input.equals("S")) {
                break;
            }
            for (int numbers : list) {
                System.out.print(numbers + " ");
            }
            System.out.println("\nnubmer of unique numeric is " + calculateUnique(list));
            System.out.println("Enter a string to get number of unique numeric, press S to stop");
        }
    }
}

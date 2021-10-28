package ua.com.alevel.layer1;

import java.util.HashSet;
import java.util.Set;

public class NumberOfUniqueNumeric {
    static String regex = "^(\\-|\\+)?\\d+(\\.\\d+)?$";
    public static double[] createNumberArray(String input) {
        String inputArray[] = input.split(regex);
        int count = 0;

        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i].matches("")) {
                count++;
            }
        }

        double numberArray[] = new double[count];

        for (int i = 0; i < numberArray.length; i++) {
            for (int j = i; j < inputArray.length; j++) {
                if (inputArray[j].matches(regex)) {
                    numberArray[i] = Double.parseDouble(inputArray[j]);
                    break;
                }
            }
        }
        return numberArray;
    }


    public static int calculateUnique(double[] arr) {
        Set<Double> unique = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            unique.add(arr[i]);
        }
        return unique.size();
    }

}

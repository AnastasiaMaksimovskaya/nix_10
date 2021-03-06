package ua.com.alevel.datas;

import java.io.*;
import java.util.*;

public class UniqueDatas {

    public static void run() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(".\\src\\main\\java\\ua\\com\\alevel\\datas\\input.txt"));
        StringBuilder stringBuilder = new StringBuilder();
        while (bufferedReader.ready()) {
            stringBuilder.append(bufferedReader.readLine()).append("\n");
        }
        bufferedReader.close();
        String[] sequences = stringBuilder.toString().split("\\s+|[\n]+");
        Set<String> uniqueDatas = new LinkedHashSet<>();
        for (int i = 0; i < sequences.length; i++) {
            if (sequences[i].matches("\\d{0,2}/\\d{0,2}/\\d{0,4}")) {
                String[] parts = sequences[i].split("/");
                int month = Integer.parseInt(parts[1]);
                int day = Integer.parseInt(parts[0]);
                int year = Integer.parseInt(parts[2]);
                if (check(month, day, year)) {
                    uniqueDatas.add(parts[2] + parts[1] + parts[0]);
                }
            } else if (sequences[i].matches("\\d{0,4}/\\d{0,2}/\\d{0,2}")) {
                String[] parts = sequences[i].split("/");
                int month = Integer.parseInt(parts[1]);
                int day = Integer.parseInt(parts[2]);
                int year = Integer.parseInt(parts[0]);
                if (check(month, day, year)) {
                    uniqueDatas.add(parts[0] + parts[1] + parts[2]);
                }
            } else if (sequences[i].matches("\\d{0,2}-\\d{0,2}-\\d{0,4}")) {
                String[] parts = sequences[i].split("-");
                int month = Integer.parseInt(parts[0]);
                int day = Integer.parseInt(parts[1]);
                int year = Integer.parseInt(parts[2]);
                if (check(month, day, year)) {
                    uniqueDatas.add(parts[2] + parts[0] + parts[1]);
                }
            }
        }
        try (BufferedWriter writter = new BufferedWriter(new FileWriter(".\\src\\main\\java\\ua\\com\\alevel\\datas\\output.txt"))) {
            for (String value : uniqueDatas) {
                writter.write(value + "\n");
            }
        }
    }

    private static boolean check(int month, int day, int year) {
        if (month < 0 || day < 0 || year < 0) {
            return false;
        }
        if ((month == 1 | month == 3 | month == 5 | month == 7 | month == 8 | month == 10 | month == 12) && day > 31) {
            return false;
        } else if ((month == 4 | month == 6 | month == 9 | month == 11) && day > 30) {
            return false;
        } else if (isBissextile(year) && month == 2 && day > 29) {
            return false;
        } else if (!isBissextile(year) && month == 2 && day > 28) {
            return false;
        }
        return true;
    }

    private static boolean isBissextile(int year) {
        if (year % 400 == 0) {
            return true;
        } else if (year % 4 == 0 && year % 100 != 0) {
            return true;
        }
        return false;
    }
}

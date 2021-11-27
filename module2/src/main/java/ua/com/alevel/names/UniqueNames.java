package ua.com.alevel.names;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UniqueNames {
    public static void run() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(".\\src\\main\\java\\ua\\com\\alevel\\names\\input.txt"));
        StringBuilder stringBuilder = new StringBuilder();
        while (bufferedReader.ready()) {
            stringBuilder.append(bufferedReader.readLine()).append("\n");
        }
        bufferedReader.close();
        String[] sequences = stringBuilder.toString().split("\\s+|[\n]+");

        //20-23 строка https://coderoad.ru/43283767
        List<String> list = Arrays.stream(sequences).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey).collect(Collectors.toList());
        if (list.size() > 0) {
            String firstUnique = list.get(list.size() - 1);
            System.out.println("первое уникальное имя  " + firstUnique);
        } else {
            System.out.println("нет уникальных имен");
        }
    }
}

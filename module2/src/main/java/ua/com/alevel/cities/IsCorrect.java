package ua.com.alevel.cities;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class IsCorrect {

    public static boolean check() {
        try (CSVReader csvReader = new CSVReader(new FileReader("cities.csv"))) {
            List<String[]> list = csvReader.readAll();
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(i)[0].equals(list.get(j)[1]) && list.get(j)[0].equals(list.get(i)[1])) {
                        if (!list.get(i)[2].equals(list.get(j)[2])) {
                            return false;
                        }
                    }
                }
            }
        } catch (CsvException | IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
